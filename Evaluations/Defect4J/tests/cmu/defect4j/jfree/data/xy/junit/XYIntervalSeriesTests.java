package cmu.defect4j.jfree.data.xy.junit;

import gov.nasa.jpf.util.test.TestJPF;
import org.junit.Test;
import junit.framework.TestCase;
public class XYIntervalSeriesTests extends TestJPF {

    private final String[] config = {"+nhandler.delegateUnhandledNative", "+classpath+=${jpf-core}/lib/junit-4.11.jar,${jpf-core}/lib/jfreechart-1.2.0-ea1.jar"};

    public static void main(String[] testMethods){
        runTestsOfThisClass(testMethods);
    }
	@Test(timeout=120000)
	public void testSetMaximumItemCount2() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testSetMaximumItemCount2");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testEquals() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testEquals");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testCloning() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testCloning");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testSerialization() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testSerialization");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testAdd() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testAdd");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testIndexOf2() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testIndexOf2");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testAdditionOfDuplicateXValues() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testAdditionOfDuplicateXValues");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testRemove() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testRemove");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testSetMaximumItemCount() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testSetMaximumItemCount");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testClear() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testClear");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testValues() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testValues");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testIndexOf() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.data.xy.junit.XYIntervalSeriesTests("testIndexOf");
			testcase.run();
		}
	}

}