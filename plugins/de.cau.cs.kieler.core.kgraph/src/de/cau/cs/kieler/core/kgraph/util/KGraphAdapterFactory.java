/**
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
 * $Id$
 */
package de.cau.cs.kieler.core.kgraph.util;

import de.cau.cs.kieler.core.kgraph.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.core.kgraph.KGraphPackage
 * @generated
 */
public class KGraphAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static KGraphPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KGraphAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = KGraphPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KGraphSwitch<Adapter> modelSwitch =
        new KGraphSwitch<Adapter>() {
            @Override
            public Adapter caseKGraphElement(KGraphElement object) {
                return createKGraphElementAdapter();
            }
            @Override
            public Adapter caseKGraphData(KGraphData object) {
                return createKGraphDataAdapter();
            }
            @Override
            public Adapter caseKNode(KNode object) {
                return createKNodeAdapter();
            }
            @Override
            public Adapter caseKEdge(KEdge object) {
                return createKEdgeAdapter();
            }
            @Override
            public Adapter caseKPort(KPort object) {
                return createKPortAdapter();
            }
            @Override
            public Adapter caseKLabel(KLabel object) {
                return createKLabelAdapter();
            }
            @Override
            public Adapter caseEModelElement(EModelElement object) {
                return createEModelElementAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.core.kgraph.KGraphElement <em>Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.core.kgraph.KGraphElement
     * @generated
     */
    public Adapter createKGraphElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.core.kgraph.KGraphData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.core.kgraph.KGraphData
     * @generated
     */
    public Adapter createKGraphDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.core.kgraph.KNode <em>KNode</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.core.kgraph.KNode
     * @generated
     */
    public Adapter createKNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.core.kgraph.KEdge <em>KEdge</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.core.kgraph.KEdge
     * @generated
     */
    public Adapter createKEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.core.kgraph.KPort <em>KPort</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.core.kgraph.KPort
     * @generated
     */
    public Adapter createKPortAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.core.kgraph.KLabel <em>KLabel</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.core.kgraph.KLabel
     * @generated
     */
    public Adapter createKLabelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.emf.ecore.EModelElement
     * @generated
     */
    public Adapter createEModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //KGraphAdapterFactory
