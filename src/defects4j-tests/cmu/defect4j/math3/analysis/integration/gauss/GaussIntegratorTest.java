package cmu.defect4j.math3.analysis.integration.gauss;

import gov.nasa.jpf.util.test.TestJPF;
import org.junit.Test;

public class GaussIntegratorTest extends TestJPF {

    private final String[] config = {"+nhandler.delegateUnhandledNative", "+classpath+=${jpf-core}/lib/junit-4.11.jar,lib/commons-math-3.1-SNAPSHOT.jar"};
    public static void main(String[] testMethods){
        runTestsOfThisClass(testMethods);
    }
    @Test(timeout=1000000)
    public void testGetWeights() throws Exception {
        if (verifyNoPropertyViolation(config)) {
               org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorTest object = new org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorTest();
               object.testGetWeights();
        }
    }

    @Test(timeout=1000000)
    public void testGetPoints() throws Exception {
        if (verifyNoPropertyViolation(config)) {
               org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorTest object = new org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorTest();
               object.testGetPoints();
        }
    }

    @Test(timeout=1000000)
    public void testIntegrate() throws Exception {
        if (verifyNoPropertyViolation(config)) {
               org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorTest object = new org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorTest();
               object.testIntegrate();
        }
    }

}