package com.whaleread.codegen.plugins;

import com.whaleread.codegen.api.IntrospectedColumn;
import com.whaleread.codegen.api.IntrospectedTable;
import com.whaleread.codegen.api.PluginAdapter;
import com.whaleread.codegen.api.dom.java.Field;
import com.whaleread.codegen.api.dom.java.FullyQualifiedJavaType;
import com.whaleread.codegen.api.dom.java.Interface;
import com.whaleread.codegen.api.dom.java.JavaVisibility;
import com.whaleread.codegen.api.dom.java.Method;
import com.whaleread.codegen.api.dom.java.Parameter;
import com.whaleread.codegen.api.dom.java.TopLevelClass;
import com.whaleread.codegen.internal.util.StringUtility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;

/**
 * @author Dolphin
 */
public class SimpleShardingPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    private Map<IntrospectedTable, PluginData> dataCache = new HashMap<>();

    private PluginData getPluginData(IntrospectedTable introspectedTable) {
        PluginData data = dataCache.get(introspectedTable);
        if (data != null) {
            return data == PluginData.EMPTY ? null : data;
        }
        if (!StringUtility.isTrue(introspectedTable.getTableConfigurationProperty("sharding"))) {
            dataCache.put(introspectedTable, PluginData.EMPTY);
            return null;
        }
        String shardingColumn = introspectedTable.getTableConfigurationProperty("shardingColumn");
        if (!stringHasValue(shardingColumn)) {
            dataCache.put(introspectedTable, PluginData.EMPTY);
            return null;
        }
        data = new PluginData();
        IntrospectedColumn column = introspectedTable.getColumn(shardingColumn);
        if (column == null) {
            throw new IllegalArgumentException("sharding column not found: " + shardingColumn);
        }
        dataCache.put(introspectedTable, data);
        data.shardingColumnParameter = new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty());
        String shardingTableName = getProperties().getProperty("shardingTableName");
        if (stringHasValue(shardingColumn)) {
            data.originalTableName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ".TABLE_NAME";
            data.shardingTableName = shardingTableName.replace("{tableName}", data.originalTableName);
        }
        String importedTypes = getProperties().getProperty("importedTypes");
        if (stringHasValue(importedTypes)) {
            data.importedTypes = new HashSet<>();
            for (String importedType : importedTypes.split(",")) {
                data.importedTypes.add(new FullyQualifiedJavaType(importedType));
            }
        }
        return data;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientCountByCriteriaMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientSelectByCriteriaMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientSelectByCriteriaSubclassMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientDeleteByCriteriaMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return process(method, topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        PluginData data = getPluginData(introspectedTable);
        if (data == null) {
            return true;
        }
        String shardingCountKey = introspectedTable.getTableConfigurationProperty("shardingCountKey");
        if (!stringHasValue(shardingCountKey)) {
            return true;
        }
        Field shardingCountField = new Field("shardingCount", FullyQualifiedJavaType.getIntInstance());
        shardingCountField.setVisibility(JavaVisibility.PRIVATE);
        shardingCountField.addAnnotation("@Value(\"${" + shardingCountKey + "}\")");
        topLevelClass.addField(shardingCountField);
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Value"));
        return true;
    }

    private boolean process(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        PluginData data = getPluginData(introspectedTable);
        if (data == null) {
            return true;
        }
        if (data.shardingColumnParameter != null) {
            boolean columnParamExists = false;
            for (Parameter parameter : method.getParameters()) {
                if (parameter.getName().equals(data.shardingColumnParameter.getName())) {
                    columnParamExists = true;
                    break;
                }
            }
            if (!columnParamExists) {
                method.addParameter(data.shardingColumnParameter);
            }
        }

        if (data.shardingTableName != null) {
            String bodyLine;
            for (int i = 0; i < method.getBodyLines().size(); i++) {
                if ((bodyLine = method.getBodyLines().get(i)).contains(data.originalTableName)) {
                    method.getBodyLines().set(i, bodyLine.replace(data.originalTableName, data.shardingTableName));
                }
            }
        }

        if (data.importedTypes != null && data.importedTypes.size() > 0) {
            topLevelClass.addImportedTypes(data.importedTypes);
        }
        return true;
    }

    private static class PluginData {
        static PluginData EMPTY = new PluginData();
        Parameter shardingColumnParameter;
        Set<FullyQualifiedJavaType> importedTypes;
        String originalTableName;
        String shardingTableName;
    }
}
