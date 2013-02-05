/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KGrid Placement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Creates a grid with <numColumns> inside the area defined by <topLeft> and <bottomRight>.
 * the grids number of rows depends on the numer of child elements to be placed. Each child is set to the first free column inside the grid. If no column is left, the next childElement is placed in the first column of a new row.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getNumColumns <em>Num Columns</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getTopLeft <em>Top Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getBottomRight <em>Bottom Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacement()
 * @model
 * @generated
 */
public interface KGridPlacement extends KPlacement {
    /**
     * Returns the value of the '<em><b>Num Columns</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Num Columns</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The number of columns the grid should have
     * if set to -1, all elements are placed in a single row
     * <!-- end-model-doc -->
     * @return the value of the '<em>Num Columns</em>' attribute.
     * @see #setNumColumns(int)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacement_NumColumns()
     * @model required="true"
     * @generated
     */
    int getNumColumns();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getNumColumns <em>Num Columns</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Num Columns</em>' attribute.
     * @see #getNumColumns()
     * @generated
     */
    void setNumColumns(int value);

    /**
     * Returns the value of the '<em><b>Top Left</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Top Left</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the top left position of the grid
     * <!-- end-model-doc -->
     * @return the value of the '<em>Top Left</em>' reference.
     * @see #setTopLeft(KPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacement_TopLeft()
     * @model
     * @generated
     */
    KPosition getTopLeft();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getTopLeft <em>Top Left</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Top Left</em>' reference.
     * @see #getTopLeft()
     * @generated
     */
    void setTopLeft(KPosition value);

    /**
     * Returns the value of the '<em><b>Bottom Right</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bottom Right</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the bottom right position of the grid
     * <!-- end-model-doc -->
     * @return the value of the '<em>Bottom Right</em>' reference.
     * @see #setBottomRight(KPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacement_BottomRight()
     * @model
     * @generated
     */
    KPosition getBottomRight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getBottomRight <em>Bottom Right</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bottom Right</em>' reference.
     * @see #getBottomRight()
     * @generated
     */
    void setBottomRight(KPosition value);

} // KGridPlacement
