package xunit;

import java.lang.reflect.Method;

public abstract class TestCase {
    protected String name;

    public abstract void setUp();
    public abstract void tearDown();

    public TestCase(String name) {
        this.name = name;
    }

    public TestResult run() {
        TestResult testResult = new TestResult();
        testResult.testStarted();
        this.setUp();
        runMethod(testResult);
        return testResult;
    }

    private void runMethod(TestResult result) {
        try {
            Class<WasRun> wasRun = WasRun.class;
            Method method = wasRun.getMethod(name);
            method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
            result.testFailed();
        } finally {
            this.tearDown();
        }
    }
}
