/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
 * $Id$
 */
package de.cau.cs.kieler.kaom;

import de.cau.cs.kieler.annotations.NamedObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kaom.Link#getSource <em>Source</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kaom.Link#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kaom.KaomPackage#getLink()
 * @model
 * @generated
 */
public interface Link extends NamedObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.kaom.Linkable#getOutgoingLinks <em>Outgoing Links</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(Linkable)
     * @see de.cau.cs.kieler.kaom.KaomPackage#getLink_Source()
     * @see de.cau.cs.kieler.kaom.Linkable#getOutgoingLinks
     * @model opposite="outgoingLinks" required="true"
     * @generated
     */
    Linkable getSource();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kaom.Link#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(Linkable value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.kaom.Linkable#getIncomingLinks <em>Incoming Links</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(Linkable)
     * @see de.cau.cs.kieler.kaom.KaomPackage#getLink_Target()
     * @see de.cau.cs.kieler.kaom.Linkable#getIncomingLinks
     * @model opposite="incomingLinks" required="true"
     * @generated
     */
    Linkable getTarget();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kaom.Link#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(Linkable value);

} // Link
