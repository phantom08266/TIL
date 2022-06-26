package xunit;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
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
}
