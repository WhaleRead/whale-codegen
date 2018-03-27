package com.whaleread.codegen.generator.builtin;

import com.whaleread.codegen.api.CommentGenerator;
import com.whaleread.codegen.api.FullyQualifiedTable;
import com.whaleread.codegen.api.IntrospectedColumn;
import com.whaleread.codegen.api.Plugin;
import com.whaleread.codegen.api.dom.java.*;
import com.whaleread.codegen.config.TableConfiguration;
import com.whaleread.codegen.generator.AbstractJavaGenerator;
import com.whaleread.codegen.internal.util.StringUtility;
import com.whaleread.codegen.runtime.jdbc.Criteria;
import com.whaleread.codegen.runtime.jdbc.spring.AliasBeanPropertyRowMapper;

import javax.annotation.Generated;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.whaleread.codegen.internal.util.JavaBeansUtil.getGetterMethodName;
import static com.whaleread.codegen.internal.util.JavaBeansUtil.getSetterMethodName;
import static com.whaleread.codegen.internal.util.JavaBeansUtil.privateStaticFinal;
import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;
import static com.whaleread.codegen.internal.util.messages.Messages.getString;

/**
 * Created by Dolphin on 2018/3/23
 */
public class JdbcTemplateJavaClientGenerator extends AbstractJavaGenerator {
    private static final String ROW_MAPPER_TYPE_NAME = "org.springframework.jdbc.core.RowMapper";

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString("Progress.8", table.toString())); //$NON-NLS-1$
        Plugin plugins = context.getPlugins();
        // TODO: plugins
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getDaoType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        commentGenerator.addClassAnnotation(topLevelClass, introspectedTable, topLevelClass.getImportedTypes());
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getModelType()));
        commentGenerator.addJavaFileComment(topLevelClass);
        commentGenerator.addClassComment(topLevelClass, introspectedTable);
        topLevelClass.addImportedType("org.springframework.stereotype.Repository");
        topLevelClass.addAnnotation("@Repository");
        FullyQualifiedJavaType superClass = new FullyQualifiedJavaType("org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport");
        topLevelClass.addImportedType(superClass);
        topLevelClass.setSuperClass(superClass);
        topLevelClass.addImportedType(new FullyQualifiedJavaType(Generated.class.getName()));

        TableConfiguration tableConfig = introspectedTable.getTableConfiguration();

        if (tableConfig.isDtoEnabled()) {
            topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getDtoType()));
        }

        addRowMapperField(topLevelClass);
        addInjectMethod(topLevelClass);

        if (tableConfig.isSelectByPrimaryKeyStatementEnabled()) {
            addSelectByPrimaryKeyMethod(topLevelClass);
        }
        if (tableConfig.isSelectByCriteriaStatementEnabled()) {
            addSelectByCriteriaMethod(topLevelClass);
        }

        if (tableConfig.isInsertStatementEnabled()) {
            addInsertMethod(topLevelClass);
        }
        if (tableConfig.isInsertSelectiveStatementEnabled()) {
            addInsertSelectiveMethod(topLevelClass);
        }

        if (tableConfig.isUpdateByPrimaryKeyStatementEnabled()) {
            addUpdateByPrimaryKeySelectiveMethod(topLevelClass);
        }

        if (tableConfig.isDeleteByPrimaryKeyStatementEnabled()) {
            addDeleteByPrimaryKeyMethod(topLevelClass);
        }
        if (tableConfig.isDeleteByCriteriaStatementEnabled()) {
            addDeleteByCriteriaMethod(topLevelClass);
        }

        return Collections.singletonList(topLevelClass);
    }

    private void addRowMapperField(TopLevelClass topLevelClass) {
        FullyQualifiedJavaType rowMapperType = new FullyQualifiedJavaType(ROW_MAPPER_TYPE_NAME);
        String paramTypeName;
        if (introspectedTable.getTableConfiguration().isDtoEnabled()) {
            paramTypeName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "DTO";
            rowMapperType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getDtoType()));
        } else {
            paramTypeName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            rowMapperType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getModelType()));
        }
        topLevelClass.addImportedType(ROW_MAPPER_TYPE_NAME);
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(rowMapperType);
        field.setName("rowMapper");
        if (stringHasValue(introspectedTable.getFullyQualifiedTable().getAlias())) {
            topLevelClass.addImportedType(AliasBeanPropertyRowMapper.class.getName());
            field.setInitializationString("new AliasBeanPropertyRowMapper<>(" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ".TABLE_ALIAS, " + paramTypeName + ".class)");
        } else {
            topLevelClass.addImportedType("org.springframework.jdbc.core.BeanPropertyRowMapper");
            field.setInitializationString("new BeanPropertyRowMapper<>(" + paramTypeName + ".class)");
        }
        context.getCommentGenerator().addGeneratedAnnotation(field, topLevelClass.getImportedTypes());
        topLevelClass.addField(field);
    }

    private void addInjectMethod(TopLevelClass topLevelClass) {
        FullyQualifiedJavaType injectedType = new FullyQualifiedJavaType(DataSource.class.getName());
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        topLevelClass.addImportedType(injectedType);
        Method method = new Method("inject");
        method.addAnnotation("@Autowired");
        method.setVisibility(JavaVisibility.PUBLIC);
        Parameter parameter = new Parameter(injectedType, "dataSource");
        method.addParameter(parameter);
        if (stringHasValue(introspectedTable.getTableConfiguration().getProperty("dataSource"))) {
            topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Qualifier"));
            parameter.addAnnotation("@Qualifier(\"" + introspectedTable.getTableConfiguration().getProperty("dataSource") + "\")");
        }
        method.addBodyLine("super.setDataSource(dataSource);");
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addSelectByPrimaryKeyMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Method method = new Method("selectByPrimaryKey");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder whereFragment = new StringBuilder();
        StringBuilder valueFragment = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            topLevelClass.addImportedType(column.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty()));
            whereFragment.append(column.getColumnName()).append(" = ? AND ");
            valueFragment.append(column.getJavaProperty()).append(", ");
        }
        whereFragment.setLength(whereFragment.length() - 4);
        valueFragment.setLength(valueFragment.length() - 2);
        String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        boolean nonNullEnabled = context.getBuiltInGeneratorConfiguration().isNonNullEnabled();
        FullyQualifiedJavaType returnType;
        if (introspectedTable.getTableConfiguration().isDtoEnabled()) {
            returnType = new FullyQualifiedJavaType(introspectedTable.getDtoType());
        } else {
            returnType = new FullyQualifiedJavaType(introspectedTable.getModelType());
        }
        if(nonNullEnabled) {
            topLevelClass.addImportedType("java.util.Optional");
            FullyQualifiedJavaType optionalType = new FullyQualifiedJavaType("java.util.Optional");
            optionalType.addTypeArgument(returnType);
            method.setReturnType(optionalType);
        } else {
            method.setReturnType(returnType);
        }
        String sb = "return getJdbcTemplate().query(\"SELECT \" + " +
                domainObjectName +
                ".BASE_COLUMNS + \" FROM \" + " +
                domainObjectName +
                ".TABLE_NAME + \" WHERE " +
                whereFragment +
                "\", new Object[]{" +
                valueFragment +
                "}, rs -> rs.next() ? " + (nonNullEnabled ? "Optional.of(rowMapper.mapRow(rs, 0)) : Optional.empty());" : "rowMapper.mapRow(rs, 0) : null);");
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        topLevelClass.addImportedType(Map.class.getName());
        topLevelClass.addImportedType(HashMap.class.getName());
        Method method = new Method("updateByPrimaryKeySelective");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getModelType()), "record"));
        method.addBodyLine("StringBuilder fragment = new StringBuilder();");
        method.addBodyLine("Map<String, Object> params = new HashMap<>();");
        StringBuilder whereFragment = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            method.addBodyLine("params.put(\"" + column.getJavaProperty() + "\", record." + getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType()) + "());");
            whereFragment.append(column.getColumnName()).append(" = :").append(column.getJavaProperty()).append(" AND ");
        }
        whereFragment.setLength(whereFragment.length() - 4);
        for (IntrospectedColumn column : introspectedTable.getBaseColumns()) {
            String getterMethodName = getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType());
            method.addBodyLine("if (record." + getterMethodName + "() != null) {");
            method.addBodyLine("fragment.append(\"" + column.getColumnName() + " = :" + column.getJavaProperty() + ", \");");
            method.addBodyLine("params.put(\"" + column.getJavaProperty() + "\", record." + getterMethodName + "());");
            method.addBodyLine("}");
        }
        method.addBodyLine("if (fragment.length() == 0) {");
        method.addBodyLine("return;");
        method.addBodyLine("}");
        method.addBodyLine("fragment.setLength(fragment.length() - 2);");
        method.addBodyLine("getNamedParameterJdbcTemplate().update(\"UPDATE \" + " + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ".TABLE_NAME + \" SET \" + fragment + \" WHERE " + whereFragment + "\", params);");
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addInsertSelectiveMethod(TopLevelClass topLevelClass) {
        Method method = new Method("insertSelective");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getModelType()), "record"));
        method.addBodyLine("Map<String, Object> params = new HashMap<>();");
        method.addBodyLine("StringBuilder columnsFragment = new StringBuilder();");
        method.addBodyLine("StringBuilder valuesFragment = new StringBuilder();");
        IntrospectedColumn autoIncrementColumn = null;
        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            if (column.isAutoIncrement()) {
                autoIncrementColumn = column;
                continue;
            }
            String getterMethodName = getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType());
            method.addBodyLine("if (record." + getterMethodName + "() != null) {");
            method.addBodyLine("columnsFragment.append(\"" + column.getColumnName() + ", \");");
            method.addBodyLine("valuesFragment.append(\":" + column.getJavaProperty() + ", \");");
            method.addBodyLine("params.put(\"" + column.getJavaProperty() + "\", record." + getterMethodName + "());");
            method.addBodyLine("}");
        }
        method.addBodyLine("if (columnsFragment.length() > 0) {");
        method.addBodyLine("columnsFragment.setLength(columnsFragment.length() - 2);");
        method.addBodyLine("valuesFragment.setLength(valuesFragment.length() - 2);");
        method.addBodyLine("}");
        StringBuilder sb = new StringBuilder();
        sb.append("getNamedParameterJdbcTemplate().update(\"INSERT INTO \" + ").append(introspectedTable.getFullyQualifiedTable().getDomainObjectName()).append(".TABLE_NAME + \" (\" + columnsFragment + \") VALUES (\" + valuesFragment + \")\", new MapSqlParameterSource(params)");
        if (autoIncrementColumn != null) {
            topLevelClass.addImportedType("org.springframework.jdbc.support.KeyHolder");
            topLevelClass.addImportedType("org.springframework.jdbc.support.GeneratedKeyHolder");
//            method.setReturnType(autoIncrementColumn.getFullyQualifiedJavaType());
            method.addBodyLine("KeyHolder keyHolder = new GeneratedKeyHolder();");
            sb.append(", keyHolder);");
            method.addBodyLine(sb.toString());
//            method.addBodyLine("return keyHolder.getKey()." + (autoIncrementColumn.getFullyQualifiedJavaType().getShortName().equals("Long") ? "long" : "int") + "Value();");
            method.addBodyLine("record." + getSetterMethodName(autoIncrementColumn.getJavaProperty()) + "(keyHolder.getKey()." + (autoIncrementColumn.getFullyQualifiedJavaType().getShortName().equals("Long") ? "long" : "int") + "Value()" + ");");
        } else {
            sb.append(");");
            method.addBodyLine(sb.toString());
        }
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addImportedType("org.springframework.jdbc.core.namedparam.MapSqlParameterSource");
        topLevelClass.addMethod(method);
    }

    private void addInsertMethod(TopLevelClass topLevelClass) {
        StringBuilder columnsFragment = new StringBuilder();
        StringBuilder valuesFragment = new StringBuilder();
        Method method = new Method("insert");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getModelType()), "record"));
        method.addBodyLine("Map<String, Object> params = new HashMap<>();");
        IntrospectedColumn autoIncrementColumn = null;
        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            if (column.isAutoIncrement()) {
                autoIncrementColumn = column;
                continue;
            }
            if (column.getActualColumnName().equals("gmt_create") || column.getActualColumnName().equals("gmt_modify")) {
                // skip gmt fields
                continue;
            }
            columnsFragment.append(column.getColumnName()).append(", ");
            valuesFragment.append(":").append(column.getJavaProperty()).append(", ");
            String getterMethodName = getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType());
//            method.addBodyLine("if (record." + getterMethodName + "() != null) {");
//            method.addBodyLine("columnsFragment.append(\"" + column.getActualColumnName() + ", \");");
//            method.addBodyLine("valuesFragment.append(\":" + column.getJavaProperty() + ", \");");
            method.addBodyLine("params.put(\"" + column.getJavaProperty() + "\", record." + getterMethodName + "());");
//            method.addBodyLine("}");
        }
        if (columnsFragment.length() == 0) {
            // there is no columns for insert
            return;
        }
        if (columnsFragment.length() > 0) {
            columnsFragment.setLength(columnsFragment.length() - 2);
            valuesFragment.setLength(valuesFragment.length() - 2);
        }
        String insertSql = "\"INSERT INTO \" + " + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ".TABLE_NAME + \"(" +
                columnsFragment + ") VALUES (" + valuesFragment + ")\"";
        Field insertSqlField = new Field("INSERT_SQL", FullyQualifiedJavaType.getStringInstance());
        insertSqlField.setInitializationString(insertSql);
        privateStaticFinal(insertSqlField);
        context.getCommentGenerator().addGeneratedAnnotation(insertSqlField, topLevelClass.getImportedTypes());
        topLevelClass.addField(insertSqlField);
//        method.addBodyLine("if (columnsFragment.length() > 0) {");
//        method.addBodyLine("columnsFragment.setLength(columnsFragment.length() - 2);");
//        method.addBodyLine("valuesFragment.setLength(valuesFragment.length() - 2);");
//        method.addBodyLine("}");
        StringBuilder sb = new StringBuilder();
        sb.append("getNamedParameterJdbcTemplate().update(INSERT_SQL, new MapSqlParameterSource(params)");
        if (autoIncrementColumn != null) {
            topLevelClass.addImportedType("org.springframework.jdbc.support.KeyHolder");
            topLevelClass.addImportedType("org.springframework.jdbc.support.GeneratedKeyHolder");
//            method.setReturnType(autoIncrementColumn.getFullyQualifiedJavaType());
            method.addBodyLine("KeyHolder keyHolder = new GeneratedKeyHolder();");
            sb.append(", keyHolder);");
            method.addBodyLine(sb.toString());
//            method.addBodyLine("return keyHolder.getKey()." + (autoIncrementColumn.getFullyQualifiedJavaType().getShortName().equals("Long") ? "long" : "int") + "Value();");
            method.addBodyLine("record." + getSetterMethodName(autoIncrementColumn.getJavaProperty()) + "(keyHolder.getKey()." + (autoIncrementColumn.getFullyQualifiedJavaType().getShortName().equals("Long") ? "long" : "int") + "Value()" + ");");
        } else {
            sb.append(");");
            method.addBodyLine(sb.toString());
        }
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addImportedType("org.springframework.jdbc.core.namedparam.MapSqlParameterSource");
        topLevelClass.addMethod(method);
    }

    private void addSelectByCriteriaMethod(TopLevelClass topLevelClass) {
        topLevelClass.addImportedType(Map.class.getName());
        topLevelClass.addImportedType(HashMap.class.getName());
        String objectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        Method method = new Method("countByCriteria");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.addBodyLine("Map<String, Object> params = criteria.toSql();");
        String alias = introspectedTable.getFullyQualifiedTable().getAlias();
        boolean hasAlias = stringHasValue(alias);
        method.addBodyLine("return getNamedParameterJdbcTemplate().queryForObject(\"SELECT COUNT(0) FROM \" + " + objectName + ".TABLE_NAME + \" " + (hasAlias ? alias : "") + " \" + criteria.getWhereClause(), params, int.class);");
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        topLevelClass.addImportedType(criteriaType);
        topLevelClass.addMethod(method);

        method = new Method("selectByCriteria");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "offset"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "count"));
        method.addBodyLine("Map<String, Object> params = criteria.toSql();");
        method.addBodyLine("return getNamedParameterJdbcTemplate().query(\"SELECT \" + " + objectName + (hasAlias ? ".ALIASED_BASE_COLUMNS" : ".BASE_COLUMNS") + " + \" FROM \" + " + objectName + ".TABLE_NAME + \" " + (hasAlias ? alias : "") + " \" + criteria.getWhereClause() + criteria.getOrderByClause() + \" LIMIT \" + offset + ',' + count, params, rowMapper);");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        if (introspectedTable.getTableConfiguration().isDtoEnabled()) {
            returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getDtoType()));
        } else {
            returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getModelType()));
        }
        method.setReturnType(returnType);
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addImportedType(new FullyQualifiedJavaType(List.class.getName()));
        topLevelClass.addMethod(method);
    }

    private void addDeleteByPrimaryKeyMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Method method = new Method("deleteByPrimaryKey");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder whereFragment = new StringBuilder();
        StringBuilder valueFragment = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            method.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty()));
            whereFragment.append(column.getColumnName()).append(" = ? AND ");
            valueFragment.append(column.getJavaProperty()).append(", ");
        }
        whereFragment.setLength(whereFragment.length() - 4);
        valueFragment.setLength(valueFragment.length() - 2);
        String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        String sb = "return getJdbcTemplate().update(\"DELETE FROM \" + " +
                domainObjectName +
                ".TABLE_NAME + \" WHERE " +
                whereFragment +
                "\", " +
                valueFragment +
                ");";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addDeleteByCriteriaMethod(TopLevelClass topLevelClass) {
        String objectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        Method method = new Method("deleteByCriteria");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.addBodyLine("Map<String, Object> params = criteria.toSql();");
        method.addBodyLine("getNamedParameterJdbcTemplate().update(\"DELETE FROM \" + " + objectName + ".TABLE_NAME + ' ' + criteria.getWhereClause(), params);");
        context.getCommentGenerator().addGeneratedAnnotation(method, topLevelClass.getImportedTypes());
        topLevelClass.addImportedType(criteriaType);
        topLevelClass.addMethod(method);
    }
}
