package xunit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCaseTest {
    TestResult result;

    @BeforeEach
    void setUp(){
        result = new TestResult();
    }

    @Test
    void testTemplateMethod() {
        WasRun wasRun = new WasRun("testMethod");
        wasRun.run(result);
        assertThat(wasRun.log).isEqualTo("setUp testMethod tearDown ");
    }

    @Test
    void testResult() {
        WasRun wasRun = new WasRun("testMethod");
        wasRun.run(result);
        assertThat("1 run, 0 failed").isEqualTo(result.summary());
    }

    @Test
    void testFailedResult() {
        WasRun wasRun = new WasRun("testBrokenMethod");
        wasRun.run(result);
        assertThat("1 run, 1 failed").isEqualTo(result.summary());
    }

    @Test
    void testFailedResultFormatting() {
        TestResult testResult = new TestResult();
        testResult.testStarted();
        testResult.testFailed();
        assertThat("1 run, 1 failed").isEqualTo(testResult.summary());
    }

    @Test
    void testSuit() {
        TestSuit testSuit = new TestSuit();
        testSuit.add(new WasRun("testMethod"));
        testSuit.add(new WasRun("testBrokenMethod"));
        TestResult result = new TestResult();
        testSuit.run(result);
        assertThat("2 run, 1 failed").isEqualTo(result.summary());
    }
}
