package com.whaleread.codegen.generator.builtin;

import com.whaleread.codegen.api.CommentGenerator;
import com.whaleread.codegen.api.IntrospectedColumn;
import com.whaleread.codegen.api.dom.java.CompilationUnit;
import com.whaleread.codegen.api.dom.java.Field;
import com.whaleread.codegen.api.dom.java.FullyQualifiedJavaType;
import com.whaleread.codegen.api.dom.java.JavaVisibility;
import com.whaleread.codegen.api.dom.java.Method;
import com.whaleread.codegen.api.dom.java.Parameter;
import com.whaleread.codegen.api.dom.java.TopLevelClass;
import com.whaleread.codegen.config.TableConfiguration;
import com.whaleread.codegen.generator.AbstractJavaGenerator;
import com.whaleread.codegen.runtime.jdbc.Criteria;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;

/**
 * Created by Dolphin on 2018/3/27
 */
public class SpringCrudServiceGenerator extends AbstractJavaGenerator {
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        if (!introspectedTable.getTableConfiguration().isEnableService()) {
            return Collections.emptyList();
        }

        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getServiceType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        commentGenerator.addGeneratedAnnotation(topLevelClass, topLevelClass.getImportedTypes());
        topLevelClass.addImportedType("org.springframework.transaction.annotation.Transactional");
        if (stringHasValue(context.getBuiltInGeneratorConfiguration().getProperty("transactionManager"))) {
            topLevelClass.addAnnotation("@Transactional(readOnly = true, transactionManager = \"" + context.getBuiltInGeneratorConfiguration().getProperty("transactionManager") + "\")");
        } else {
            topLevelClass.addAnnotation("@Transactional(readOnly = true)");
        }
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.addImportedType(introspectedTable.getModelType());
        commentGenerator.addJavaFileComment(topLevelClass);
        commentGenerator.addClassComment(topLevelClass, introspectedTable);
        topLevelClass.addImportedType("org.springframework.stereotype.Service");
        topLevelClass.addAnnotation("@Service");
        FullyQualifiedJavaType repositoryClass = new FullyQualifiedJavaType(introspectedTable.getDaoType());
        topLevelClass.addImportedType(repositoryClass);

        Field repositoryField = new Field(introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix(), repositoryClass);
        repositoryField.setVisibility(JavaVisibility.PRIVATE);
        repositoryField.addAnnotation("@Autowired");
        commentGenerator.addGeneratedAnnotation(repositoryField, topLevelClass.getImportedTypes());
        topLevelClass.addImportedType("org.springframework.beans.factory.annotation.Autowired");
        topLevelClass.addField(repositoryField);

        TableConfiguration tc = introspectedTable.getTableConfiguration();
        if (tc.isEnableSelectByPrimaryKey()) {
            addFindByIdMethod(topLevelClass);
        }
        if (tc.isEnableCountByCriteria()) {
            addCountByCriteriaMethod(topLevelClass);
        }
        if (tc.isEnableSelectByCriteria()) {
            addFindByCriteriaMethod(topLevelClass);
        }
        if (tc.isEnableInsertSelective()) {
            addSaveMethod(topLevelClass);
        }
        if (tc.isEnableUpdateByPrimaryKeySelective()) {
            addUpdateMethod(topLevelClass);
        }
        if (tc.isEnableDeleteByPrimaryKey()) {
            addDeleteByIdMethod(topLevelClass);
        }
        if (tc.isEnableDeleteByCriteria()) {
            addDeleteByCriteriaMethod(topLevelClass);
        }
        return Collections.singletonList(topLevelClass);
    }

    private void addFindByIdMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        Method method = new Method("findById");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder repositoryParams = new StringBuilder();
        preparePrimaryKeyArgs(importedTypes, method, repositoryParams);
        FullyQualifiedJavaType recordType;
//        if (introspectedTable.getTableConfiguration().isEnableDTO()) {
//            recordType = new FullyQualifiedJavaType(introspectedTable.getDtoType());
//        } else {
        recordType = new FullyQualifiedJavaType(introspectedTable.getModelType());
//        }
        if (context.getBuiltInGeneratorConfiguration().isEnableNonNull()) {
            importedTypes.add(new FullyQualifiedJavaType("java.util.Optional"));
            FullyQualifiedJavaType optionalType = new FullyQualifiedJavaType("java.util.Optional");
            optionalType.addTypeArgument(recordType);
            method.setReturnType(optionalType);
        } else {
            method.setReturnType(recordType);
        }

        importedTypes.add(recordType);
        String sb = "return " + introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".selectByPrimaryKey(" + repositoryParams.toString() + ");";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().serviceFindByIdMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void preparePrimaryKeyArgs(Set<FullyQualifiedJavaType> importedTypes, Method method, StringBuilder repositoryParams) {
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            importedTypes.add(column.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty()));
            if (repositoryParams.length() > 0) {
                repositoryParams.append(", ");
            }
            repositoryParams.append(column.getJavaProperty());
        }
    }

    private void addCountByCriteriaMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        Method method = new Method("countByCriteria");
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        importedTypes.add(criteriaType);
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        String sb = "return " + introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".countByCriteria(criteria);";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().serviceCountByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addFindByCriteriaMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        Method method = new Method("findByCriteria");
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        importedTypes.add(criteriaType);
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "offset"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "limit"));
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        FullyQualifiedJavaType returnTypeArgument;
//        if (introspectedTable.getTableConfiguration().isEnableDTO()) {
//            returnTypeArgument = new FullyQualifiedJavaType(introspectedTable.getDtoType());
//        } else {
        returnTypeArgument = new FullyQualifiedJavaType(introspectedTable.getModelType());
//        }
        returnType.addTypeArgument(returnTypeArgument);
        importedTypes.add(new FullyQualifiedJavaType(List.class.getName()));
        method.setReturnType(returnType);
        importedTypes.add(returnType);
        String sb = "return " + introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".selectByCriteria(criteria, offset, limit);";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().serviceFindByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addSaveMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        Method method = new Method("save");
        FullyQualifiedJavaType modelType = new FullyQualifiedJavaType(introspectedTable.getModelType());
        importedTypes.add(modelType);
        method.addParameter(new Parameter(modelType, "record"));
        method.setVisibility(JavaVisibility.PUBLIC);
        String sb = introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".insertSelective(record);";
        method.addBodyLine(sb);
        if (stringHasValue(context.getBuiltInGeneratorConfiguration().getProperty("transactionManager"))) {
            method.addAnnotation("@Transactional(readOnly = false, transactionManager = \"" + context.getBuiltInGeneratorConfiguration().getProperty("transactionManager") + "\")");
        } else {
            method.addAnnotation("@Transactional(readOnly = false)");
        }
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().serviceSaveMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addUpdateMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        Method method = new Method("update");
        FullyQualifiedJavaType modelType = new FullyQualifiedJavaType(introspectedTable.getModelType());
        importedTypes.add(modelType);
        method.addParameter(new Parameter(modelType, "record"));
        method.setVisibility(JavaVisibility.PUBLIC);
        String sb = introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".updateByPrimaryKeySelective(record);";
        method.addBodyLine(sb);
        if (stringHasValue(context.getBuiltInGeneratorConfiguration().getProperty("transactionManager"))) {
            method.addAnnotation("@Transactional(readOnly = false, transactionManager = \"" + context.getBuiltInGeneratorConfiguration().getProperty("transactionManager") + "\")");
        } else {
            method.addAnnotation("@Transactional(readOnly = false)");
        }
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().serviceUpdateMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addDeleteByIdMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        Method method = new Method("deleteById");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder repositoryParams = new StringBuilder();
        preparePrimaryKeyArgs(importedTypes, method, repositoryParams);
        String sb = introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".deleteByPrimaryKey(" + repositoryParams.toString() + ");";
        method.addBodyLine(sb);
        if (stringHasValue(context.getBuiltInGeneratorConfiguration().getProperty("transactionManager"))) {
            method.addAnnotation("@Transactional(readOnly = false, transactionManager = \"" + context.getBuiltInGeneratorConfiguration().getProperty("transactionManager") + "\")");
        } else {
            method.addAnnotation("@Transactional(readOnly = false)");
        }
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().serviceDeleteByIdMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addDeleteByCriteriaMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        Method method = new Method("deleteByCriteria");
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        importedTypes.add(criteriaType);
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.setVisibility(JavaVisibility.PUBLIC);
        String sb = introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".deleteByCriteria(criteria);";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().serviceDeleteByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }
}
