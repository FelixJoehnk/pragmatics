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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KStyle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Adds additional StyleInformation to a rendering.
 * can be set to propagate to children to make redefinining styles unneccessary
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyle#getRendering <em>Rendering</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyle#isPropagateToChildren <em>Propagate To Children</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyle#getFunctionId <em>Function Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle()
 * @model abstract="true"
 * @generated
 */
public interface KStyle extends EObject {
    /**
     * Returns the value of the '<em><b>Rendering</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rendering</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the rendering the style is attached to
     * <!-- end-model-doc -->
     * @return the value of the '<em>Rendering</em>' reference.
     * @see #setRendering(KRendering)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle_Rendering()
     * @model
     * @generated
     */
    KRendering getRendering();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyle#getRendering <em>Rendering</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rendering</em>' reference.
     * @see #getRendering()
     * @generated
     */
    void setRendering(KRendering value);

    /**
     * Returns the value of the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Propagate To Children</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * defines whether child elements of the rendering should inherit this style
     * <!-- end-model-doc -->
     * @return the value of the '<em>Propagate To Children</em>' attribute.
     * @see #setPropagateToChildren(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle_PropagateToChildren()
     * @model required="true"
     * @generated
     */
    boolean isPropagateToChildren();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyle#isPropagateToChildren <em>Propagate To Children</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Propagate To Children</em>' attribute.
     * @see #isPropagateToChildren()
     * @generated
     */
    void setPropagateToChildren(boolean value);

    /**
     * Returns the value of the '<em><b>Function Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * id of the function that should be called when layout is finished to change this style
     * <!-- end-model-doc -->
     * @return the value of the '<em>Function Id</em>' attribute.
     * @see #setFunctionId(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle_FunctionId()
     * @model
     * @generated
     */
    String getFunctionId();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyle#getFunctionId <em>Function Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Id</em>' attribute.
     * @see #getFunctionId()
     * @generated
     */
    void setFunctionId(String value);

} // KStyle
