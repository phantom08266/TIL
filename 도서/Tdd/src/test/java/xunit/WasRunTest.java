package xunit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class WasRunTest {

    @Test
    void test1() {
        WasRun test = new WasRun("testMethod");
        System.out.println(test.wasRun);
        test.run(new TestResult());
        System.out.println(test.wasRun);
    }

    @Test
    void test2() {
        WasRun test = new WasRun("testMethod");
        assertThat(test.wasRun).isZero();
        test.run(new TestResult());
        assertThat(test.wasRun).isNotZero();
    }

    @Test
    void test3() {
        WasRun wasRun = new WasRun("testMethod");
        wasRun.run(new TestResult());
        assertThat(wasRun.wasSetUp).isNotZero();
    }


}
