package xunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WasRun extends TestCase{

    public int wasRun;

    public WasRun(String name) {
        super(name);
        this.wasRun = 0;
    }

    public void testMethod() {
        this.wasRun = 1;
    }

    public void run() {
        runMethod();
    }

    private void runMethod() {
        try {
            Class<WasRun> wasRun = WasRun.class;
            Method method = wasRun.getMethod(name);
            method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
