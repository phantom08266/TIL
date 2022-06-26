package xunit;

public class TestResult {


    private int runCount;

    public TestResult() {
        this.runCount = 1;
    }

    public String summary() {
        return String.format("%d run, 0 failed", runCount);
    }
}
