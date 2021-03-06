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
package gov.nasa.jpf.vm;

import cmu.conditional.One;
import de.fosd.typechef.featureexpr.FeatureExpr;
import gov.nasa.jpf.JPFException;



/**
 * type, name and attribute information for 'double' fields
 */
public class DoubleFieldInfo extends DoubleSlotFieldInfo {
  double init;

  public DoubleFieldInfo (String name, int modifiers) {
    super(name, "D", modifiers);
  }

  public void setConstantValue(Object constValue){
    if (constValue instanceof Double){
      cv = constValue;
      init = ((Double)constValue).doubleValue();

    } else {
      throw new JPFException("illegal boolean ConstValue=" + constValue);
    }
  }


  public void initialize (FeatureExpr ctx, ElementInfo ei, ThreadInfo ti) {
    ei.getFields().setDoubleValue(ctx, storageOffset, new One<>(init));
  }

  public Class<? extends ChoiceGenerator<?>> getChoiceGeneratorType() {
    return DoubleChoiceGenerator.class;
  }

  public int getStorageSize () {
    return 2;
  }

  public String valueToString (Fields f) {
    double d = f.getDoubleValue(storageOffset).getValue();
    return Double.toString(d);
  }

  public Object getValueObject (Fields f){
    return f.getDoubleValue(storageOffset).getValue();
  }

  public boolean isDoubleField(){
    return true;
  }
  
	@Override
	public boolean isByteField() {
		return true;
	}
	
	@Override
	public boolean isShortField() {
		return true;
	}
	
	@Override
	public boolean isIntField() {
		return true;
	}
	
	@Override
	public boolean isLongField() {
		return true;
	}
	
	@Override
	public boolean isFloatField() {
		return true;
	}

  public boolean isNumericField(){
    return true;
  }

  public boolean isFloatingPointField(){
    return true;
  }
}
