package com.example.tobyspring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {

    @Test
    void conditional설정값인_matches의_반환값이_true인경우_성공한다() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config1.class);
        applicationContext.refresh();

        Config1 bean = applicationContext.getBean(Config1.class);
        assertThat(bean).isNotNull();
    }

    @Test
    void conditional설정값인_matches의_반환값이_false인경우_에러발생() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config2.class);
        applicationContext.refresh();

        assertThatThrownBy(() -> {
            applicationContext.getBean(Config2.class);
        }).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Test
    void 스프링부트가_제공하는_테스트전용_서블릿컨테이너사용하여_conditional_bean_구분() {
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(Config1.class);
                    assertThat(context).hasSingleBean(MyBean.class);
                });

        ApplicationContextRunner falseContextRunner = new ApplicationContextRunner();
        falseContextRunner.withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(Config2.class);
                    assertThat(context).doesNotHaveBean(MyBean.class);
                });
    }

    static class MyBean {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional {
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional {

    }

    @Configuration
//    @TrueConditional
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        public MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
//    @FalseConditional
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        public MyBean myBean() {
            return new MyBean();
        }
    }

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // BooleanConditional애노테이션응 사용하는 클래스 이름들의 애노테이션 boolean value값을 가져온다.
            Map<String, Object> annotationAttributes =
                    metadata.getAnnotationAttributes(BooleanConditional.class.getName());

            boolean value = (boolean) annotationAttributes.get("value");
            return value;
        }
    }
}
