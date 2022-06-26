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
        this.setUp();
        runMethod();
        return new TestResult();
    }

    private void runMethod() {
        try {
            Class<WasRun> wasRun = WasRun.class;
            Method method = wasRun.getMethod(name);
            method.invoke(this);
            this.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
