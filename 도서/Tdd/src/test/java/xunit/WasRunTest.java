package xunit;

import org.junit.jupiter.api.Test;

class WasRunTest {

    @Test
    void test1() {
        WasRun testMethod = new WasRun("testMethod");
        System.out.println(test.wasRun);
        test.testMethod();
        System.out.println(test.wasRun);
    }
}
