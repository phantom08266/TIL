package xunit;

public class TestResult {


    private int runCount;
    private int failureCount;

    public TestResult() {
        this.runCount = 0;
        this.failureCount = 0;
    }

    public void testStarted() {
        this.runCount += 1;
    }

    public void testFailed() {
        this.failureCount += 1;
    }

    public String summary() {
        return String.format("%d run, %d failed", runCount, failureCount);
    }
}
