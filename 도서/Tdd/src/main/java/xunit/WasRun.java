package xunit;

public class WasRun {
    public int wasRun;
    public WasRun(String name) {
        this.wasRun = 0;
    }

    public void testMethod() {
        this.wasRun = 1;
    }
}
