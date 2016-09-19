package cmu.defect4j.jfree.chart.plot.junit;

import gov.nasa.jpf.util.test.TestJPF;
import org.junit.Test;
import junit.framework.TestCase;
public class CombinedRangeCategoryPlotTests extends TestJPF {

    private final String[] config = {"+nhandler.delegateUnhandledNative", "+classpath+=${jpf-core}/lib/junit-4.11.jar,${jpf-core}/lib/jfreechart-1.2.0-ea1.jar"};

    public static void main(String[] testMethods){
        runTestsOfThisClass(testMethods);
    }
	@Test(timeout=120000)
	public void testEquals() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.plot.junit.CombinedRangeCategoryPlotTests("testEquals");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testCloning() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.plot.junit.CombinedRangeCategoryPlotTests("testCloning");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testSerialization() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.plot.junit.CombinedRangeCategoryPlotTests("testSerialization");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testRemoveSubplot() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.plot.junit.CombinedRangeCategoryPlotTests("testRemoveSubplot");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testNotification() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.plot.junit.CombinedRangeCategoryPlotTests("testNotification");
			testcase.run();
		}
	}

}