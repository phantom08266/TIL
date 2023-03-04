package com.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AutoConfig implements DeferredImportSelector {

    public final ClassLoader classLoader;
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> imports = new ArrayList<>();

        ImportCandidates.load(MyAutoConfiguration.class, classLoader)
                .forEach(imports::add);

        return imports.toArray(String[]::new);
    }

}
