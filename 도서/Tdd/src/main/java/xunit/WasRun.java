package xunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WasRun extends TestCase {

    public int wasRun;
    public int wasSetUp;
    public String log;

    public WasRun(String name) {
        super(name);
    }

    @Override
    public void setUp() {
        this.wasRun = 0;
        this.wasSetUp = 1;
        this.log = "setUp ";
    }

    public void testMethod() {
        this.wasRun = 1;
    }
}
