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
import cmu.vatrace.LocalStoreStatement;
import de.fosd.typechef.featureexpr.FeatureExpr;
import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.LocalVarInfo;
import gov.nasa.jpf.vm.StackFrame;
import gov.nasa.jpf.vm.ThreadInfo;


/**
 * Store int into local variable
 * ..., value => ...
 */
public class ISTORE extends LocalVariableInstruction implements StoreInstruction {

  public ISTORE(int localVarIndex){
    super(localVarIndex);
  }

  @Override
  public Conditional<Instruction> execute (FeatureExpr ctx, ThreadInfo ti) {
    StackFrame frame = ti.getModifiableTopFrame();
    
    
    LocalVarInfo localVarInfo = frame.getLocalVarInfo(index, ctx);
    Conditional<Integer> oldValue = null;
    if (localVarInfo != null) {
		int startPC = localVarInfo.getStartPC();
		int pos = getPosition();
		if (pos == startPC - 1) {// init
			oldValue = null;
		} else if (getMethodInfo().getInstructionAt(startPC).getLineNumber() >= this.getLineNumber()) {
			oldValue = null;// TODO this is not clean
		} else {
			oldValue = frame.getLocalVariable(frame.stack.getCtx(), index);
		}
    }
    frame.storeOperand(ctx, index);
    if (localVarInfo != null) {
		Conditional<Integer> newValue = frame.getLocalVariable(frame.stack.getCtx(), index);
		FeatureExpr returnContext = getReturnContext(frame);
		newValue = newValue.simplify(returnContext.not());
		new LocalStoreStatement(this, frame.method, oldValue, newValue, localVarInfo, ctx);
	}
    return getNext(ctx, ti);

  }

  public int getLength() {
    if (index > 3){
      return 2; // bytecode, index
    } else {
      return 1;
    }
  }
  
  @Override
  public int getByteCode () {
    switch (index) {
    case 0: return 0x3b;
    case 1: return 0x3c;
    case 2: return 0x3d;
    case 3: return 0x3e;
    }
    return 0x36; // ?? wide, ISTORE_n
  }
  
  public String getBaseMnemonic() {
    return "istore";
  }
  
  @Override
  public void accept(InstructionVisitor insVisitor) {
	  insVisitor.visit(this);
  }
}
