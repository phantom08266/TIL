package xunit;

import java.lang.reflect.Method;

public abstract class TestCase {
    protected String name;

    public abstract void setUp();

    public TestCase(String name) {
        this.name = name;
    }

    public void run() {
        this.setUp();
        runMethod();
    }

    private void runMethod() {
        try {
            Class<WasRun> wasRun = WasRun.class;
            Method method = wasRun.getMethod(name);
            method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
