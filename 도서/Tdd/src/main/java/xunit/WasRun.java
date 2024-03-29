package xunit;

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

    @Override
    public void tearDown() {
        this.log = this.log + "tearDown ";
    }

    public void testMethod() {
        this.wasRun = 1;
        this.log = this.log + "testMethod ";
    }

    public void testBrokenMethod() {
        throw new RuntimeException();
    }
}
