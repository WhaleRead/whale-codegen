/**
 * Copyright 2006-2018 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.whaleread.codegen.api;

import com.whaleread.codegen.config.*;
import com.whaleread.codegen.internal.rules.FlatModelRules;
import com.whaleread.codegen.internal.rules.Rules;

import java.util.*;

import static com.whaleread.codegen.internal.util.StringUtility.isTrue;

/**
 * Base class for all code generator implementations. This class provides many
 * of the housekeeping methods needed to implement a code generator, with only
 * the actual code generation methods left unimplemented.
 *
 * @author Jeff Butler
 */
public abstract class IntrospectedTable {

    protected enum InternalAttribute {
        ATTR_MODEL_TYPE,
        ATTR_DTO_TYPE,
        ATTR_DAO_TYPE,
        ATTR_SERVICE_TYPE,
        ATTR_CONTROLLER_TYPE,

        ATTR_DAO_IMPLEMENTATION_TYPE,
        ATTR_DAO_INTERFACE_TYPE,
        ATTR_PRIMARY_KEY_TYPE,
        ATTR_BASE_RECORD_TYPE,
        ATTR_EXAMPLE_TYPE,
        ATTR_IBATIS2_SQL_MAP_PACKAGE,
        ATTR_IBATIS2_SQL_MAP_FILE_NAME,
        ATTR_IBATIS2_SQL_MAP_NAMESPACE,
        ATTR_MYBATIS3_XML_MAPPER_PACKAGE,
        ATTR_MYBATIS3_XML_MAPPER_FILE_NAME,
        /**
         * also used as XML Mapper namespace if a Java mapper is generated.
         */
        ATTR_MYBATIS3_JAVA_MAPPER_TYPE,
        /**
         * used as XML Mapper namespace if no client is generated.
         */
        ATTR_MYBATIS3_FALLBACK_SQL_MAP_NAMESPACE,
        ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME,
        ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME,
        ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID,
        ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID,
        ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID,
        ATTR_INSERT_STATEMENT_ID,
        ATTR_INSERT_SELECTIVE_STATEMENT_ID,
        ATTR_SELECT_ALL_STATEMENT_ID,
        ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID,
        ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID,
        ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID,
        ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID,
        ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID,
        ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID,
        ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID,
        ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID,
        ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID,
        ATTR_BASE_RESULT_MAP_ID,
        ATTR_RESULT_MAP_WITH_BLOBS_ID,
        ATTR_EXAMPLE_WHERE_CLAUSE_ID,
        ATTR_BASE_COLUMN_LIST_ID,
        ATTR_BLOB_COLUMN_LIST_ID,
        ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID,
        ATTR_MYBATIS3_SQL_PROVIDER_TYPE
    }

    protected TableConfiguration tableConfiguration;

    protected FullyQualifiedTable fullyQualifiedTable;

    protected Context context;

    protected Rules rules;

    protected List<IntrospectedColumn> primaryKeyColumns;

    protected List<IntrospectedColumn> baseColumns;

    protected List<IntrospectedColumn> blobColumns;

    /**
     * Attributes may be used by plugins to capture table related state between
     * the different plugin calls.
     */
    protected Map<String, Object> attributes;

    /**
     * Internal attributes are used to store commonly accessed items by all code generators.
     */
    protected Map<IntrospectedTable.InternalAttribute, String> internalAttributes;

    /**
     * Table remarks retrieved from database metadata.
     */
    protected String remarks;

    /**
     * Table type retrieved from database metadata.
     */
    protected String tableType;

    public IntrospectedTable() {
        super();
        primaryKeyColumns = new ArrayList<IntrospectedColumn>();
        baseColumns = new ArrayList<IntrospectedColumn>();
        blobColumns = new ArrayList<IntrospectedColumn>();
        attributes = new HashMap<String, Object>();
        internalAttributes = new HashMap<IntrospectedTable.InternalAttribute, String>();
    }

    public FullyQualifiedTable getFullyQualifiedTable() {
        return fullyQualifiedTable;
    }

    public GeneratedKey getGeneratedKey() {
        return tableConfiguration.getGeneratedKey();
    }

    public IntrospectedColumn getColumn(String columnName) {
        if (columnName == null) {
            return null;
        } else {
            // search primary key columns
            for (IntrospectedColumn introspectedColumn : primaryKeyColumns) {
                if (introspectedColumn.isColumnNameDelimited()) {
                    if (introspectedColumn.getActualColumnName().equals(
                            columnName)) {
                        return introspectedColumn;
                    }
                } else {
                    if (introspectedColumn.getActualColumnName()
                            .equalsIgnoreCase(columnName)) {
                        return introspectedColumn;
                    }
                }
            }

            // search base columns
            for (IntrospectedColumn introspectedColumn : baseColumns) {
                if (introspectedColumn.isColumnNameDelimited()) {
                    if (introspectedColumn.getActualColumnName().equals(
                            columnName)) {
                        return introspectedColumn;
                    }
                } else {
                    if (introspectedColumn.getActualColumnName()
                            .equalsIgnoreCase(columnName)) {
                        return introspectedColumn;
                    }
                }
            }

            // search blob columns
            for (IntrospectedColumn introspectedColumn : blobColumns) {
                if (introspectedColumn.isColumnNameDelimited()) {
                    if (introspectedColumn.getActualColumnName().equals(
                            columnName)) {
                        return introspectedColumn;
                    }
                } else {
                    if (introspectedColumn.getActualColumnName()
                            .equalsIgnoreCase(columnName)) {
                        return introspectedColumn;
                    }
                }
            }

            return null;
        }
    }

    /**
     * Returns true if any of the columns in the table are JDBC Dates (as
     * opposed to timestamps).
     *
     * @return true if the table contains DATE columns
     */
    public boolean hasJDBCDateColumns() {
        boolean rc = false;

        for (IntrospectedColumn introspectedColumn : primaryKeyColumns) {
            if (introspectedColumn.isJDBCDateColumn()) {
                rc = true;
                break;
            }
        }

        if (!rc) {
            for (IntrospectedColumn introspectedColumn : baseColumns) {
                if (introspectedColumn.isJDBCDateColumn()) {
                    rc = true;
                    break;
                }
            }
        }

        return rc;
    }

    /**
     * Returns true if any of the columns in the table are JDBC Times (as
     * opposed to timestamps).
     *
     * @return true if the table contains TIME columns
     */
    public boolean hasJDBCTimeColumns() {
        boolean rc = false;

        for (IntrospectedColumn introspectedColumn : primaryKeyColumns) {
            if (introspectedColumn.isJDBCTimeColumn()) {
                rc = true;
                break;
            }
        }

        if (!rc) {
            for (IntrospectedColumn introspectedColumn : baseColumns) {
                if (introspectedColumn.isJDBCTimeColumn()) {
                    rc = true;
                    break;
                }
            }
        }

        return rc;
    }

    /**
     * Returns the columns in the primary key. If the generatePrimaryKeyClass()
     * method returns false, then these columns will be iterated as the
     * parameters of the selectByPrimaryKay and deleteByPrimaryKey methods
     *
     * @return a List of ColumnDefinition objects for columns in the primary key
     */
    public List<IntrospectedColumn> getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }

    public boolean hasPrimaryKeyColumns() {
        return primaryKeyColumns.size() > 0;
    }

    public List<IntrospectedColumn> getBaseColumns() {
        return baseColumns;
    }

    /**
     * Returns all columns in the table (for use by the select by primary key and
     * select by example with BLOBs methods).
     *
     * @return a List of ColumnDefinition objects for all columns in the table
     */
    public List<IntrospectedColumn> getAllColumns() {
        List<IntrospectedColumn> answer = new ArrayList<IntrospectedColumn>();
        answer.addAll(primaryKeyColumns);
        answer.addAll(baseColumns);
        answer.addAll(blobColumns);

        return answer;
    }

    /**
     * Returns all columns except BLOBs (for use by the select by example without BLOBs method).
     *
     * @return a List of ColumnDefinition objects for columns in the table that are non BLOBs
     */
    public List<IntrospectedColumn> getNonBLOBColumns() {
        List<IntrospectedColumn> answer = new ArrayList<IntrospectedColumn>();
        answer.addAll(primaryKeyColumns);
        answer.addAll(baseColumns);

        return answer;
    }

    public int getNonBLOBColumnCount() {
        return primaryKeyColumns.size() + baseColumns.size();
    }

    public List<IntrospectedColumn> getNonPrimaryKeyColumns() {
        List<IntrospectedColumn> answer = new ArrayList<IntrospectedColumn>();
        answer.addAll(baseColumns);
        answer.addAll(blobColumns);

        return answer;
    }

    public List<IntrospectedColumn> getBLOBColumns() {
        return blobColumns;
    }

    public boolean hasBLOBColumns() {
        return blobColumns.size() > 0;
    }

    public boolean hasBaseColumns() {
        return baseColumns.size() > 0;
    }

    public Rules getRules() {
        return rules;
    }

    public String getTableConfigurationProperty(String property) {
        return tableConfiguration.getProperty(property);
    }

    public boolean hasAnyColumns() {
        return primaryKeyColumns.size() > 0 || baseColumns.size() > 0
                || blobColumns.size() > 0;
    }

    public void setTableConfiguration(TableConfiguration tableConfiguration) {
        this.tableConfiguration = tableConfiguration;
    }

    public void setFullyQualifiedTable(FullyQualifiedTable fullyQualifiedTable) {
        this.fullyQualifiedTable = fullyQualifiedTable;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void addColumn(IntrospectedColumn introspectedColumn) {
        if (introspectedColumn.isBLOBColumn()) {
            blobColumns.add(introspectedColumn);
        } else {
            baseColumns.add(introspectedColumn);
        }

        introspectedColumn.setIntrospectedTable(this);
    }

    public void addPrimaryKeyColumn(String columnName) {
        boolean found = false;
        // first search base columns
        Iterator<IntrospectedColumn> iter = baseColumns.iterator();
        while (iter.hasNext()) {
            IntrospectedColumn introspectedColumn = iter.next();
            if (introspectedColumn.getActualColumnName().equals(columnName)) {
                primaryKeyColumns.add(introspectedColumn);
                iter.remove();
                found = true;
                break;
            }
        }

        // search blob columns in the weird event that a blob is the primary key
        if (!found) {
            iter = blobColumns.iterator();
            while (iter.hasNext()) {
                IntrospectedColumn introspectedColumn = iter.next();
                if (introspectedColumn.getActualColumnName().equals(columnName)) {
                    primaryKeyColumns.add(introspectedColumn);
                    iter.remove();
                    found = true;
                    break;
                }
            }
        }
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public void initialize() {
        calculateAttributes();
        rules = new FlatModelRules(this);
        context.getPlugins().initialized(this);
    }

    private boolean isSubPackagesEnabled(PropertyHolder propertyHolder) {
        return isTrue(propertyHolder.getProperty(PropertyRegistry.ANY_ENABLE_SUB_PACKAGES));
    }

    protected String calculateJavaClientInterfacePackage() {
        BuiltInGeneratorConfiguration config = context
                .getBuiltInGeneratorConfiguration();
        if (config == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(config.getTargetPackage());

        sb.append(fullyQualifiedTable.getSubPackage(isSubPackagesEnabled(config)));

        return sb.toString();
    }

    public String getFullyQualifiedTableNameAtRuntime() {
        return internalAttributes
                .get(InternalAttribute.ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME);
    }

    public String getAliasedFullyQualifiedTableNameAtRuntime() {
        return internalAttributes
                .get(InternalAttribute.ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME);
    }

    /**
     * Gets the record with blo bs type.
     *
     * @return the type for the record with BLOBs class. Note that the value will be calculated regardless of whether
     * the table has BLOB columns or not.
     */
    public String getModelType() {
        return internalAttributes
                .get(InternalAttribute.ATTR_MODEL_TYPE);
    }

    public String getDtoType() {
        return internalAttributes.get(InternalAttribute.ATTR_DTO_TYPE);
    }

    public String getDaoType() {
        return internalAttributes.get(InternalAttribute.ATTR_DAO_TYPE);
    }

    public String getServiceType() {
        return internalAttributes.get(InternalAttribute.ATTR_SERVICE_TYPE);
    }

    public String getControllerType() {
        return internalAttributes.get(InternalAttribute.ATTR_CONTROLLER_TYPE);
    }

    /**
     * This method can be used to initialize the generators before they will be called.
     * <p>
     * <p>This method is called after all the setX methods, but before getNumberOfSubtasks(), getGeneratedJavaFiles, and
     * getGeneratedXmlFiles.
     *
     * @param warnings         the warnings
     * @param progressCallback the progress callback
     */
    public abstract void calculateGenerators(List<String> warnings,
                                             ProgressCallback progressCallback);

    /**
     * This method should return a list of generated files related to this
     * table.
     *
     * @return the list of generated files for this table
     */
    public abstract List<GeneratedFile> getGeneratedFiles();

    /**
     * Denotes whether generated code is targeted for Java version 5.0 or
     * higher.
     *
     * @return true if the generated code makes use of Java5 features
     */
    public abstract boolean isJava5Targeted();

    /**
     * This method should return the number of progress messages that will be
     * send during the generation phase.
     *
     * @return the number of progress messages
     */
    public abstract int getGenerationSteps();

    /**
     * This method exists to give plugins the opportunity to replace the calculated rules if necessary.
     *
     * @param rules the new rules
     */
    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public TableConfiguration getTableConfiguration() {
        return tableConfiguration;
    }

    public boolean isImmutable() {
        Properties properties;

        if (tableConfiguration.getProperties().containsKey(PropertyRegistry.ANY_IMMUTABLE)) {
            properties = tableConfiguration.getProperties();
        } else {
            properties = context.getBuiltInGeneratorConfiguration().getProperties();
        }

        return isTrue(properties.getProperty(PropertyRegistry.ANY_IMMUTABLE));
    }

    public boolean isConstructorBased() {
        if (isImmutable()) {
            return true;
        }

        Properties properties;

        if (tableConfiguration.getProperties().containsKey(PropertyRegistry.ANY_CONSTRUCTOR_BASED)) {
            properties = tableConfiguration.getProperties();
        } else {
            properties = context.getBuiltInGeneratorConfiguration().getProperties();
        }

        return isTrue(properties.getProperty(PropertyRegistry.ANY_CONSTRUCTOR_BASED));
    }

    public Context getContext() {
        return context;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }


    public void calculateAttributes() {
        BuiltInGeneratorConfiguration config = context
                .getBuiltInGeneratorConfiguration();

        StringBuilder sb = new StringBuilder();
        sb.append(config.getTargetPackage());
        sb.append(fullyQualifiedTable.getSubPackage(isSubPackagesEnabled(config)));
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectSubPackage() == null ? config.getModelSubPackage() : fullyQualifiedTable.getDomainObjectSubPackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        internalAttributes.put(InternalAttribute.ATTR_MODEL_TYPE, sb.toString());

        sb.setLength(0);
        sb.append(config.getTargetPackage());
        sb.append(fullyQualifiedTable.getSubPackage(isSubPackagesEnabled(config)));
        sb.append('.');
        sb.append(config.getDtoSubPackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append(config.getDtoSuffix());
        internalAttributes.put(InternalAttribute.ATTR_DTO_TYPE, sb.toString());

        sb.setLength(0);
        sb.append(config.getTargetPackage());
        sb.append(fullyQualifiedTable.getSubPackage(isSubPackagesEnabled(config)));
        sb.append('.');
        sb.append(config.getDaoSubPackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append(config.getDaoSuffix());
        internalAttributes.put(InternalAttribute.ATTR_DAO_TYPE, sb.toString());

        sb.setLength(0);
        sb.append(config.getTargetPackage());
        sb.append(fullyQualifiedTable.getSubPackage(isSubPackagesEnabled(config)));
        sb.append('.');
        sb.append(config.getServiceSubPackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append(config.getServiceSuffix());
        internalAttributes.put(InternalAttribute.ATTR_SERVICE_TYPE, sb.toString());
    }
}
