/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.31
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package net.ogdf.lib;

public class DPointList implements  Iterable<DPoint>  {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected DPointList(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(DPointList obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      OgdfJNI.delete_DPointList(swigCPtr);
    }
    swigCPtr = 0;
  }

  public DPointList() {
    this(OgdfJNI.new_DPointList(), true);
  }

  public boolean empty() {
    return OgdfJNI.DPointList_empty(swigCPtr, this);
  }

  public int size() {
    return OgdfJNI.DPointList_size(swigCPtr, this);
  }

  public DPointListConstIterator iterator() {
    return new DPointListConstIterator(OgdfJNI.DPointList_iterator(swigCPtr, this), true);
  }

}
