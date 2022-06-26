package xunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WasRun {

    public int wasRun;
    private final String name;

    public WasRun(String name) {
        this.wasRun = 0;
        this.name = name;
    }

    public void testMethod() {
        this.wasRun = 1;
    }

    public void run() {
        getattr();
    }

    private void getattr() {
        try {
            Class<WasRun> wasRun = WasRun.class;
            Method method = wasRun.getMethod(name);
            method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
