/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.36
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.ogdf.lib;

public class UMLGraph extends GraphAttributes {
  private long swigCPtr;

  protected UMLGraph(long cPtr, boolean cMemoryOwn) {
    super(OgdfJNI.SWIGUMLGraphUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(UMLGraph obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      OgdfJNI.delete_UMLGraph(swigCPtr);
    }
    swigCPtr = 0;
    super.delete();
  }

  public UMLGraph(Graph G, int initAttributes) {
    this(OgdfJNI.new_UMLGraph__SWIG_0(Graph.getCPtr(G), G, initAttributes), true);
  }

  public UMLGraph(Graph G) {
    this(OgdfJNI.new_UMLGraph__SWIG_1(Graph.getCPtr(G), G), true);
  }

}
