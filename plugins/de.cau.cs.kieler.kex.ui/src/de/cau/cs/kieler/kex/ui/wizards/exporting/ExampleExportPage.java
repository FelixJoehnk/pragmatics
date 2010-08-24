package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.SourceType;

public class ExampleExportPage extends WizardPage {

	private static final int TWO_COLUMNS = 0;

	private Text destPath;

	private final int THREE_COLUMNS = 3;
	private List<URL> resources;

	private Tree categoryTree;
	private final List<String> checkedCategories;
	private final List<String> creatableCategories;
	private final List<String> deletableCategories;

	protected ExampleExportPage(String pageName) {
		super(pageName);
		setTitle("Destination Choice");
		setDescription("Set destination for exported Example and determine Example Resources.");
		checkedCategories = new ArrayList<String>();
		creatableCategories = new ArrayList<String>();
		deletableCategories = new ArrayList<String>();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		setControl(composite);

		createTopGroup(composite);
		createMiddleGroup(composite);
		createBottomGroup(composite);
	}

	private void createTopGroup(final Composite composite) {

		Group topGroup = new Group(composite, SWT.NONE);
		GridLayout topLayout = new GridLayout();
		topLayout.numColumns = this.THREE_COLUMNS;
		topGroup.setLayout(topLayout);
		topGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		topGroup.setText("Set Example Destination");
		Label destLabel = new Label(topGroup, SWT.NONE);
		destLabel.setText("To directory:");
		this.destPath = new Text(topGroup, SWT.BORDER);
		this.destPath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button addDestPath = new Button(topGroup, SWT.NONE);
		addDestPath.setText("Add...");

		addDestPath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dirDiag = new DirectoryDialog(composite
						.getShell());

				dirDiag.setText("Choose destination directory");
				dirDiag
						.setMessage("Select a directory in a java plugin project.");
				String dir = dirDiag.open();
				// TODO ueberlegen, ob hier direkt eine pruefung eingebaut
				// werden kann.
				if (dir != null) {
					destPath.setText(dir);
				}
			}
		});
	}

	private void createMiddleGroup(final Composite composite) {
		Group middleGroup = new Group(composite, SWT.NONE);
		GridLayout middleLayout = new GridLayout();
		middleGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		middleLayout.numColumns = 1;
		middleGroup.setText("Add Example Category");
		middleGroup.setToolTipText("Please select one or more cateogies.");
		middleGroup.setLayout(middleLayout);
		createCheckedTree(middleGroup);
		createButtonComposite(middleGroup);

	}

	private void createButtonComposite(Group middleGroup) {
		Composite buttonCompo = new Composite(middleGroup, SWT.NONE);
		GridLayout buttonCompoLayout = new GridLayout();
		buttonCompoLayout.numColumns = THREE_COLUMNS;
		buttonCompo.setLayout(buttonCompoLayout);
		buttonCompo.setLayoutData(new GridData(GridData.FILL_BOTH));
		Button addCategory = new Button(buttonCompo, SWT.NONE);
		addCategory.setText("New...");
		// addCategory.setToolTipText("Creates a new Category");
		// FIXME sch�ner noch mit dem tree editing mechanismus.
		addCategory.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				IInputValidator validator = new IInputValidator() {
					public String isValid(String newText) {
						if (newText.length() < 4) {
							return "Category name has to have at least 4 characters.";
						}
						for (TreeItem item : categoryTree.getItems()) {
							if (newText.equals(item.getText()))
								return "Category exists already! Please enter another name.";
						}
						return null;

					}
				};
				InputDialog dialog = new InputDialog(getShell(),
						"Create New Category", "Please enter a new category.",
						"", validator);
				dialog.open();
				String value = dialog.getValue();
				TreeItem item = new TreeItem(categoryTree, SWT.NONE);
				item.setText(value);
				creatableCategories.add(value);
			}
		});

		Button deleteCategory = new Button(buttonCompo, SWT.NONE);
		deleteCategory.setText("Delete");
		// deleteCategory.setToolTipText("Deletes selected categories.");
		deleteCategory.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] selection = categoryTree.getSelection();
				for (TreeItem item : selection) {
					getDeletableCategories().add(item.getText());
					item.setGrayed(true);
				}
			}
		});

		Button revertTree = new Button(buttonCompo, SWT.NONE);
		revertTree.setText("Revert");
		revertTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				categoryTree.removeAll();
				fillTree(categoryTree);
				creatableCategories.clear();
				deletableCategories.clear();
				super.widgetSelected(e);
			}
		});

	}

	private void createBottomGroup(Composite composite) {
		// TODO think about: exampleFolderButton kann ich glaube ich nicht
		// machen, nachdenken was damit passiert bis zum import
		Group bottomGroup = new Group(composite, SWT.NONE);
		GridLayout bottomLayout = new GridLayout();
		bottomLayout.numColumns = 1;
		bottomGroup.setText("Options");
		bottomGroup.setLayout(bottomLayout);
		bottomGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		Button exampleFolderButton = new Button(bottomGroup, SWT.CHECK);
		exampleFolderButton.setText("create folder with example name");
		exampleFolderButton.setSelection(true);
		Button copyHiddenFilesButton = new Button(bottomGroup, SWT.CHECK);
		copyHiddenFilesButton.setText("copy hidden files");

	}

	void createCheckedTree(Composite parent) {
		this.categoryTree = new Tree(parent, SWT.CHECK | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_BOTH);
		categoryTree.setLayoutData(data);
		categoryTree.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				// fill per hand checked elements list
				if (event.detail == SWT.CHECK) {
					int removeCount = -1;
					String category = ((TreeItem) event.item).getText();
					for (int i = 0; i < checkedCategories.size(); i++) {
						if (category.equals(checkedCategories.get(i))) {
							removeCount = i;
							break;
						}
					}

					if (removeCount == -1) {
						checkedCategories.add(category);
					} else
						checkedCategories.remove(removeCount);
				}
			}

		});
		fillTree(categoryTree);

	}

	/**
	 * Helper method to fill a tree with data
	 * 
	 * @param tree
	 *            the tree to fill
	 */
	public static void fillTree(Tree tree) {
		// disable drawing to avoid flicker
		tree.setRedraw(false);
		List<String> categories = ExampleManager.get().getCategories();
		for (String category : categories) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText(category);
		}
		// enable drawing
		tree.setRedraw(true);
	}

	public List<String> getCheckedCategories() {
		return checkedCategories;
	}

	public String getDestLocation() {
		return this.destPath.getText();
	}

	public List<URL> getExampleResources() {
		return this.resources;
	}

	public SourceType getExportType() {
		// TODO muss der user entscheiden wohin das gehen soll
		// f�r feld wurde SourceType.map gebaut.
		return SourceType.KIELER;
	}

	@Override
	public boolean isPageComplete() {
		return true;
	}

	public List<String> getCreatableCategories() {
		return creatableCategories;
	}

	public List<String> getDeletableCategories() {
		return deletableCategories;
	};
}
