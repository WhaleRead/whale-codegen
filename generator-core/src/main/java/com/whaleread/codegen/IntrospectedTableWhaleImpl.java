package com.whaleread.codegen;

import com.whaleread.codegen.api.GeneratedFile;
import com.whaleread.codegen.api.IntrospectedTable;
import com.whaleread.codegen.api.ProgressCallback;
import com.whaleread.codegen.config.BuiltInGeneratorConfiguration;
import com.whaleread.codegen.generator.AbstractGenerator;
import com.whaleread.codegen.generator.builtin.DTOGenerator;
import com.whaleread.codegen.generator.builtin.JdbcTemplateJavaClientGenerator;
import com.whaleread.codegen.generator.builtin.ModelGenerator;
import com.whaleread.codegen.generator.builtin.SpringCrudServiceGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dolphin on 2018/1/17
 */
public class IntrospectedTableWhaleImpl extends IntrospectedTable {
    protected List<AbstractGenerator> generators;

    public IntrospectedTableWhaleImpl() {
        super();
        generators = new ArrayList<>();
    }

    @Override
    public void calculateGenerators(List<String> warnings, ProgressCallback progressCallback) {
        ModelGenerator modelGenerator = new ModelGenerator();
        initializeAbstractGenerator(modelGenerator, warnings,
                progressCallback);
        generators.add(modelGenerator);
        BuiltInGeneratorConfiguration builtInGeneratorConfiguration = context.getBuiltInGeneratorConfiguration();
        if (builtInGeneratorConfiguration.isEnableDTO()) {
            DTOGenerator dtoGenerator = new DTOGenerator();
            initializeAbstractGenerator(dtoGenerator, warnings, progressCallback);
            generators.add(dtoGenerator);
        }
        if (builtInGeneratorConfiguration.isEnableDAO()) {
            JdbcTemplateJavaClientGenerator jdbcTemplateJavaClientGenerator = new JdbcTemplateJavaClientGenerator();
            initializeAbstractGenerator(jdbcTemplateJavaClientGenerator, warnings, progressCallback);
            generators.add(jdbcTemplateJavaClientGenerator);
        }
        if (builtInGeneratorConfiguration.isEnableService()) {
            SpringCrudServiceGenerator serviceGenerator = new SpringCrudServiceGenerator();
            initializeAbstractGenerator(serviceGenerator, warnings, progressCallback);
            generators.add(serviceGenerator);
        }
    }

    protected void initializeAbstractGenerator(
            AbstractGenerator abstractGenerator, List<String> warnings,
            ProgressCallback progressCallback) {
        if (abstractGenerator == null) {
            return;
        }

        abstractGenerator.setContext(context);
        abstractGenerator.setIntrospectedTable(this);
        abstractGenerator.setProgressCallback(progressCallback);
        abstractGenerator.setWarnings(warnings);
    }

    @Override
    public List<GeneratedFile> getGeneratedFiles() {
        List<GeneratedFile> generatedFiles = new ArrayList<>();
        for (AbstractGenerator generator : generators) {
            generatedFiles.addAll(generator.getGeneratedFiles());
        }
        return generatedFiles;
    }

    @Override
    public boolean isJava5Targeted() {
        return false;
    }

    @Override
    public int getGenerationSteps() {
        return generators.size();
    }
}
