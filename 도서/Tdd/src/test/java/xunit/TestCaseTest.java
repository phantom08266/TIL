package xunit;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCaseTest {

    private WasRun wasRun;

    public TestCaseTest() {
    }

    public void setUp() {
        wasRun = new WasRun("testMethod");
    }

    public void testTemplateMethod() {
        this.wasRun.run();
        assertThat(wasRun.log).isEqualTo("setUp testMethod ");
    }

}
