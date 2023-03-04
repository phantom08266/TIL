package com.example.tobyspring.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @Test
    void beanSameFailTest() {
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        assertThat(bean1.common).isNotSameAs(bean2.common);
    }

    @Test
    void beanSameSuccessTest() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MyConfig.class);
        applicationContext.refresh();

        MyConfig bean = applicationContext.getBean(MyConfig.class);
        Bean1 bean1 = bean.bean1();
        Bean2 bean2 = bean.bean2();

        assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyTest() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();
        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        assertThat(bean1.common).isEqualTo(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        public Common common() {
            if (this.common == null) this.common = new Common();
            return this.common;
        }
    }

    @Configuration(proxyBeanMethods = true)
    static class MyConfig {
        @Bean
        public Common common() {
            return new Common();
        }

        @Bean
        public Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }


    static class Common {

        public void test() {
            System.out.println("test");
        }
    }
}
