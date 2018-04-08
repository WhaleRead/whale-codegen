package com.whaleread.codegen.generator.builtin;

import com.whaleread.codegen.api.CommentGenerator;
import com.whaleread.codegen.api.FullyQualifiedTable;
import com.whaleread.codegen.api.IntrospectedColumn;
import com.whaleread.codegen.api.Plugin;
import com.whaleread.codegen.api.dom.java.*;
import com.whaleread.codegen.generator.AbstractJavaGenerator;
import com.whaleread.codegen.generator.RootClassInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.whaleread.codegen.internal.util.JavaBeansUtil.*;
import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;
import static com.whaleread.codegen.internal.util.messages.Messages.getString;

/**
 * Created by Dolphin on 2018/1/18
 */
public class ModelGenerator extends AbstractJavaGenerator {
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        if (!introspectedTable.getTableConfiguration().isEnableModel()) {
            return Collections.emptyList();
        }
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString("Progress.8", table.toString())); //$NON-NLS-1$
        Plugin plugins = context.getPlugins();
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getModelType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        FullyQualifiedJavaType superClass = getSuperClass();
        if (superClass != null) {
            topLevelClass.setSuperClass(superClass);
            topLevelClass.addImportedType(superClass);
        }

        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);
        commentGenerator.addClassAnnotation(topLevelClass, introspectedTable, topLevelClass.getImportedTypes());

        List<IntrospectedColumn> introspectedColumns = introspectedTable.getAllColumns();

        generateTableFields(topLevelClass);
        generateColumnNameFields(topLevelClass, introspectedColumns);

        if (introspectedTable.isConstructorBased()) {
            addParameterizedConstructor(topLevelClass);

            if (!introspectedTable.isImmutable()) {
                addDefaultConstructor(topLevelClass);
            }
        }

        String rootClass = getRootClass();

        String alias = introspectedTable.getFullyQualifiedTable().getAlias();
        boolean hasAlias = stringHasValue(alias);
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        StringBuilder aliasSb = null;
        if (hasAlias) {
            aliasSb = new StringBuilder();
            aliasSb.append("\"");
        }

        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            if (RootClassInfo.getInstance(rootClass, warnings)
                    .containsProperty(introspectedColumn)) {
                continue;
            }

            Field field = getJavaBeansField(introspectedColumn, context, introspectedTable);
            commentGenerator.addFieldAnnotation(field, introspectedTable, introspectedColumn, topLevelClass.getImportedTypes());
            if (plugins.modelFieldGenerated(field, topLevelClass,
                    introspectedColumn, introspectedTable,
                    Plugin.ModelClassType.BASE_RECORD)) {
                topLevelClass.addField(field);
                topLevelClass.addImportedType(field.getType());
            }

            Method method = getJavaBeansGetter(introspectedColumn, context, introspectedTable);
            commentGenerator.addGeneralMethodAnnotation(method, introspectedTable, introspectedColumn, topLevelClass.getImportedTypes());
            if (plugins.modelGetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable,
                    Plugin.ModelClassType.BASE_RECORD)) {
                topLevelClass.addMethod(method);
            }

            if (!introspectedTable.isImmutable()) {
                method = getJavaBeansSetter(introspectedColumn, context, introspectedTable);
                commentGenerator.addGeneralMethodAnnotation(method, introspectedTable, introspectedColumn, topLevelClass.getImportedTypes());
                if (plugins.modelSetterMethodGenerated(method, topLevelClass,
                        introspectedColumn, introspectedTable,
                        Plugin.ModelClassType.BASE_RECORD)) {
                    topLevelClass.addMethod(method);
                }
            }

//            if (introspectedColumn.isColumnNameDelimited()) {
//                sb.append(introspectedColumn.getColumnName());
//                if (hasAlias) {
//                    aliasSb.append(alias);
//                    aliasSb.append(".");
//                    aliasSb.append(introspectedColumn.getColumnName());
//                }
//            } else {
//                sb.append(introspectedColumn.getColumnName());
//                if (hasAlias) {
//                    aliasSb.append(alias);
//                    aliasSb.append(".");
//                    aliasSb.append(introspectedColumn.getColumnName());
//                }
//            }
            sb.append(introspectedColumn.getColumnName());
            if (hasAlias) {
                aliasSb.append(alias);
                aliasSb.append(".");
                aliasSb.append(introspectedColumn.getColumnName());
            }
            sb.append(", ");
            if (hasAlias) {
                aliasSb.append(" AS ");
                aliasSb.append(alias);
                aliasSb.append("_");
                aliasSb.append(introspectedColumn.getActualColumnName());
                aliasSb.append(", ");
            }
        }
        sb.setLength(sb.length() - 2);
        if (hasAlias) {
            aliasSb.setLength(aliasSb.length() - 2);
        }
        sb.append("\"");
        if (hasAlias) {
            aliasSb.append("\"");
        }
        Field columnsField = new Field("BASE_COLUMNS", new FullyQualifiedJavaType(String.class.getName()));
        commentGenerator.addGeneratedAnnotation(columnsField, topLevelClass.getImportedTypes());
        publicStaticFinal(columnsField);
        columnsField.setInitializationString(sb.toString());
        topLevelClass.addField(0, columnsField);
        if (hasAlias) {
            columnsField = new Field("ALIASED_BASE_COLUMNS", new FullyQualifiedJavaType(String.class.getName()));
            commentGenerator.addGeneratedAnnotation(columnsField, topLevelClass.getImportedTypes());
            publicStaticFinal(columnsField);
            columnsField.setInitializationString(aliasSb.toString());
            topLevelClass.addField(1, columnsField);
        }

        List<CompilationUnit> answer = new ArrayList<>();
        if (context.getPlugins().modelBaseRecordClassGenerated(topLevelClass,
                introspectedTable)) {
            answer.add(topLevelClass);
        }
        return answer;
    }

    private FullyQualifiedJavaType getSuperClass() {
        FullyQualifiedJavaType superClass;
        String rootClass = getRootClass();
        if (rootClass != null) {
            superClass = new FullyQualifiedJavaType(rootClass);
        } else {
            superClass = null;
        }

        return superClass;
    }

    private void addParameterizedConstructor(TopLevelClass topLevelClass) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.setName(topLevelClass.getType().getShortName());
        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        List<IntrospectedColumn> constructorColumns = introspectedTable
                .getAllColumns();

        for (IntrospectedColumn introspectedColumn : constructorColumns) {
            method.addParameter(new Parameter(introspectedColumn
                    .getFullyQualifiedJavaType(), introspectedColumn
                    .getJavaProperty()));
        }

        StringBuilder sb = new StringBuilder();
        List<IntrospectedColumn> introspectedColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            sb.setLength(0);
            sb.append("this."); //$NON-NLS-1$
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" = "); //$NON-NLS-1$
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(';');
            method.addBodyLine(sb.toString());
        }

        topLevelClass.addMethod(method);
    }

    private void generateColumnNameFields(TopLevelClass topLevelClass, List<IntrospectedColumn> columns) {
        for (IntrospectedColumn introspectedColumn : columns) {
            Field columnNameField = new Field("COLUMN_" + introspectedColumn.getActualColumnName().toUpperCase(), FullyQualifiedJavaType.getStringInstance());
            publicStaticFinal(columnNameField);
            columnNameField.setInitializationString("\"" + introspectedColumn.getColumnName() + "\"");
            context.getCommentGenerator().addGeneratedAnnotation(columnNameField, topLevelClass.getImportedTypes());
            context.getCommentGenerator().addFieldComment(columnNameField,
                    introspectedTable, introspectedColumn);
            topLevelClass.addField(columnNameField);
        }
    }

    private void generateTableFields(TopLevelClass topLevelClass) {
        Field tableNameField = new Field("TABLE_NAME", new FullyQualifiedJavaType(String.class.getName()));
        context.getCommentGenerator().addGeneratedAnnotation(tableNameField, topLevelClass.getImportedTypes());
        publicStaticFinal(tableNameField);
        tableNameField.setInitializationString("\"" + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName() + "\"");
        topLevelClass.addField(tableNameField);
        if (stringHasValue(introspectedTable.getTableConfiguration().getAlias())) {
            Field aliasField = new Field("TABLE_ALIAS", new FullyQualifiedJavaType(String.class.getName()));
            context.getCommentGenerator().addGeneratedAnnotation(aliasField, topLevelClass.getImportedTypes());
            publicStaticFinal(aliasField);
            aliasField.setInitializationString("\"" + introspectedTable.getTableConfiguration().getAlias() + "\"");
            topLevelClass.addField(aliasField);
        }
    }
}
