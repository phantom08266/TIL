package com.example.tobyspring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

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

        assertThatThrownBy( () -> {
            applicationContext.getBean(Config2.class);
        }).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Test
    void 스프링부트가_제공하는_테스트전용_서블릿컨테이너사용하여_conditional_bean_구분() {
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class)
                .run( context -> {
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

    @Configuration
    @Conditional(TrueConditional.class)
    static class Config1 {
        @Bean
        public MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @Conditional(FalseConditional.class)
    static class Config2 {
        @Bean
        public MyBean myBean() {
            return new MyBean();
        }
    }

    static class TrueConditional implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseConditional implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }
}
