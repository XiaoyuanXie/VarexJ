package cmu.defect4j.jfree.chart.junit;

import gov.nasa.jpf.util.test.TestJPF;
import org.junit.Test;
import junit.framework.TestCase;
public class AreaChartTests extends TestJPF {

    private final String[] config = {"+nhandler.delegateUnhandledNative", "+classpath+=${jpf-core}/lib/junit-4.11.jar,${jpf-core}/lib/jfreechart-1.2.0-ea1.jar"};

    public static void main(String[] testMethods){
        runTestsOfThisClass(testMethods);
    }
	@Test(timeout=120000)
	public void testDrawWithNullInfo() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.junit.AreaChartTests("testDrawWithNullInfo");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testSetSeriesToolTipGenerator() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.junit.AreaChartTests("testSetSeriesToolTipGenerator");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testSetSeriesURLGenerator() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.junit.AreaChartTests("testSetSeriesURLGenerator");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testReplaceDataset() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.jfree.chart.junit.AreaChartTests("testReplaceDataset");
			testcase.run();
		}
	}

}