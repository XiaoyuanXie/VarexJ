package cmu.defect4j.joda.time.tz;

import gov.nasa.jpf.util.test.TestJPF;
import org.junit.Test;
import junit.framework.TestCase;
public class TestUTCProvider extends TestJPF {

    private final String[] config = {"+nhandler.delegateUnhandledNative", "+classpath+=${jpf-core}/lib/junit-3.8.2.jar,lib/joda-convert-1.2.jar,jodatime/target/classes/,jodatime/target/test-classes,jodatime/"};

    public static void main(String[] testMethods){
        runTestsOfThisClass(testMethods);
    }
	@Test(timeout=120000)
	public void testClass() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.joda.time.tz.TestUTCProvider("testClass");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testGetZone_String() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.joda.time.tz.TestUTCProvider("testGetZone_String");
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testGetAvailableIDs() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new org.joda.time.tz.TestUTCProvider("testGetAvailableIDs");
			testcase.run();
		}
	}

}