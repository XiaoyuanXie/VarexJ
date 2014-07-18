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

import gov.nasa.jpf.Config;
import gov.nasa.jpf.annotation.MJI;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.fosd.typechef.featureexpr.FeatureExpr;

/**
 * native peer for a regex Matcher
 * this is just a delegatee peer
 */
public class JPF_java_util_regex_Matcher extends NativePeer {

  HashMap<Integer, Matcher> matchers;
 
  public JPF_java_util_regex_Matcher (Config conf) {
    matchers = new HashMap<Integer,Matcher>();
  }

  void putInstance (MJIEnv env, int objref, Matcher matcher) {
    int id = env.getIntField(NativeMethodInfo.CTX,  objref, "id").getValue().intValue();
    matchers.put(id, matcher);
  }

  Matcher getInstance (MJIEnv env, int objref) {
    
    int id = env.getIntField(NativeMethodInfo.CTX,  objref, "id").getValue().intValue();
    return matchers.get(id);
  }
  
  @MJI
  public void register____V (MJIEnv env, int objref) {
	  FeatureExpr ctx = NativeMethodInfo.CTX;
    int patRef = env.getReferenceField(ctx, objref, "pattern").getValue();
    
    int regexRef = env.getReferenceField(ctx, patRef, "regex").getValue();
    String regex = env.getStringObject(regexRef);
    int flags = env.getIntField(NativeMethodInfo.CTX, patRef, "flags").getValue().intValue();
    
    Pattern pat = Pattern.compile(regex, flags);

    int inputRef = env.getReferenceField(ctx, objref, "input").getValue();
    String input = env.getStringObject(inputRef);
    
    Matcher matcher = pat.matcher(input);
    putInstance(env, objref, matcher);
  }
  
  @MJI
  public boolean matches____Z (MJIEnv env, int objref) {
    Matcher matcher = getInstance( env, objref);
    return matcher.matches();
  }
  
  @MJI
  public boolean find____Z (MJIEnv env, int objref) {
	Matcher matcher = getInstance( env, objref);
    return matcher.find();
  }

  @MJI
  public boolean lookingAt____Z(MJIEnv env, int objref) {
    Matcher matcher = getInstance(env, objref);
    return matcher.lookingAt();
  }

  @MJI
  public int start__I__I(MJIEnv env, int objref, int group) {
    Matcher matcher = getInstance(env, objref);
    return matcher.start(group);
  }

  @MJI
  public int end__I__I(MJIEnv env, int objref, int group) {
    Matcher matcher = getInstance(env, objref);
    return matcher.end(group);
  }

  @MJI
  public int regionStart____I(MJIEnv env, int objref) {
    Matcher matcher = getInstance(env, objref);
    return matcher.regionStart();
  }

  @MJI
  public int regionEnd____I(MJIEnv env, int objref) {
    Matcher matcher = getInstance(env, objref);
    return matcher.regionEnd();
  }

  @MJI
  public int region__II__Ljava_util_regex_Matcher_2(MJIEnv env, int objref, int start, int end) {
    Matcher matcher = getInstance(env, objref);
    matcher = matcher.region(start, end);
    putInstance(env, objref, matcher);

    return objref;
  }

  @MJI
  public int reset____Ljava_util_regex_Matcher_2 (MJIEnv env, int objref) {
    Matcher matcher = getInstance( env, objref);
    FeatureExpr ctx = NativeMethodInfo.CTX;
    int inputRef = env.getReferenceField(ctx, objref, "input").getValue();
    String input = env.getStringObject(inputRef);
    
    matcher = matcher.reset(input);
    putInstance(env, objref, matcher);
    
    return objref;
  }
  
  @MJI
  public int groupCount____I (MJIEnv env, int objref) {
    Matcher matcher = getInstance(env, objref);
    return matcher.groupCount();
  }
  
  @MJI
  public int group__I__Ljava_lang_String_2 (MJIEnv env, int objref, int i) {
    Matcher matcher = getInstance( env, objref);
    String grp = matcher.group(i);
    
    return env.newString(NativeMethodInfo.CTX, grp);
  }

  @MJI
  public int quoteReplacement__Ljava_lang_String_2__Ljava_lang_String_2 (MJIEnv env, int clsObjref, int string) {
    String parm = env.getStringObject(string);
    String result = Matcher.quoteReplacement(parm);
    return env.newString(NativeMethodInfo.CTX, result);
  }

  @MJI
  public int replaceAll__Ljava_lang_String_2__Ljava_lang_String_2 (MJIEnv env, int objref, int string) {
    Matcher matcher = getInstance(env, objref);
    String replacement = env.getStringObject(string);
    String result = matcher.replaceAll(replacement);

    int resultref = env.newString(NativeMethodInfo.CTX, result);
    return resultref;
  }
  
  @MJI
  public int replaceFirst__Ljava_lang_String_2__Ljava_lang_String_2(MJIEnv env, int objref, int string) {
    Matcher matcher = getInstance(env, objref);
    String replacement = env.getStringObject(string);
    String result = matcher.replaceFirst(replacement);

    int resultref = env.newString(NativeMethodInfo.CTX, result);
    return resultref;
  }

  @MJI
  public boolean hasTransparentBounds____Z(MJIEnv env, int objref) {
    Matcher matcher = getInstance(env, objref);
    return matcher.hasTransparentBounds();
  }

  @MJI
  public int useTransparentBounds__Z__Ljava_util_regex_Matcher_2(MJIEnv env, int objref, boolean b) {
    Matcher matcher = getInstance(env, objref);
    matcher = matcher.useTransparentBounds(b);
    putInstance(env, objref, matcher);
     
    return objref;
  }

  @MJI
  public boolean hasAnchoringBounds____Z(MJIEnv env, int objref) {
    Matcher matcher = getInstance(env, objref);
    return matcher.hasTransparentBounds();
  }

  @MJI
  public int useAnchoringBounds__Z__Ljava_util_regex_Matcher_2(MJIEnv env, int objref, boolean b) {
    Matcher matcher = getInstance(env, objref);
    matcher = matcher.useAnchoringBounds(b);
    putInstance(env, objref, matcher);

    return objref;
  }

  @MJI
  public int toString____Ljava_lang_String_2 (MJIEnv env, int objref) {
    Matcher matcher = getInstance(env, objref);
    String str = matcher.toString();

    return env.newString(NativeMethodInfo.CTX, str);
  }

  @MJI
  public boolean hitEnd____Z (MJIEnv env, int objref) {
    Matcher matcher = getInstance( env, objref);
    return matcher.hitEnd();
  }

  @MJI
  public boolean requireEnd____Z (MJIEnv env, int objref) {
    Matcher matcher = getInstance( env, objref);
    return matcher.requireEnd();
  }
}
