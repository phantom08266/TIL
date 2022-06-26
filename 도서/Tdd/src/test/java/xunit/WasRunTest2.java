package xunit;

import org.junit.jupiter.api.Test;

class WasRunTest2 {

    @Test
    void test1() {
        TestCaseTest test = new TestCaseTest();
        test.testTemplateMethod();
    }

    @Test
    void test2() {
        TestCaseTest test = new TestCaseTest();
        test.testResult();
    }

    @Test
    void test3() {
        TestCaseTest test = new TestCaseTest();
        test.testFailedResultFormatting();
    }
}
