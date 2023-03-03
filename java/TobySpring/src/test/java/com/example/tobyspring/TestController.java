package com.example.tobyspring;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest {

}

public class TestController {
    @FastUnitTest
    void test1() {
        String name = "test";
        assertThat("test").isEqualTo("test");
    }

    @UnitTest
    void metaAnnotationTest() {
        assertThat("test").isEqualTo("test");
    }
}