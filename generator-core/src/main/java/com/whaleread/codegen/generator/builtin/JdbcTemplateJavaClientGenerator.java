package com.whaleread.codegen.generator.builtin;

import com.whaleread.codegen.api.CommentGenerator;
import com.whaleread.codegen.api.FullyQualifiedTable;
import com.whaleread.codegen.api.IntrospectedColumn;
import com.whaleread.codegen.api.dom.java.CompilationUnit;
import com.whaleread.codegen.api.dom.java.Field;
import com.whaleread.codegen.api.dom.java.FullyQualifiedJavaType;
import com.whaleread.codegen.api.dom.java.JavaVisibility;
import com.whaleread.codegen.api.dom.java.Method;
import com.whaleread.codegen.api.dom.java.Parameter;
import com.whaleread.codegen.api.dom.java.TopLevelClass;
import com.whaleread.codegen.api.dom.java.TypeParameter;
import com.whaleread.codegen.config.TableConfiguration;
import com.whaleread.codegen.generator.AbstractJavaGenerator;
import com.whaleread.codegen.runtime.jdbc.Criteria;
import com.whaleread.codegen.runtime.jdbc.spring.AliasBeanPropertyRowMapper;
import com.whaleread.codegen.runtime.jdbc.spring.GenericBeanPropertyRowMapper;

import javax.annotation.Generated;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static com.whaleread.codegen.internal.util.JavaBeansUtil.getGetterMethodName;
import static com.whaleread.codegen.internal.util.JavaBeansUtil.getSetterMethodName;
import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;
import static com.whaleread.codegen.internal.util.messages.Messages.getString;

/**
 * Created by Dolphin on 2018/3/23
 */
public class JdbcTemplateJavaClientGenerator extends AbstractJavaGenerator {
    private static final String PROPERTY_TABLE_NAME = "tableName";

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        if (!introspectedTable.getTableConfiguration().isEnableDAO()) {
            return Collections.emptyList();
        }
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString("Progress.8", table.toString())); //$NON-NLS-1$
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

//        if (tableConfig.isEnableDTO()) {
//            topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getDtoType()));
//        }

//        String shardingTableName = tableConfig.getProperty(TABLE_SHARDING_TABLE_NAME);
//        if (stringHasValue(shardingTableName)) {
//            introspectedTable.setAttribute(PROPERTY_SHARDING, true);
//            introspectedTable.setAttribute(PROPERTY_TABLE_NAME, shardingTableName.replace("{tableName}", introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ".TABLE_NAME"));
//            String shardingParams = tableConfig.getProperty(TABLE_SHARDING_PARAMS);
//            if (stringHasValue(shardingParams)) {
//                List<Parameter> shardingParamList = new ArrayList<>();
//                for (String paramTupleStr : shardingParams.split(",")) {
//                    String[] paramTuple = paramTupleStr.split(" ");
//                    Parameter param = new Parameter(new FullyQualifiedJavaType(paramTuple[0]), paramTuple[1]);
//                    topLevelClass.addImportedType(param.getType());
//                    shardingParamList.add(param);
//                }
//                introspectedTable.setAttribute(TABLE_SHARDING_PARAMS, shardingParamList);
//            }
//        } else {
        introspectedTable.setAttribute(PROPERTY_TABLE_NAME, introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ".TABLE_NAME");
//        }


        addRowMapperField(topLevelClass);
        addInjectMethod(topLevelClass);

        if (tableConfig.isEnableSelectByPrimaryKey()) {
            addSelectByPrimaryKeyMethod(topLevelClass);
            addSelectByPrimaryKeySubclassMethod(topLevelClass);
        }
        if (tableConfig.isEnableCountByCriteria()) {
            addCountByCriteriaMethod(topLevelClass);
        }
        if (tableConfig.isEnableSelectByCriteria()) {
            addSelectByCriteriaMethod(topLevelClass);
            addSelectByCriteriaSubclassMethod(topLevelClass);
        }

        if (tableConfig.isEnableInsert()) {
            addInsertMethod(topLevelClass);
        }
        if (tableConfig.isEnableInsertSelective()) {
            addInsertSelectiveMethod(topLevelClass);
        }

//        if (tableConfig.isEnableUpdateByPrimaryKey()) {
//            addUpdateByPrimaryKeyMethod(topLevelClass);
//        }

        if (tableConfig.isEnableUpdateByPrimaryKeySelective()) {
            addUpdateByPrimaryKeySelectiveMethod(topLevelClass);
        }

        if (tableConfig.isEnableDeleteByPrimaryKey()) {
            addDeleteByPrimaryKeyMethod(topLevelClass);
        }
        if (tableConfig.isEnableDeleteByCriteria()) {
            addDeleteByCriteriaMethod(topLevelClass);
        }

        if (context.getPlugins().clientGenerated(null, topLevelClass, introspectedTable)) {
            return Collections.singletonList(topLevelClass);
        }
        return Collections.emptyList();
    }

    private void addRowMapperField(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        FullyQualifiedJavaType rowMapperType;
        String paramTypeName;
//        if (introspectedTable.getTableConfiguration().isEnableDTO()) {
//            paramTypeName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "DTO";
//            rowMapperType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getDtoType()));
//        } else {
        paramTypeName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
//        }
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setName("rowMapper");
        if (stringHasValue(introspectedTable.getFullyQualifiedTable().getAlias())) {
            rowMapperType = new FullyQualifiedJavaType(AliasBeanPropertyRowMapper.class.getName());
            importedTypes.add(new FullyQualifiedJavaType(AliasBeanPropertyRowMapper.class.getName()));
            field.setInitializationString("new AliasBeanPropertyRowMapper<>(" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ".TABLE_ALIAS, " + paramTypeName + ".class)");
        } else {
            rowMapperType = new FullyQualifiedJavaType(GenericBeanPropertyRowMapper.class.getName());
            importedTypes.add(new FullyQualifiedJavaType(GenericBeanPropertyRowMapper.class.getName()));
            field.setInitializationString("new GenericBeanPropertyRowMapper<>(" + paramTypeName + ".class)");
        }
        rowMapperType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getModelType()));
        field.setType(rowMapperType);
        context.getCommentGenerator().addGeneratedAnnotation(field, importedTypes);
        if (context.getPlugins().clientRowMapperFieldGenerated(field, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addField(field);
        }
    }

    private void addInjectMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        FullyQualifiedJavaType injectedType = new FullyQualifiedJavaType(DataSource.class.getName());
        importedTypes.add(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        importedTypes.add(injectedType);
        Method method = new Method("inject");
        method.addAnnotation("@Autowired");
        method.setVisibility(JavaVisibility.PUBLIC);
        Parameter parameter = new Parameter(injectedType, "dataSource");
        method.addParameter(parameter);
        if (stringHasValue(context.getBuiltInGeneratorConfiguration().getProperty("dataSource"))) {
            importedTypes.add(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Qualifier"));
            parameter.addAnnotation("@Qualifier(\"" + context.getBuiltInGeneratorConfiguration().getProperty("dataSource") + "\")");
        }
        method.addBodyLine("super.setDataSource(dataSource);");
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().clientInjectMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addSelectByPrimaryKeyMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method("selectByPrimaryKey");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder whereFragment = new StringBuilder();
        StringBuilder valueFragment = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            importedTypes.add(column.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty()));
            whereFragment.append(column.getColumnName()).append(" = ? AND ");
            valueFragment.append(column.getJavaProperty()).append(", ");
        }
//        addExtraParams(method);
        whereFragment.setLength(whereFragment.length() - 4);
        valueFragment.setLength(valueFragment.length() - 2);
        String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        boolean nonNullEnabled = context.getBuiltInGeneratorConfiguration().isEnableNonNull();
        FullyQualifiedJavaType returnType;
//        if (introspectedTable.getTableConfiguration().isEnableDTO()) {
//            returnType = new FullyQualifiedJavaType(introspectedTable.getDtoType());
//        } else {
        returnType = new FullyQualifiedJavaType(introspectedTable.getModelType());
//        }
        if (nonNullEnabled) {
            importedTypes.add(new FullyQualifiedJavaType("java.util.Optional"));
            FullyQualifiedJavaType optionalType = new FullyQualifiedJavaType("java.util.Optional");
            optionalType.addTypeArgument(returnType);
            method.setReturnType(optionalType);
        } else {
            method.setReturnType(returnType);
        }
        String sb = "return getJdbcTemplate().query(\"SELECT \" + " +
                domainObjectName +
                ".BASE_COLUMNS + \" FROM \" + " +
                introspectedTable.getAttribute(PROPERTY_TABLE_NAME) +
                " + \" WHERE " +
                whereFragment +
                "\", new Object[]{" +
                valueFragment +
                "}, rs -> rs.next() ? " + (nonNullEnabled ? "Optional.of(rowMapper.mapRow(rs, 0)) : Optional.empty());" : "rowMapper.mapRow(rs, 0) : null);");
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addSelectByPrimaryKeySubclassMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method("selectByPrimaryKey");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder whereFragment = new StringBuilder();
        StringBuilder valueFragment = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            importedTypes.add(column.getFullyQualifiedJavaType());
            method.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty()));
            whereFragment.append(column.getColumnName()).append(" = ? AND ");
            valueFragment.append(column.getJavaProperty()).append(", ");
        }

        whereFragment.setLength(whereFragment.length() - 4);
        valueFragment.setLength(valueFragment.length() - 2);
        String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        boolean nonNullEnabled = context.getBuiltInGeneratorConfiguration().isEnableNonNull();

        FullyQualifiedJavaType classType = new FullyQualifiedJavaType("java.lang.Class<T>");
        method.addParameter(new Parameter(classType, "expectedType"));
//        addExtraParams(method);
        method.addTypeParameter(new TypeParameter("T", Collections.singletonList(new FullyQualifiedJavaType(introspectedTable.getModelType()))));
        if (nonNullEnabled) {
            importedTypes.add(new FullyQualifiedJavaType("java.util.Optional"));
            method.setReturnType(new FullyQualifiedJavaType("java.util.Optional<T>"));
        } else {
            method.setReturnType(new FullyQualifiedJavaType("T"));
        }
        String sb = "return getJdbcTemplate().query(\"SELECT \" + " +
                domainObjectName +
                ".BASE_COLUMNS + \" FROM \" + " +
                introspectedTable.getAttribute(PROPERTY_TABLE_NAME) +
                " + \" WHERE " +
                whereFragment +
                "\", new Object[]{" +
                valueFragment +
                "}, rs -> rs.next() ? " + (nonNullEnabled ? "Optional.of(rowMapper.mapRow(rs, 0, expectedType)) : Optional.empty());" : "rowMapper.mapRow(rs, 0, expectedType) : null);");
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().clientSelectByPrimaryKeySubclassMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(new FullyQualifiedJavaType(Map.class.getName()));
        importedTypes.add(new FullyQualifiedJavaType(HashMap.class.getName()));
        Method method = new Method("updateByPrimaryKeySelective");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getModelType()), "record"));
//        addExtraParams(method);
        method.addBodyLine("StringBuilder fragment = new StringBuilder();");
        method.addBodyLine("Map<String, Object> params = new HashMap<>();");
        StringBuilder whereFragment = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            method.addBodyLine("params.put(\"" + column.getJavaProperty() + "\", record." + getGetterMethodName(column.getJavaProperty(), column.getFullyQualifiedJavaType()) + "());");
            whereFragment.append(column.getColumnName()).append(" = :").append(column.getJavaProperty()).append(" AND ");
        }
        whereFragment.setLength(whereFragment.length() - 4);
        for (IntrospectedColumn column : introspectedTable.getNonPrimaryKeyColumns()) {
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
        method.addBodyLine("getNamedParameterJdbcTemplate().update(\"UPDATE \" + " + introspectedTable.getAttribute(PROPERTY_TABLE_NAME) + " + \" SET \" + fragment + \" WHERE " + whereFragment + "\", params);");
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().clientUpdateByPrimaryKeySelectiveMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addInsertSelectiveMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(new FullyQualifiedJavaType(Map.class.getName()));
        importedTypes.add(new FullyQualifiedJavaType(HashMap.class.getName()));
        Method method = new Method("insertSelective");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getModelType()), "record"));
//        addExtraParams(method);
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
        sb.append("getNamedParameterJdbcTemplate().update(\"INSERT INTO \" + ").append(introspectedTable.getAttribute(PROPERTY_TABLE_NAME)).append(" + \" (\" + columnsFragment + \") VALUES (\" + valuesFragment + \")\", new MapSqlParameterSource(params)");
        //noinspection Duplicates
        if (autoIncrementColumn != null) {
            importedTypes.add(new FullyQualifiedJavaType("org.springframework.jdbc.support.KeyHolder"));
            importedTypes.add(new FullyQualifiedJavaType("org.springframework.jdbc.support.GeneratedKeyHolder"));
            method.addBodyLine("KeyHolder keyHolder = new GeneratedKeyHolder();");
            sb.append(", keyHolder);");
            method.addBodyLine(sb.toString());
            method.addBodyLine("record." + getSetterMethodName(autoIncrementColumn.getJavaProperty()) + "(keyHolder.getKey()." + (autoIncrementColumn.getFullyQualifiedJavaType().getShortName().equals("Long") ? "long" : "int") + "Value()" + ");");
        } else {
            sb.append(");");
            method.addBodyLine(sb.toString());
        }
        importedTypes.add(new FullyQualifiedJavaType("org.springframework.jdbc.core.namedparam.MapSqlParameterSource"));
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().clientInsertSelectiveMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addInsertMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        StringBuilder columnsFragment = new StringBuilder();
        StringBuilder valuesFragment = new StringBuilder();
        Method method = new Method("insert");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getModelType()), "record"));
//        addExtraParams(method);
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
        String insertSql = "\"INSERT INTO \" + " + introspectedTable.getAttribute(PROPERTY_TABLE_NAME) + " + \"(" +
                columnsFragment + ") VALUES (" + valuesFragment + ")\"";
//        Field insertSqlField = new Field("INSERT_SQL", FullyQualifiedJavaType.getStringInstance());
//        insertSqlField.setInitializationString(insertSql);
//        privateStaticFinal(insertSqlField);
//        context.getCommentGenerator().addGeneratedAnnotation(insertSqlField, topLevelClass.getImportedTypes());
//        topLevelClass.addField(insertSqlField);
//        method.addBodyLine("if (columnsFragment.length() > 0) {");
//        method.addBodyLine("columnsFragment.setLength(columnsFragment.length() - 2);");
//        method.addBodyLine("valuesFragment.setLength(valuesFragment.length() - 2);");
//        method.addBodyLine("}");
        StringBuilder sb = new StringBuilder();
        sb.append("getNamedParameterJdbcTemplate().update(").append(insertSql).append(", new MapSqlParameterSource(params)");
        //noinspection Duplicates
        if (autoIncrementColumn != null) {
            importedTypes.add(new FullyQualifiedJavaType("org.springframework.jdbc.support.KeyHolder"));
            importedTypes.add(new FullyQualifiedJavaType("org.springframework.jdbc.support.GeneratedKeyHolder"));
            method.addBodyLine("KeyHolder keyHolder = new GeneratedKeyHolder();");
            sb.append(", keyHolder);");
            method.addBodyLine(sb.toString());
            method.addBodyLine("record." + getSetterMethodName(autoIncrementColumn.getJavaProperty()) + "(keyHolder.getKey()." + (autoIncrementColumn.getFullyQualifiedJavaType().getShortName().equals("Long") ? "long" : "int") + "Value()" + ");");
        } else {
            sb.append(");");
            method.addBodyLine(sb.toString());
        }
        importedTypes.add(new FullyQualifiedJavaType("org.springframework.jdbc.core.namedparam.MapSqlParameterSource"));
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addCountByCriteriaMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(new FullyQualifiedJavaType(Map.class.getName()));
        importedTypes.add(new FullyQualifiedJavaType(HashMap.class.getName()));
        Method method = new Method("countByCriteria");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        method.addParameter(new Parameter(criteriaType, "criteria"));
//        addExtraParams(method);
        method.addBodyLine("Map<String, Object> params = criteria.toSql();");
        String alias = introspectedTable.getFullyQualifiedTable().getAlias();
        boolean hasAlias = stringHasValue(alias);
        method.addBodyLine("return getNamedParameterJdbcTemplate().queryForObject(\"SELECT COUNT(0) FROM \" + " + introspectedTable.getAttribute(PROPERTY_TABLE_NAME) + " + \" " + (hasAlias ? alias : "") + " \" + criteria.getWhereClause(), params, int.class);");
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        importedTypes.add(criteriaType);
        if (context.getPlugins().clientCountByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addSelectByCriteriaMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(new FullyQualifiedJavaType(Map.class.getName()));
        importedTypes.add(new FullyQualifiedJavaType(HashMap.class.getName()));
        String objectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        Method method = new Method("selectByCriteria");
        method.setVisibility(JavaVisibility.PUBLIC);
        String alias = introspectedTable.getFullyQualifiedTable().getAlias();
        boolean hasAlias = stringHasValue(alias);
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "offset"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "count"));
//        addExtraParams(method);
        method.addBodyLine("Map<String, Object> params = criteria.toSql();");
        method.addBodyLine("return getNamedParameterJdbcTemplate().query(\"SELECT \" + " + objectName + (hasAlias ? ".ALIASED_BASE_COLUMNS" : ".BASE_COLUMNS") + " + \" FROM \" + " + introspectedTable.getAttribute(PROPERTY_TABLE_NAME) + " + \" " + (hasAlias ? alias : "") + " \" + criteria.getWhereClause() + criteria.getOrderByClause() + \" LIMIT \" + offset + ',' + count, params, rowMapper);");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
//        if (introspectedTable.getTableConfiguration().isEnableDTO()) {
//            returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getDtoType()));
//        } else {
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getModelType()));
//        }
        method.setReturnType(returnType);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        importedTypes.add(new FullyQualifiedJavaType(List.class.getName()));
        if (context.getPlugins().clientSelectByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addSelectByCriteriaSubclassMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(new FullyQualifiedJavaType(Map.class.getName()));
        importedTypes.add(new FullyQualifiedJavaType(HashMap.class.getName()));
        String objectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        Method method = new Method("selectByCriteria");
        method.setVisibility(JavaVisibility.PUBLIC);
        String alias = introspectedTable.getFullyQualifiedTable().getAlias();
        boolean hasAlias = stringHasValue(alias);
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        method.addParameter(new Parameter(criteriaType, "criteria"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "offset"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "count"));

        FullyQualifiedJavaType classType = new FullyQualifiedJavaType("java.lang.Class<T>");
        method.addParameter(new Parameter(classType, "expectedType"));
//        addExtraParams(method);
        method.addBodyLine("Map<String, Object> params = criteria.toSql();");
        method.addBodyLine("return getNamedParameterJdbcTemplate().query(\"SELECT \" + " + objectName + (hasAlias ? ".ALIASED_BASE_COLUMNS" : ".BASE_COLUMNS") + " + \" FROM \" + " + introspectedTable.getAttribute(PROPERTY_TABLE_NAME) + " + \" " + (hasAlias ? alias : "") + " \" + criteria.getWhereClause() + criteria.getOrderByClause() + \" LIMIT \" + offset + ',' + count, params, (rs, rowNum) -> rowMapper.mapRow(rs, rowNum, expectedType));");
        method.addTypeParameter(new TypeParameter("T", Collections.singletonList(new FullyQualifiedJavaType(introspectedTable.getModelType()))));
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName() + "<T>");
        method.setReturnType(returnType);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        importedTypes.add(new FullyQualifiedJavaType(List.class.getName()));
        if (context.getPlugins().clientSelectByCriteriaSubclassMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addDeleteByPrimaryKeyMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getPrimaryKeyColumns().isEmpty()) {
            return;
        }
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method("deleteByPrimaryKey");
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder whereFragment = new StringBuilder();
        StringBuilder valueFragment = new StringBuilder();
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            method.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty()));
            whereFragment.append(column.getColumnName()).append(" = ? AND ");
            valueFragment.append(column.getJavaProperty()).append(", ");
        }
//        addExtraParams(method);
        whereFragment.setLength(whereFragment.length() - 4);
        valueFragment.setLength(valueFragment.length() - 2);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        String sb = "return getJdbcTemplate().update(\"DELETE FROM \" + " +
                introspectedTable.getAttribute(PROPERTY_TABLE_NAME) +
                " + \" WHERE " +
                whereFragment +
                "\", " +
                valueFragment +
                ");";
        method.addBodyLine(sb);
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addDeleteByCriteriaMethod(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method("deleteByCriteria");
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType criteriaType = new FullyQualifiedJavaType(Criteria.class.getName());
        method.addParameter(new Parameter(criteriaType, "criteria"));
//        addExtraParams(method);
        method.addBodyLine("Map<String, Object> params = criteria.toSql();");
        method.addBodyLine("getNamedParameterJdbcTemplate().update(\"DELETE FROM \" + " + introspectedTable.getAttribute(PROPERTY_TABLE_NAME) + " + ' ' + criteria.getWhereClause(), params);");
        context.getCommentGenerator().addGeneratedAnnotation(method, importedTypes);
        importedTypes.add(criteriaType);
        if (context.getPlugins().clientDeleteByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

//    private void addExtraParams(Method element) {
//        if (introspectedTable.getAttribute(PROPERTY_SHARDING) != null) {
//            @SuppressWarnings("unchecked") List<Parameter> shardingParams = (List<Parameter>) introspectedTable.getAttribute(TABLE_SHARDING_PARAMS);
//            if (shardingParams != null) {
//                for (Parameter param : shardingParams) {
//                    if (Collections.binarySearch(element.getParameters(), param, (o1, o2) -> o1.getName().equals(o2.getName()) && o1.getType().equals(o2.getType()) ? 0 : 1) == -1) {
//                        element.addParameter(param);
//                    }
//                }
//            }
//        }
//    }
}
