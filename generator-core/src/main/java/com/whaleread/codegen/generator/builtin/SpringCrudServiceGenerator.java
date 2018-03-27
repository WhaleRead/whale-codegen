package com.whaleread.codegen.generator.builtin;

import com.whaleread.codegen.api.CommentGenerator;
import com.whaleread.codegen.api.IntrospectedColumn;
import com.whaleread.codegen.api.dom.java.*;
import com.whaleread.codegen.generator.AbstractJavaGenerator;
import com.whaleread.codegen.runtime.jdbc.Criteria;

import java.util.Collections;
import java.util.List;

import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;

/**
 * Created by Dolphin on 2018/3/27
 */
public class SpringCrudServiceGenerator extends AbstractJavaGenerator {
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        if (!introspectedTable.getTableConfiguration().isServiceEnabled()) {
            return Collections.emptyList();
        }

        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getServiceType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        commentGenerator.addGeneratedAnnotation(topLevelClass, topLevelClass.getImportedTypes());
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

        topLevelClass.addImportedType("org.springframework.transaction.annotation.Transactional");

        addFindByIdMethod(topLevelClass);
        addCountByCriteriaMethod(topLevelClass);
        addFindByCriteriaMethod(topLevelClass);
        addSaveMethod(topLevelClass);
        addUpdateMethod(topLevelClass);
        addDeleteByIdMethod(topLevelClass);
        return Collections.singletonList(topLevelClass);
    }

    private void addFindByIdMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Method method = new Method("findById");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder repositoryParams = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            topLevelClass.addImportedType(column.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty()));
            if (repositoryParams.length() > 0) {
                repositoryParams.append(", ");
            }
            repositoryParams.append(column.getJavaProperty());
        }
        FullyQualifiedJavaType recordType;
        if (context.getBuiltInGeneratorConfiguration().isDtoEnabled()) {
            recordType = new FullyQualifiedJavaType(introspectedTable.getDtoType());
        } else {
            recordType = new FullyQualifiedJavaType(introspectedTable.getModelType());
        }
        if(context.getBuiltInGeneratorConfiguration().isNonNullEnabled()) {
            topLevelClass.addImportedType("java.util.Optional");
            FullyQualifiedJavaType optionalType = new FullyQualifiedJavaType("java.util.Optional");
            optionalType.addTypeArgument(recordType);
            method.setReturnType(optionalType);
        } else {
            method.setReturnType(recordType);
        }

        topLevelClass.addImportedType(recordType);
        String sb = "return " + introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".selectByPrimaryKey(" + repositoryParams.toString() + ");";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addCountByCriteriaMethod(TopLevelClass topLevelClass) {
        Method method = new Method("countByCriteria");
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        topLevelClass.addImportedType(criteriaType);
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        String sb = "return " + introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".countByCriteria(criteria);";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addFindByCriteriaMethod(TopLevelClass topLevelClass) {
        Method method = new Method("findByCriteria");
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        topLevelClass.addImportedType(criteriaType);
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "offset"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "limit"));
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        FullyQualifiedJavaType returnTypeArgument;
        if (context.getBuiltInGeneratorConfiguration().isDtoEnabled()) {
            returnTypeArgument = new FullyQualifiedJavaType(introspectedTable.getDtoType());
        } else {
            returnTypeArgument = new FullyQualifiedJavaType(introspectedTable.getModelType());
        }
        returnType.addTypeArgument(returnTypeArgument);
        topLevelClass.addImportedType(List.class.getName());
        method.setReturnType(returnType);
        topLevelClass.addImportedType(returnType);
        String sb = "return " + introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".selectByCriteria(criteria, offset, limit);";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addSaveMethod(TopLevelClass topLevelClass) {
        Method method = new Method("save");
        FullyQualifiedJavaType modelType = new FullyQualifiedJavaType(introspectedTable.getModelType());
        topLevelClass.addImportedType(modelType);
        method.addParameter(new Parameter(modelType, "record"));
        method.setVisibility(JavaVisibility.PUBLIC);
        String sb = introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".insertSelective(record);";
        method.addBodyLine(sb);
        if (stringHasValue(introspectedTable.getTableConfiguration().getProperty("transactionManager"))) {
            method.addAnnotation("@Transactional(transactionManager = \"" + introspectedTable.getTableConfiguration().getProperty("transactionManager") + "\")");
        } else {
            method.addAnnotation("@Transactional");
        }
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addUpdateMethod(TopLevelClass topLevelClass) {
        Method method = new Method("update");
        FullyQualifiedJavaType modelType = new FullyQualifiedJavaType(introspectedTable.getModelType());
        topLevelClass.addImportedType(modelType);
        method.addParameter(new Parameter(modelType, "record"));
        method.setVisibility(JavaVisibility.PUBLIC);
        String sb = introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".updateByPrimaryKeySelective(record);";
        method.addBodyLine(sb);
        if (stringHasValue(introspectedTable.getTableConfiguration().getProperty("transactionManager"))) {
            method.addAnnotation("@Transactional(transactionManager = \"" + introspectedTable.getTableConfiguration().getProperty("transactionManager") + "\")");
        } else {
            method.addAnnotation("@Transactional");
        }
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addDeleteByIdMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Method method = new Method("deleteById");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder repositoryParams = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            topLevelClass.addImportedType(column.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty()));
            if (repositoryParams.length() > 0) {
                repositoryParams.append(", ");
            }
            repositoryParams.append(column.getJavaProperty());
        }
        String sb = introspectedTable.getFullyQualifiedTable().getDomainObjectProperty() + context.getBuiltInGeneratorConfiguration().getDaoSuffix() + ".deleteByPrimaryKey(" + repositoryParams.toString() + ");";
        method.addBodyLine(sb);
        if (stringHasValue(introspectedTable.getTableConfiguration().getProperty("transactionManager"))) {
            method.addAnnotation("@Transactional(transactionManager = \"" + introspectedTable.getTableConfiguration().getProperty("transactionManager") + "\")");
        } else {
            method.addAnnotation("@Transactional");
        }
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }
}
