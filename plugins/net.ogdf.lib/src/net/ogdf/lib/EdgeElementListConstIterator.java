/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.31
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.ogdf.lib;

public class EdgeElementListConstIterator {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected EdgeElementListConstIterator(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EdgeElementListConstIterator obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      OgdfJNI.delete_EdgeElementListConstIterator(swigCPtr);
    }
    swigCPtr = 0;
  }

  public EdgeElementListConstIterator() {
    this(OgdfJNI.new_EdgeElementListConstIterator(), true);
  }

  public boolean hasNext() {
    return OgdfJNI.EdgeElementListConstIterator_hasNext(swigCPtr, this);
  }

  public EdgeElement next() {
    long cPtr = OgdfJNI.EdgeElementListConstIterator_next(swigCPtr, this);
    return (cPtr == 0) ? null : new EdgeElement(cPtr, false);
  }

  public void remove() {
    OgdfJNI.EdgeElementListConstIterator_remove(swigCPtr, this);
  }

}
