package xunit;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCaseTest {

    public TestCaseTest() {
    }

    public void testTemplateMethod() {
        WasRun wasRun = new WasRun("testMethod");
        wasRun.run();
        assertThat(wasRun.log).isEqualTo("setUp testMethod ");
    }

}
