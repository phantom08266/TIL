package xunit;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCaseTest {

    public TestCaseTest() {
    }

    public void testTemplateMethod() {
        WasRun wasRun = new WasRun("testMethod");
        wasRun.run();
        assertThat(wasRun.log).isEqualTo("setUp testMethod tearDown ");
    }

    public void testResult() {
        WasRun wasRun = new WasRun("testMethod");
        TestResult result = wasRun.run();
        assertThat("1 run, 0 failed").isEqualTo(result.summary());
    }

    public void testFailedResult() {
        WasRun wasRun = new WasRun("testBrokenMethod");
        TestResult result = wasRun.run();
        assertThat("1 run, 1 failed").isEqualTo(result.summary());
    }

    public void testFailedResultFormatting() {
        TestResult testResult = new TestResult();
        testResult.testStarted();
        testResult.testFailed();
        assertThat("1 run, 1 failed").isEqualTo(testResult.summary());
    }
}
