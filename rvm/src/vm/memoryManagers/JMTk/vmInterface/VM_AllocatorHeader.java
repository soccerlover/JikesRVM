/*
 * (C) Copyright IBM Corp. 2001
 */
//$Id$

package com.ibm.JikesRVM.memoryManagers.vmInterface;

import com.ibm.JikesRVM.memoryManagers.JMTk.Header;
import com.ibm.JikesRVM.BootImageInterface;
import com.ibm.JikesRVM.VM_Magic;
import com.ibm.JikesRVM.VM_PragmaInline;
import com.ibm.JikesRVM.VM_PragmaUninterruptible;

/**
 * Chooses the appropriate collector-specific header model.
 *
 * @see com.ibm.JikesRVM.VM_ObjectModel
 * 
 * @author Perry Cheng
 */
public final class VM_AllocatorHeader extends Header {

  /**
   * Override the boot-time initialization method here, so that
   * the core JMTk code doesn't need to know about the 
   * BootImageInterface type.
   */
  public static void initializeHeader(BootImageInterface bootImage, int ref,
                                      Object[] tib, int size, boolean isScalar)
    throws VM_PragmaUninterruptible {
    initializeHeaderBootTime(ref,tib,size,isScalar);
  }

  /**
   * Impedance matching for Jikes RVM which expects to pass
   * Object references to the header
   */

  public static void initializeHeader(Object ref, Object[] tib, int size,
                                      boolean isScalar)
    throws VM_PragmaUninterruptible, VM_PragmaInline {
    Header.initializeHeader(VM_Magic.objectAsAddress(ref),tib,size,isScalar);
  }

  public static void dumpHeader(Object ref) throws VM_PragmaUninterruptible {
    Header.dumpHeader(VM_Magic.objectAsAddress(ref));
  }

}
