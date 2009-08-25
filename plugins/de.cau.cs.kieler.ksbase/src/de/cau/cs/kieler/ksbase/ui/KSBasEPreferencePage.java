/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.ui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jdt.internal.core.ResolvedBinaryType;
import org.eclipse.jdt.internal.core.ResolvedSourceType;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.part.FileEditorInput;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;

/**
 * The KSBasE preference page
 * 
 * @author Michael Matzen
 */
@SuppressWarnings("restriction")
public class KSBasEPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	// TODO: Make this configurable ? Maybe add another 'internal settings'
	// preference page
	// The classes which have to be implemented/extended by a class to be used
	// as an editor
	protected static final String DIAGRAM_EDITORS[] = new String[] { "org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor" }; //$NON-NLS-1$
	// The classes which have to be implemented/extended by a class to be used
	// as selections for a transformation
	protected static final String DIAGRAM_EDIT_PARTS[] = new String[] {
			"org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart", //$NON-NLS-1$
			"org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart" }; //$NON-NLS-1$
	protected static final String DIAGRAM_PACKAGES[] = new String[] { "org.eclipse.emf.ecore.EPackage" }; //$NON-NLS-1$

	/**
	 * A Dialog which contains 4 check boxes Show in Menu, Show in Context, Show
	 * in Toolbar, Show in Balloon. Used to configure the visibility of
	 * transformations. TODO: Enable only those flags that are enabled in the
	 * editor
	 * 
	 * @author Michael Matzen
	 */
	private class CheckBoxDialog extends Dialog {

		protected Object result;
		Button btMenu, btContext, btToolbar, btBalloon;

		public CheckBoxDialog(Shell parent, int style) {
			super(parent, style);
		}

		public Object open(boolean[] initalSelections) {
			if (initalSelections == null | initalSelections.length != 4)
				return null;

			Shell parent = getParent();
			final Shell shell = new Shell(parent, SWT.DIALOG_TRIM
					| SWT.APPLICATION_MODAL);
			shell.setText(Messages.KSBasEPreferencePage_CheckBoxDialog_Title);

			Composite pane = new Composite(shell, SWT.NONE);
			pane.setLayout(new GridLayout(4, false));

			new Label(pane, SWT.NONE)
					.setText(Messages.KSBasEPreferencePage_ShowInMenu);
			btMenu = new Button(pane, SWT.CHECK);
			btMenu.setSelection(initalSelections[0]);

			new Label(pane, SWT.NONE)
					.setText(Messages.KSBasEPreferencePage_ShowInPopup);
			btContext = new Button(pane, SWT.CHECK);
			btContext.setSelection(initalSelections[1]);

			new Label(pane, SWT.NONE)
					.setText(Messages.KSBasEPreferencePage_ShowInToolbar);
			btToolbar = new Button(pane, SWT.CHECK);
			btToolbar.setSelection(initalSelections[2]);

			new Label(pane, SWT.NONE)
					.setText(Messages.KSBasEPreferencePage_ShowInBalloon);
			btBalloon = new Button(pane, SWT.CHECK);
			btBalloon.setSelection(initalSelections[3]);
			new Label(pane, SWT.NONE);

			Button btCancel = new Button(pane, SWT.NONE);
			btCancel.setText(Messages.KSBasEPreferencePage_Button_Cancel);
			btCancel.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub

				}

				public void widgetSelected(SelectionEvent e) {
					result = null;
					shell.dispose();
				}

			});
			Button btOK = new Button(pane, SWT.NONE);
			btOK.setText(Messages.KSBasEPreferencePage_Button_OK);
			btOK.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
				}

				public void widgetSelected(SelectionEvent e) {
					result = new boolean[] { btMenu.getSelection(),
							btContext.getSelection(), btToolbar.getSelection(),
							btBalloon.getSelection() };
					shell.dispose();
				}

			});
			shell.setLayout(new GridLayout(1, false));
			shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			// pane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			shell.layout();
			shell.pack();
			shell.open();

			Display display = parent.getDisplay();

			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}

			return result;
		}
	}

	protected Text sfMetaModel, sfMenu, sfMenuLoc, sfToolbarLoc;
	protected Combo cbEditors;
	protected Button bfShowMenu, bfShowToolbar, bfShowPopup, bfShowBalloon,
			bfAutoLayout, btBrowseXtend, btModelPackage;
	FileFieldEditor dfDefaultIcon, btTableRemove, btTableAdd;
	protected EditorTransformationSettings activeEditor;
	protected Table table;
	Composite tableComp, browserContainer, btComp;
	private TransformationManager manager;

	public KSBasEPreferencePage() {
		setDescription(Messages.KSBasEPreferencePage_Title);
		manager = TransformationManager.getInstance();
	}

	/**
	 * Creates the contents of the preference page
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);

		Label editorLabel = new Label(container, SWT.NONE);
		editorLabel
				.setText(Messages.KSBasEPreferencePage_EditorSelection_Title);
		cbEditors = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		cbEditors.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			/**
			 * Handles the selection of an editor from the combo box
			 */
			public void widgetSelected(SelectionEvent e) {
				EditorTransformationSettings editor = manager
						.getEditorByName(((Combo) e.getSource()).getText());
				activeEditor = editor;
				if (activeEditor != null) { // Load editor settings
					sfMetaModel.setText(editor.getModelPackageClass());
					sfMenu.setText(editor.getMenuName());
					sfMenuLoc.setText(editor.getMenuLocation());
					sfToolbarLoc.setText(editor.getToolbarLocation());
					bfShowMenu.setSelection(editor.isShownInMenu());
					bfShowPopup.setSelection(editor.isShownInContext());
					bfShowToolbar.setSelection(editor.isShownIToolbar());
					bfShowBalloon.setSelection(editor.isShownInBalloon());
					bfAutoLayout.setSelection(editor.isPerformAutoLayout());
					dfDefaultIcon.setStringValue(editor.getDefaultIconURI()
							.toString());
					// Fill table with transformations
					table.removeAll();
					for (Transformation t : editor.getTransformations()) {
						TableItem tItem = new TableItem(table, SWT.NONE);
						String showIn = ""; //$NON-NLS-1$
						if (t.getVisiblity() == 15)
							showIn = Messages.KSBasEPreferencePage_ShowIn_All;
						else {
							if (t.isShownInMenu())
								showIn = Messages.KSBasEPreferencePage_ShowIn_Menu;
							if (t.isShownInContext()) {
								if (showIn.length() > 0)
									showIn += Messages.KSBasEPreferencePage_ShowIn_Separator;
								showIn += Messages.KSBasEPreferencePage_ShowIn_Popup;
							}
							if (t.isShownIToolbar()) {
								if (showIn.length() > 0)
									showIn += Messages.KSBasEPreferencePage_ShowIn_Separator;
								showIn += Messages.KSBasEPreferencePage_ShowIn_Toolbar;
							}
							if (t.isShownInBalloon()) {
								if (showIn.length() > 0)
									showIn += Messages.KSBasEPreferencePage_ShowIn_Separator;
								showIn += Messages.KSBasEPreferencePage_ShowIn_Balloon;
							}
						}
						tItem.setText(new String[] { t.getName(),
								t.getTransformationName(), showIn,
								t.getPartConfigList(),
								String.valueOf(t.getNumSelections()),
								t.getIconString(), t.getKeyboardShortcut() });
					}
					// enable controls
					sfMetaModel.setEnabled(true);
					sfMenu.setEnabled(true);
					sfMenuLoc.setEnabled(true);
					sfToolbarLoc.setEnabled(true);
					browserContainer.setEnabled(true);
					tableComp.setEnabled(true);
					btComp.setEnabled(true);
					bfShowMenu.setEnabled(true);
					bfShowToolbar.setEnabled(true);
					bfShowPopup.setEnabled(true);
					bfShowBalloon.setEnabled(true);
					bfAutoLayout.setEnabled(true);
				}
			}

		});
		new Label(container, SWT.NONE);

		GridData gData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		cbEditors.setLayoutData(gData);

		final Composite editContainer = new Composite(container, SWT.NONE);
		editContainer.setLayout(new RowLayout());
		Button btAddEditor = new Button(editContainer, SWT.RIGHT);
		btAddEditor.setText(Messages.KSBasEPreferencePage_EditorSelection_Add);
		btAddEditor.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			/**
			 * Handles the 'add editor' event. Shows a list of classes from the
			 * workspace that can be used as editors
			 */
			public void widgetSelected(SelectionEvent e) {
				String[] res = openElementSelectionDialog(DIAGRAM_EDITORS,
						false, editContainer);
				String editorName = ""; //$NON-NLS-1$
				if (res != null) {
					editorName = res[0];

				} else {
					InputDialog dlg = new InputDialog(
							getShell(),
							Messages.KSBasEPreferencePage_DiagramEditor_NoDiagram_Title,
							Messages.KSBasEPreferencePage_DiagramEditor_NoDiagram_Message,
							Messages.KSBasEPreferencePage_DiagramEditor_NoDiagram_Default,
							null);
					if (dlg.open() == InputDialog.OK) {
						editorName = dlg.getValue();
					} else {
						return;
					}
				}
				//only add a diagram once !
				if ( manager.getEditorByName(editorName) == null ) {
				EditorTransformationSettings editor = manager
						.addEditor(editorName);
				cbEditors.add(editor.getEditor());
				cbEditors.select(cbEditors.indexOf(editor.getEditor()));
				cbEditors.notifyListeners(SWT.Selection, null);
				}
			}

		});
		btAddEditor.setEnabled(true);
		Button btModifyEditor = new Button(editContainer, SWT.RIGHT);
		btModifyEditor
				.setText(Messages.KSBasEPreferencePage_EditorSelection_Editor);
		btModifyEditor.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			/**
			 * Handles the 'edit editor' event
			 */
			public void widgetSelected(SelectionEvent e) {
				if (cbEditors.getText().length() > 0) {
					String[] res = openElementSelectionDialog(DIAGRAM_EDITORS,
							false, editContainer);
					if (res != null) {
						manager.getEditorByName(cbEditors.getText()).setEditor(
								res[0]);
						cbEditors.notifyListeners(SWT.Selection, null);
					}
				}
			}

		});
		Button btDelEditor = new Button(editContainer, SWT.RIGHT);
		btDelEditor
				.setText(Messages.KSBasEPreferencePage_EditorSelection_Delete);
		btDelEditor.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			/**
			 * Handles the 'delete editor' event
			 */
			public void widgetSelected(SelectionEvent e) {
				if (cbEditors.getText().length() > 0) {
					manager.removeEditor(cbEditors.getText());

					cbEditors.remove(cbEditors.getText());
					if (cbEditors.getItemCount() > 0) {
						cbEditors.select(0);
					} else { // Disable controls if no other editor exists
						sfMetaModel.setText(""); //$NON-NLS-1$
						sfMetaModel.setEnabled(false);
						sfMenu.setEnabled(false);
						sfMenu.setText(""); //$NON-NLS-1$
						sfMenuLoc.setEnabled(false);
						sfMenuLoc.setText(""); //$NON-NLS-1$
						sfToolbarLoc.setEnabled(false);
						sfToolbarLoc.setText(""); //$NON-NLS-1$
						browserContainer.setEnabled(false);
						tableComp.setEnabled(false);
						btComp.setEnabled(false);
						bfShowMenu.setEnabled(false);
						bfShowToolbar.setEnabled(false);
						bfShowPopup.setEnabled(false);
						bfShowBalloon.setEnabled(false);
						bfAutoLayout.setEnabled(false);
						table.removeAll();
					}
					cbEditors.notifyListeners(SWT.Selection, null);
				}
			}

		});

		new Label(container, SWT.NONE)
				.setText(Messages.KSBasEPreferencePage_MenuName);
		sfMenu = new Text(container, SWT.SINGLE | SWT.BORDER);
		sfMenu.setTextLimit(50);
		sfMenu
				.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
						false));
		sfMenu.setEnabled(false);

		new Label(container, SWT.NONE)
				.setText(Messages.KSBasEPreferencePage_MenuLocation);
		sfMenuLoc = new Text(container, SWT.SINGLE | SWT.BORDER);
		sfMenuLoc.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		sfMenuLoc.setEnabled(false);

		new Label(container, SWT.NONE)
				.setText(Messages.KSBasEPreferencePage_ToolbarLocation);
		sfToolbarLoc = new Text(container, SWT.SINGLE | SWT.BORDER);
		sfToolbarLoc.setTextLimit(Text.LIMIT);
		sfToolbarLoc.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		sfToolbarLoc.setEnabled(false);

		browserContainer = new Composite(parent, SWT.NONE);
		browserContainer.setLayout(new GridLayout(3, true));

		new Label(browserContainer, SWT.NONE)
				.setText(Messages.KSBasEPreferencePage_ModelPackage);
		sfMetaModel = new Text(browserContainer, SWT.SINGLE | SWT.BORDER
				| SWT.READ_ONLY);
		sfMetaModel.setEnabled(false);
		btModelPackage = new Button(browserContainer, SWT.NONE);
		btModelPackage.setText(Messages.KSBasEPreferencePage_Button_Browse);
		btModelPackage.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			/**
			 * Handles the 'browse model package' event Shows a simple
			 * FileDialog in which a file can be selected
			 */
			public void widgetSelected(SelectionEvent e) {
				String[] res = openElementSelectionDialog(DIAGRAM_PACKAGES,
						false, editContainer);
				String modelPack = ""; //$NON-NLS-1$
				if (res != null) {
					modelPack = res[0];
				} else {
					InputDialog dlg = new InputDialog(
							getShell(),
							Messages.KSBasEPreferencePage_ModelPackage_NoPackageFound_Title,
							Messages.KSBasEPreferencePage_ModelPackage_NoPackageFound_Message,
							Messages.KSBasEPreferencePage_ModelPackage_NoPackageFound_Default,
							null);
					if (dlg.open() == InputDialog.OK) {
						modelPack = dlg.getValue();
					} else {
						return;
					}
				}

				EditorTransformationSettings editor = manager
						.getEditorByName(cbEditors.getText());
				editor.setModelPackageClass(modelPack);
				cbEditors.notifyListeners(SWT.Selection, null);
			}

		});
		sfMetaModel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));

		dfDefaultIcon = new FileFieldEditor(
				Messages.KSBasEPreferencePage_DefaultIconName,
				Messages.KSBasEPreferencePage_DefaultIcon, browserContainer);
		dfDefaultIcon.setFileExtensions(new String[] {
				Messages.KSBasEPreferencePage_IconExtension_PNG,
				Messages.KSBasEPreferencePage_IconExtension_ICO });

		browserContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING,
				true, false));
		browserContainer.setEnabled(false);

		bfShowMenu = new Button(container, SWT.CHECK);
		bfShowMenu.setText(Messages.KSBasEPreferencePage_ShowMenu);
		bfShowMenu.setSelection(true);
		bfShowMenu.setEnabled(false);

		bfShowToolbar = new Button(container, SWT.CHECK);
		bfShowToolbar.setText(Messages.KSBasEPreferencePage_ShowToolbar);
		bfShowToolbar.setSelection(true);
		bfShowToolbar.setEnabled(false);

		bfShowPopup = new Button(container, SWT.CHECK);
		bfShowPopup.setText(Messages.KSBasEPreferencePage_ShowPopup);
		bfShowPopup.setSelection(true);
		bfShowPopup.setEnabled(false);

		bfShowBalloon = new Button(container, SWT.CHECK);
		bfShowBalloon.setText(Messages.KSBasEPreferencePage_ShowBalloon);
		bfShowBalloon.setSelection(true);
		bfShowBalloon.setEnabled(false);

		bfAutoLayout = new Button(container, SWT.CHECK);
		bfAutoLayout.setText(Messages.KSBasEPreferencePage_AutoLayout);
		bfAutoLayout.setSelection(true);
		bfAutoLayout.setEnabled(false);

		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false,
				false));

		tableComp = new Composite(parent, SWT.NONE);
		tableComp.setLayout(new GridLayout(1, false));
		Label tableLabel = new Label(tableComp, SWT.NONE);
		tableLabel.setText(Messages.KSBasEPreferencePage_TableTitle);

		table = new Table(tableComp, SWT.BORDER | SWT.SINGLE);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn[] titleCols = new TableColumn[7];
		titleCols[0] = new TableColumn(table, SWT.NONE);
		titleCols[0].setText(Messages.KSBasEPreferencePage_Table_Col_Name);
		titleCols[0]
				.setToolTipText(Messages.KSBasEPreferencePage_Table_Col_Name_ToolTip);
		titleCols[0].setWidth(150);

		titleCols[1] = new TableColumn(table, SWT.NONE);
		titleCols[1]
				.setText(Messages.KSBasEPreferencePage_Table_Col_Transformation);
		titleCols[1]
				.setToolTipText(Messages.KSBasEPreferencePage_Table_Col_Transformation_ToolTip);
		titleCols[1].setWidth(150);

		titleCols[2] = new TableColumn(table, SWT.NONE);
		titleCols[2].setText(Messages.KSBasEPreferencePage_Table_Col_Visible);
		titleCols[2]
				.setToolTipText(Messages.KSBasEPreferencePage_Table_Col_Visible_ToolTip);
		titleCols[2].setWidth(75);

		titleCols[3] = new TableColumn(table, SWT.NONE);
		titleCols[3].setText(Messages.KSBasEPreferencePage_Table_Col_Elements);
		titleCols[3]
				.setToolTipText(Messages.KSBasEPreferencePage_Table_Col_Elements_ToolTip);
		titleCols[3].setWidth(150);

		titleCols[4] = new TableColumn(table, SWT.NONE);
		titleCols[4]
				.setText(Messages.KSBasEPreferencePage_Table_Col_Selections);
		titleCols[4]
				.setToolTipText(Messages.KSBasEPreferencePage_Table_Col_Selections_ToolTip);
		titleCols[4].setWidth(60);

		titleCols[5] = new TableColumn(table, SWT.NONE);
		titleCols[5].setText(Messages.KSBasEPreferencePage_Table_Col_Icon);
		titleCols[5].setWidth(50);

		titleCols[6] = new TableColumn(table, SWT.NONE);
		titleCols[6].setText(Messages.KSBasEPreferencePage_Table_Col_Shortcut);
		titleCols[6].setWidth(75);
		titleCols[6]
				.setToolTipText(Messages.KSBasEPreferencePage_Table_Col_Shortcut_ToolTip);

		// Cursor to edit Table cells
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;

		cursor.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				table.setSelection(new TableItem[] { cursor.getRow() });
			}

			/**
			 * Handles the event of selecting a table entry
			 */
			public void widgetSelected(SelectionEvent e) {

				TableItem row = cursor.getRow();
				int col = cursor.getColumn();
				final Transformation transformation = activeEditor
						.getTransformations().get(table.indexOf(row));
				if (col == 0 || col == 4) {
					// Show text editors for entering a name or the number of
					// selections
					final Text text = new Text(cursor, SWT.NONE);
					text.addKeyListener(new KeyAdapter() {

						@Override
						public void keyPressed(KeyEvent e) {
							if (e.character == SWT.CR) {
								// TableItem innerRow = cursor.getRow();
								int col = cursor.getColumn();
								if (col == 4) { // Number of selections has to
									// be an int-value
									try {
										int number = Integer.parseInt(text
												.getText());
										transformation.setNumSelections(number);
									} catch (NumberFormatException excep) {
										// ignore invalid input
									}
								} else if (col == 0) {
									transformation.setName(text.getText());
								}
								text.dispose();
								cbEditors.notifyListeners(SWT.Selection, null);
							}

						}
					});
					text.addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent e) {
							int col = cursor.getColumn();
							if (col == 4) { // Number of selections has to
								// be an int-value
								try {
									int number = Integer.parseInt(text
											.getText());
									transformation.setNumSelections(number);
								} catch (NumberFormatException excep) {
									// ignore invalid input
								}
							} else if (col == 0) {
								transformation.setName(text.getText());
							}
							text.dispose();
						}
					});
					text.setText(row.getText(col));
					editor.setEditor(text);
					text.setFocus();
				} else if (col == 2) {
					// Show checkbox dialog to select visiblity flags
					CheckBoxDialog dlg = new CheckBoxDialog(getShell(),
							SWT.NONE);
					row.getText(col);
					Object result = dlg.open(new boolean[] {
							transformation.isShownInMenu(),
							transformation.isShownInContext(),
							transformation.isShownIToolbar(),
							transformation.isShownInBalloon() });
					if (result != null) {
						boolean[] bRes = (boolean[]) result;
						int visible = 0;
						if (bRes[0])
							visible += KSBasEPlugin.SHOW_MENU;
						if (bRes[1])
							visible += KSBasEPlugin.SHOW_CONTEXT;
						if (bRes[2])
							visible += KSBasEPlugin.SHOW_TOOLBAR;
						if (bRes[3])
							visible += KSBasEPlugin.SHOW_BALLOON;

						transformation.setVisibility(visible);
						cbEditors.notifyListeners(SWT.Selection, null);
					}
				} else if (col == 3) {
					// Open a dialog to select diagram elements
					String[] res = openElementSelectionDialog(
							DIAGRAM_EDIT_PARTS, true, editContainer);

					if (res == null) {

						InputDialog dlg = new InputDialog(
								getShell(),
								Messages.KSBasEPreferencePage_DiagramElements_NoElements_Title,
								Messages.KSBasEPreferencePage_DiagramElements_NoElements_Message,
								Messages.KSBasEPreferencePage_DiagramElements_NoElements_Default,
								null);
						if (dlg.open() == InputDialog.OK) {
							res = dlg.getValue().split(","); //$NON-NLS-1$
						} else {
							return;
						}
					}
					transformation.setPartConfig(res);
					cbEditors.notifyListeners(SWT.Selection, null);

				} else if (col == 5) { // Icon
					FileDialog dlg = new FileDialog(getShell(), SWT.OPEN);
					dlg.setFilterExtensions(new String[] {
							Messages.KSBasEPreferencePage_IconExtension_PNG,
							Messages.KSBasEPreferencePage_IconExtension_ICO });
					String fileName = dlg.open();
					if (fileName != null) {
						transformation.setIconURI(URI.create(fileName));
						cbEditors.notifyListeners(SWT.Selection, null);
					}
				} else if (col == 6) { // Keyboard shortcut
					// We are using two text fields here
					// one for storing the actual shortcut text including
					// modifiers ('text') and one
					// for storing the raw pressed keys ('keys') without
					// modifiers
					final Text text = new Text(cursor, SWT.NONE);
					// We can't use a string here 'cause we need a final
					// variable which is defined
					// in the enclosing type and we really don't need another
					// class variable
					final Text keys = new Text(cursor, SWT.NONE);
					text.addKeyListener(new KeyAdapter() {

						@Override
						public void keyPressed(KeyEvent e) {
							if (e.keyCode == SWT.DEL || e.keyCode == SWT.BS) {
								text.setText(""); //$NON-NLS-1$
								keys.setText(""); //$NON-NLS-1$
							} else {
								if (e.keyCode >= 97 && e.keyCode <= 122) { // only
									// allow
									// characters
									String ex = ""; //$NON-NLS-1$
									if (ex.length() > 0)
										ex += "+"; //$NON-NLS-1$
									if ((e.stateMask & SWT.CTRL) != 0) {
										ex += Messages.KSBasEPreferencePage_Shortcut_CTRL;
									}
									if ((e.stateMask & SWT.ALT) != 0) {
										ex += Messages.KSBasEPreferencePage_Shortcut_ALT;
									}
									if ((e.stateMask & SWT.SHIFT) != 0) {
										ex += Messages.KSBasEPreferencePage_Shortcut_SHIFT;
									}
									if (keys.getText().length() > 0)
										ex += keys;

									keys.append(String
											.valueOf((char) e.keyCode)
											+ " + "); //$NON-NLS-1$

									ex += (char) e.keyCode;
									text.setText(ex.toUpperCase());
								}
							}
						}
					});
					text.addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent e) {

							if (text.getText().length() > 0) {
								transformation.setKeyboardShortcut(text
										.getText());
								cbEditors.notifyListeners(SWT.Selection, null);
							}
							text.dispose();
							keys.dispose();
						}
					});
					text.setText(row.getText(col));
					editor.setEditor(text);
					text.setFocus();
				}
			}
		});

		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableComp.setEnabled(false);

		btComp = new Composite(parent, SWT.NONE);
		btComp.setLayout(new GridLayout(4, false));
		btComp.setEnabled(false);

		Button btTableAdd = new Button(btComp, SWT.NONE);
		btTableAdd
				.setText(Messages.KSBasEPreferencePage_Button_Edit_Transformations);
		btTableAdd.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			/**
			 * Handles the 'Edit transformations' event. Closes the preferences
			 * page and opens the Xtend file in a new workbench editor
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(getShell(), SWT.OK | SWT.CANCEL);
				box
						.setMessage(Messages.KSBasEPreferencePage_EditTransformations_Message);
				box
						.setText(Messages.KSBasEPreferencePage_EditTransformations_Title);
				if (box.open() == SWT.OK) {
					try {

						DirectoryDialog dlg = new DirectoryDialog(getShell());
						dlg.setFilterPath(ResourcesPlugin.getWorkspace()
								.getRoot().getLocation().toOSString());
						dlg
								.setMessage("Select a source folder to store and open the transformation file");

						String result = dlg.open();
						if (result != null && result.length() > 0) {
							final File tmpFile = File.createTempFile(
									"extension", ".ext", new File(result));

							IEditorDescriptor desc = PlatformUI.getWorkbench()
									.getEditorRegistry().getDefaultEditor(
											"feature.ext");
							IWorkspace workspace = ResourcesPlugin
									.getWorkspace();
							IPath location = Path.fromOSString(tmpFile
									.getAbsolutePath());
							IFile file = workspace.getRoot()
									.getFileForLocation(location);
							ByteArrayInputStream extStream = new ByteArrayInputStream(
									activeEditor.getExtFile().getBytes());
							if (file.exists()) {
								file.setContents(extStream, IFile.FORCE, null);
							} else {
								file.create(extStream, IFile.FORCE, null);
							}
							IWorkbenchPage page = PlatformUI.getWorkbench()
									.getActiveWorkbenchWindow().getActivePage();
							FileEditorInput input = new FileEditorInput(file);
							IEditorPart editor = page.openEditor(input, desc
									.getId());
							IPropertyListener list = new IPropertyListener() {

								private boolean dirty = false;

								public void propertyChanged(Object source,
										int propId) {
									if (propId == IWorkbenchPartConstants.PROP_DIRTY) {
										if (dirty) {
											activeEditor
													.parseTransformationsFromFile(tmpFile
															.getAbsolutePath());
											TransformationManager.getInstance()
													.storeTransformations();
											dirty = false;
											return;
										} else {
											dirty = true;
										}
									}
								}
							};

							editor.addPropertyListener(list);
							performOk();
						}
					} catch (PartInitException excep) {
						excep.printStackTrace();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		btBrowseXtend = new Button(btComp, SWT.NONE);
		btBrowseXtend.setText("Load Xtend File");
		btBrowseXtend.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			/**
			 * Handles the 'browse extend file' event Shows a simple FileDialog
			 * in which a file can be selected
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(getShell(), SWT.YES | SWT.NO);
				box
						.setMessage("Loading an xtend file may reset the existing transformations.\r\n Do you want continue?");
				box
						.setText(Messages.KSBasEPreferencePage_EditTransformations_Title);
				if (box.open() == SWT.OK) {
					FileDialog dlg = new FileDialog(getShell());
					dlg.setFilterPath(ResourcesPlugin.getWorkspace().getRoot()
							.getLocation().toOSString());
					dlg
							.setFileName(Messages.KSBasEPreferencePage_XtendFile_DefaultName);
					dlg
							.setFilterExtensions(new String[] { Messages.KSBasEPreferencePage_XtendFile_Extension });
					dlg
							.setText(Messages.KSBasEPreferencePage_XtendFile_DialogTitle);
					String result = dlg.open();
					if (result != null) {
						IPath path = new Path(result);

						IFile file = ResourcesPlugin.getWorkspace().getRoot()
								.getFileForLocation(path);

						if (file == null) {
							box = new MessageBox(getShell(), SWT.OK);
							box
									.setMessage(Messages.KSBasEPreferencePage_XtendFile_InvalidFile);
							box.open();
							return;
						} else {
							manager.getEditorByName(cbEditors.getText())
									.parseTransformationsFromFile(result);
							cbEditors.notifyListeners(SWT.Selection, null);
						}
					}
				}
			}

		});

		Button btImport = new Button(btComp, SWT.RIGHT);
		btImport.setText("Import Settings");
		btImport.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));

		Button btExport = new Button(btComp, SWT.RIGHT);
		btExport.setText("Export Settings");
		btExport.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));

		btComp.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

		// Fill editors combo box with existing editors
		if (manager.getEditors() != null) {
			for (EditorTransformationSettings s : manager.getEditors()) {
				cbEditors.add(s.getEditor());
			}
			if (cbEditors.getItemCount() > 0) {
				cbEditors.select(0);
				cbEditors.notifyListeners(SWT.Selection, null);
			}
		}
		return null;
	}

	/**
	 * Initalizes the preference page
	 */
	public void init(IWorkbench workbench) {
		setPreferenceStore(KSBasEPlugin.getDefault().getPreferenceStore());
		manager.initializeTransformations();
	}

	/**
	 * Performs an 'OK' command. i.e. stores the settings for the currently
	 * selected editor
	 */
	@Override
	public boolean performOk() {
		if (activeEditor != null) {
			activeEditor.setModelPackageClass(sfMetaModel.getText());
			activeEditor.setMenuName(sfMenu.getText());
			activeEditor.setMenuLocation(sfMenuLoc.getText());
			activeEditor.setToolbarLocation(sfToolbarLoc.getText());
			int flags = 0;
			if (bfShowMenu.getSelection())
				flags |= KSBasEPlugin.SHOW_MENU;
			if (bfShowPopup.getSelection())
				flags |= KSBasEPlugin.SHOW_CONTEXT;
			if (bfShowToolbar.getSelection())
				flags |= KSBasEPlugin.SHOW_TOOLBAR;
			if (bfShowBalloon.getSelection())
				flags |= KSBasEPlugin.SHOW_BALLOON;

			activeEditor.setVisibilityFlags(flags);
			activeEditor.setPerformAutoLayout(bfAutoLayout.getSelection());
			activeEditor.setDefaultIconURI(URI.create(dfDefaultIcon
					.getStringValue()));
			manager.storeTransformations();
		}

		// Recreate menu, or add new :P
		KSBasEPlugin.getDefault().createMenu();

		return super.performOk();
	}

	/**
	 * Opens an TypeSelectionDialog (@see E...) to select either single or
	 * multiple entries from a filtered list of existing classes in the current
	 * workspace
	 * 
	 * @param types
	 *            A number of types which have to be implemented or extend by
	 *            the class
	 * @param multiple
	 *            Allow multiple selection?
	 * @return The list of selected entries
	 */
	protected String[] openElementSelectionDialog(String[] types,
			boolean multiple, Composite parent) {
		try {
			final LinkedList<IJavaElement> javaelements = new LinkedList<IJavaElement>();
			final ProgressMonitorPart monitor = new ProgressMonitorPart(parent,
					new GridLayout());
			
			
			for (String type : types) {
				SearchPattern p = SearchPattern.createPattern(type,
						IJavaSearchConstants.CLASS_AND_INTERFACE,
						IJavaSearchConstants.IMPLEMENTORS,
						SearchPattern.R_EXACT_MATCH);
				IJavaSearchScope scope = SearchEngine.createWorkspaceScope();

				SearchRequestor req = new SearchRequestor() {

					@Override
					public void acceptSearchMatch(SearchMatch match)
							throws CoreException {
						if (match.getElement() instanceof ResolvedSourceType) {
							ResolvedSourceType type = ((ResolvedSourceType) match
									.getElement());
							javaelements.add(type.getCompilationUnit());
						} else if (match.getElement() instanceof ResolvedBinaryType) {
							// Ignore binary types for now

							// ResolvedBinaryType type =
							// ((ResolvedBinaryType)match.getElement());
							// System.out.println(type.getFullyQualifiedName());
							// diagrams.add(type.getFullyQualifiedName());
						}
					}

				};
				
				SearchEngine engine = new SearchEngine();
				engine.search(p, new SearchParticipant[] { SearchEngine
						.getDefaultSearchParticipant() }, scope, req, monitor);
			}
			monitor.done();
			if (javaelements.size() > 0) {
				IJavaElement[] jelem = new IJavaElement[javaelements.size()];
				System.arraycopy(javaelements.toArray(), 0, jelem,0 , javaelements.size());
				SelectionDialog listDlg = JavaUI.createTypeDialog(getShell(), new ProgressMonitorDialog(getShell()), SearchEngine.createJavaSearchScope(jelem), IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES, multiple);

				listDlg.setTitle(Messages.KSBasEPreferencePage_91);
				
				if (listDlg.open() == ResourceListSelectionDialog.OK) {
					Object[] dlgResult = listDlg.getResult();
					if ( dlgResult == null)
						return null;
					
					String[] result = new String[dlgResult.length];
					for ( int i = 0; i < dlgResult.length; ++i) {
						if ( dlgResult[i] instanceof SourceType) {
							result[i] = ((SourceType)dlgResult[i]).getFullyQualifiedName();
						}
					}
					return result;
				}
			}

		} catch (Exception excep) {
			excep.printStackTrace();
		}

		return null;
	}
}
