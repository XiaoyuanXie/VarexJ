//
// Copyright (C) 2007 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
// 
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
// 
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//
package gov.nasa.jpf.vm;

import gov.nasa.jpf.annotation.MJI;

import java.util.Map;

import de.fosd.typechef.featureexpr.FeatureExpr;

/**
 * @author Nastaran Shafiei <nastaran.shafiei@gmail.com>
 * 
 * Native peer for java.lang.ClassLoader
 */
public class JPF_java_lang_ClassLoader extends NativePeer {

  @MJI
  public void $init____V (MJIEnv env, int objRef) {
    ClassLoaderInfo systemCl = ClassLoaderInfo.getCurrentSystemClassLoader();
    $init__Ljava_lang_ClassLoader_2__V (env, objRef, systemCl.getClassLoaderObjectRef());
  }

  @MJI
  public void $init__Ljava_lang_ClassLoader_2__V (MJIEnv env, int objRef, int parentRef) {
    Heap heap = env.getHeap();
    FeatureExpr ctx = NativeMethodInfo.CTX;
    //--- Retrieve the parent ClassLoaderInfo
    ClassLoaderInfo parent = env.getClassLoaderInfo(parentRef);

    //--- create the internal representation of the classloader
    ClassLoaderInfo cl = new ClassLoaderInfo(ctx, env.getVM(), objRef, new ClassPath(), parent);

    //--- initialize the java.lang.ClassLoader object
    ElementInfo ei = heap.getModifiable(objRef);
    ei.setIntField(ctx, ClassLoaderInfo.ID_FIELD, cl.getId());

    // we should use the following block if we ever decide to make systemClassLoader 
    // unavailable if(parent.isSystemClassLoader) {
    //  // we don't want to make the systemCLassLoader available through SUT
    //  parentRef = MJIEnv.NULL;
    // }

    ei.setReferenceField("parent", parentRef);
  }

  @MJI
  public int getSystemClassLoader____Ljava_lang_ClassLoader_2 (MJIEnv env, int clsObjRef) {
    return ClassLoaderInfo.getCurrentSystemClassLoader().getClassLoaderObjectRef();
  }

  @MJI
  public int getResource0__Ljava_lang_String_2__Ljava_lang_String_2 (MJIEnv env, int objRef, int resRef){
    String rname = env.getStringObject(resRef);

    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);

    String resourcePath = cl.findResource(rname);

    return env.newString(NativeMethodInfo.CTX, resourcePath);
  }

  @MJI
  public int getResources0__Ljava_lang_String_2___3Ljava_lang_String_2 (MJIEnv env, int objRef, int resRef) {
    String rname = env.getStringObject(resRef);

    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);

    String[] resources = cl.findResources(rname);

    return env.newStringArray(resources);
  }

  @MJI
  public int findLoadedClass__Ljava_lang_String_2__Ljava_lang_Class_2 (MJIEnv env, int objRef, int nameRef) {
    String cname = env.getStringObject(nameRef);

    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);

    ClassInfo ci = cl.getAlreadyResolvedClassInfo(cname);
    if(ci != null) {
      return ci.getClassObjectRef();
    }

    return MJIEnv.NULL;
  }

  @MJI
  public int findSystemClass__Ljava_lang_String_2__Ljava_lang_Class_2 (MJIEnv env, int objRef, int nameRef) {
    String cname = env.getStringObject(nameRef);

    checkForIllegalName(env, cname);
    if(env.hasException()) {
      return MJIEnv.NULL;
    }

    ClassLoaderInfo cl = ClassLoaderInfo.getCurrentSystemClassLoader();

    ClassInfo ci = cl.getResolvedClassInfo(cname);

    if(!ci.isRegistered()) {
      ci.registerClass(env.getThreadInfo());
    }

    return ci.getClassObjectRef();
  }

  @MJI
  public int defineClass0__Ljava_lang_String_2_3BII__Ljava_lang_Class_2 
                                      (MJIEnv env, int objRef, int nameRef, int bufferRef, int offset, int length) {
    String cname = env.getStringObject(nameRef);
    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);

    // determine whether that the corresponding class is already defined by this 
    // classloader, if so, this attempt is invalid, and loading throws a LinkageError
    if (cl.getDefinedClassInfo(cname) != null) {  // attempt to define twice
      env.throwException("java.lang.LinkageError"); 
      return MJIEnv.NULL;
    }
        
    byte[] buffer = env.getByteArrayObject(bufferRef);
    
    try {
      ClassInfo ci = cl.getResolvedClassInfo( cname, buffer, offset, length);

      // Note: if the representation is not of a supported major or minor version, loading 
      // throws an UnsupportedClassVersionError. But for now, we do not check for this here 
      // since we don't do much with minor and major versions

      ThreadInfo ti = env.getThreadInfo();
      ci.registerClass(ti);

      return ci.getClassObjectRef();
      
    } catch (ClassInfoException cix){
      env.throwException("java.lang.ClassFormatError");
      return MJIEnv.NULL;
    }
  }


  protected static boolean check(MJIEnv env, String cname, byte[] buffer, int offset, int length) {
    // throw SecurityExcpetion if the package prefix is java
    checkForProhibitedPkg(env, cname);

    // throw NoClassDefFoundError if the given class does name might 
    // not be a valid binary name
    checkForIllegalName(env, cname);

    // throw IndexOutOfBoundsException if buffer length is not consistent
    // with offset
    checkData(env, buffer, offset, length);

    return !env.hasException();
  }

  protected static void checkForProhibitedPkg(MJIEnv env, String name) {
    if(name != null && name.startsWith("java.")) {
      env.throwException("java.lang.SecurityException", "Prohibited package name: " + name);
    }
  }

  protected static void checkForIllegalName(MJIEnv env, String name) {
    if((name == null) || (name.length() == 0)) {
      return;
    }

    if((name.indexOf('/') != -1) || (name.charAt(0) == '[')) {
      env.throwException("java.lang.NoClassDefFoundError", "IllegalName: " + name);
    }
  }

  protected static void checkData(MJIEnv env, byte[] buffer, int offset, int length) {
    if(offset<0 || length<0 || offset+length > buffer.length) {
      env.throwException("java.lang.IndexOutOfBoundsException");
    }
  }

  static String pkg_class_name = "java.lang.Package";

  @MJI
  public int getPackages_____3Ljava_lang_Package_2 (MJIEnv env, int objRef) {
    ClassLoaderInfo sysLoader = ClassLoaderInfo.getCurrentSystemClassLoader();
    ClassInfo pkgClass = null; 
    try {
      pkgClass = sysLoader.getInitializedClassInfo(pkg_class_name, env.getThreadInfo());
    } catch (ClinitRequired x){
      env.handleClinitRequest(x.getRequiredClassInfo());
      return MJIEnv.NULL;
    }

    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);
    // Returns all of the Packages defined by this class loader and its ancestors
    Map<String, ClassLoaderInfo> pkgs = cl.getPackages();
    int size = pkgs.size();
    // create an array of type java.lang.Package
    int pkgArr = env.newObjectArray(pkg_class_name, size);

    int i = 0;
    for(String name: cl.getPackages().keySet()) {
      int pkgRef = createPackageObject(env, pkgClass, name, cl);
      // place the object into the array
      env.setReferenceArrayElement(pkgArr, i++, pkgRef);
    }

    return pkgArr;
  }

  @MJI
  public int getPackage__Ljava_lang_String_2__Ljava_lang_Package_2 (MJIEnv env, int objRef, int nameRef) {
    ClassLoaderInfo sysLoader = ClassLoaderInfo.getCurrentSystemClassLoader();

    ClassInfo pkgClass = null; 
    try {
      pkgClass = sysLoader.getInitializedClassInfo(pkg_class_name, env.getThreadInfo());
    } catch (ClinitRequired x){
      env.handleClinitRequest(x.getRequiredClassInfo());
      return MJIEnv.NULL;
    }

    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);
    String pkgName = env.getStringObject(nameRef);
    if(cl.getPackages().get(pkgName)!=null) {
      return createPackageObject(env, pkgClass, pkgName, cl);
    } else {
      return MJIEnv.NULL;
    }
  }

  public static int createPackageObject(MJIEnv env, ClassInfo pkgClass, String pkgName, ClassLoaderInfo cl) {
    int pkgRef = env.newObject(pkgClass);
    ElementInfo ei = env.getModifiableElementInfo(pkgRef);

    ei.setReferenceField("pkgName", env.newString(NativeMethodInfo.CTX, pkgName));
    ei.setReferenceField("loader", cl.getClassLoaderObjectRef());
    // the rest of the fields set to some dummy value
    ei.setReferenceField("specTitle", env.newString(NativeMethodInfo.CTX, "spectitle"));
    ei.setReferenceField("specVersion", env.newString(NativeMethodInfo.CTX, "specversion"));
    ei.setReferenceField("specVendor", env.newString(NativeMethodInfo.CTX, "specvendor"));
    ei.setReferenceField("implTitle", env.newString(NativeMethodInfo.CTX, "impltitle"));
    ei.setReferenceField("implVersion", env.newString(NativeMethodInfo.CTX, "implversion"));
    ei.setReferenceField("sealBase", MJIEnv.NULL);

    return pkgRef;
  }

  @MJI
  public void setDefaultAssertionStatus__Z__V (MJIEnv env, int objRef, boolean enabled) {
    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);
    cl.setDefaultAssertionStatus(enabled);
  }

  @MJI
  public void setPackageAssertionStatus__Ljava_lang_String_2Z__V (MJIEnv env, int objRef, int strRef, boolean enabled) {
    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);
    String pkgName = env.getStringObject(strRef);
    cl.setPackageAssertionStatus(pkgName, enabled);
  }

  @MJI
  public void setClassAssertionStatus__Ljava_lang_String_2Z__V (MJIEnv env, int objRef, int strRef, boolean enabled) {
    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);
    String clsName = env.getStringObject(strRef);
    cl.setClassAssertionStatus(clsName, enabled);
  }

  @MJI
  public void clearAssertionStatus____V (MJIEnv env, int objRef) {
    ClassLoaderInfo cl = env.getClassLoaderInfo(objRef);
    cl.clearAssertionStatus();
  }
}
