package xunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WasRun extends TestCase {

    public int wasRun;
    public int wasSetUp;

    public WasRun(String name) {
        super(name);
    }

    @Override
    public void setUp() {
        this.wasRun = 0;
        this.wasSetUp = 1;
    }

    public void testMethod() {
        this.wasRun = 1;
    }
}
