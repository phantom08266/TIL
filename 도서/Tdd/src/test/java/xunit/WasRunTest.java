package xunit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class WasRunTest {

    @Test
    void test1() {
        WasRun test = new WasRun("testMethod");
        System.out.println(test.wasRun);
        test.run();
        System.out.println(test.wasRun);
    }

    @Test
    void test2() {
        WasRun test = new WasRun("testMethod");
        assertThat(test.wasRun).isZero();
        test.run();
        assertThat(test.wasRun).isNotZero();
    }

    @Test
    void test3() {
        WasRun wasRun = new WasRun("testMethod");
        wasRun.run();
        assertThat(wasRun.wasSetUp).isNotZero();
    }


}
