//
// Copyright (C) 2006 United States Government as represented by the
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
package gov.nasa.jpf.jvm.bytecode;

import cmu.conditional.Conditional;
import cmu.conditional.Function;
import cmu.conditional.One;
import de.fosd.typechef.featureexpr.FeatureExpr;
import gov.nasa.jpf.jvm.JVMInstruction;
import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.StackFrame;
import gov.nasa.jpf.vm.ThreadInfo;


/**
 * Convert long to int
 * ..., value => ..., result
 */
public class L2I extends JVMInstruction {

  @Override
  public Conditional<Instruction> execute (FeatureExpr ctx, ThreadInfo ti) {
    StackFrame frame = ti.getModifiableTopFrame();
    
    Conditional<Long> v = frame.popLong(ctx);
    frame.push(ctx, v.mapr(new Function<Long, Conditional<Integer>>() {

 		@Override
 		public Conditional<Integer> apply(Long l) {
 			return new One<>((int)l.longValue());
 		}
     	
 	}));
    
    return getNext(ctx, ti);
  }

  @Override
  public int getByteCode () {
    return 0x88;
  }
  
  @Override
  public void accept(InstructionVisitor insVisitor) {
	  insVisitor.visit(this);
  }
}