package com.example.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class AutoConfig implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[] {
                "com.example.config.DispatcherServletConfig",
                "com.example.config.TomcatServletWebServerConfig"
        };
    }

}
