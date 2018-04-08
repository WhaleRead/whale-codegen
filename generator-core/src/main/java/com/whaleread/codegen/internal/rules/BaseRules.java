/**
 * Copyright 2006-2017 the original author or authors.
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
package com.whaleread.codegen.internal.rules;

import com.whaleread.codegen.api.IntrospectedTable;
import com.whaleread.codegen.api.dom.java.FullyQualifiedJavaType;
import com.whaleread.codegen.config.PropertyRegistry;
import com.whaleread.codegen.config.TableConfiguration;
import com.whaleread.codegen.internal.util.StringUtility;

/**
 * This class centralizes all the rules related to code generation - including
 * the methods and objects to create, and certain attributes related to those
 * objects.
 *
 * @author Jeff Butler
 */
public abstract class BaseRules implements Rules {

    protected TableConfiguration tableConfiguration;

    protected IntrospectedTable introspectedTable;

    protected final boolean isModelOnly;

    public BaseRules(IntrospectedTable introspectedTable) {
        super();
        this.introspectedTable = introspectedTable;
        this.tableConfiguration = introspectedTable.getTableConfiguration();
        String modelOnly = tableConfiguration.getProperty(PropertyRegistry.TABLE_MODEL_ONLY);
        isModelOnly = StringUtility.isTrue(modelOnly);
    }

    /**
     * Implements the rule for generating the insert SQL Map element and DAO
     * method. If the insert statement is allowed, then generate the element and
     * method.
     *
     * @return true if the element and method should be generated
     */
    @Override
    public boolean generateInsert() {
        if (isModelOnly) {
            return false;
        }

        return tableConfiguration.isEnableInsert();
    }

    /**
     * Implements the rule for generating the insert selective SQL Map element
     * and DAO method. If the insert statement is allowed, then generate the
     * element and method.
     *
     * @return true if the element and method should be generated
     */
    @Override
    public boolean generateInsertSelective() {
        if (isModelOnly) {
            return false;
        }

        return tableConfiguration.isEnableInsert();
    }

    /**
     * Calculates the class that contains all fields. This class is used as the
     * insert statement parameter, as well as the returned value from the select
     * by primary key method. The actual class depends on how the domain model
     * is generated.
     *
     * @return the type of the class that holds all fields
     */
    @Override
    public FullyQualifiedJavaType calculateAllFieldsClass() {

        String answer;

        answer = introspectedTable.getModelType();

        return new FullyQualifiedJavaType(answer);
    }


    /**
     * Implements the rule for generating the delete by primary key SQL Map
     * element and DAO method. If the table has a primary key, and the
     * deleteByPrimaryKey statement is allowed, then generate the element and
     * method.
     *
     * @return true if the element and method should be generated
     */
    @Override
    public boolean generateDeleteByPrimaryKey() {
        if (isModelOnly) {
            return false;
        }

        boolean rc = tableConfiguration.isEnableDeleteByPrimaryKey()
                && introspectedTable.hasPrimaryKeyColumns();

        return rc;
    }

    /**
     * Implements the rule for generating the delete by example SQL Map element
     * and DAO method. If the deleteByExample statement is allowed, then
     * generate the element and method.
     *
     * @return true if the element and method should be generated
     */
    @Override
    public boolean generateDeleteByExample() {
//        if (isModelOnly) {
//            return false;
//        }
//
//        boolean rc = tableConfiguration.isDeleteByExampleStatementEnabled();
//
//        return rc;
        return false;
    }

    /**
     * Implements the rule for generating the result map without BLOBs. If
     * either select method is allowed, then generate the result map.
     *
     * @return true if the result map should be generated
     */
    @Override
    public boolean generateBaseResultMap() {
//        if (isModelOnly) {
//            return true;
//        }
//
//        boolean rc = tableConfiguration.isSelectByExampleStatementEnabled()
//                || tableConfiguration.isEnableSelectByPrimaryKey();
//
//        return rc;
        return false;
    }

    /**
     * Implements the rule for generating the result map with BLOBs. If the
     * table has BLOB columns, and either select method is allowed, then
     * generate the result map.
     *
     * @return true if the result map should be generated
     */
    @Override
    public boolean generateResultMapWithBLOBs() {
//        boolean rc;
//
//        if (introspectedTable.hasBLOBColumns()) {
//            if (isModelOnly) {
//                rc = true;
//            } else {
//                rc = tableConfiguration.isSelectByExampleStatementEnabled()
//                        || tableConfiguration.isEnableSelectByPrimaryKey();
//            }
//        } else {
//            rc = false;
//        }
//
//        return rc;
        return false;
    }

    /**
     * Implements the rule for generating the select by primary key SQL Map
     * element and DAO method. If the table has a primary key as well as other
     * fields, and the selectByPrimaryKey statement is allowed, then generate
     * the element and method.
     *
     * @return true if the element and method should be generated
     */
    @Override
    public boolean generateSelectByPrimaryKey() {
        if (isModelOnly) {
            return false;
        }

        boolean rc = tableConfiguration.isEnableSelectByPrimaryKey()
                && introspectedTable.hasPrimaryKeyColumns()
                && (introspectedTable.hasBaseColumns() || introspectedTable
                .hasBLOBColumns());

        return rc;
    }

    /**
     * Implements the rule for generating the select by example without BLOBs
     * SQL Map element and DAO method. If the selectByExample statement is
     * allowed, then generate the element and method.
     *
     * @return true if the element and method should be generated
     */
    @Override
    public boolean generateSelectByExampleWithoutBLOBs() {
//        if (isModelOnly) {
//            return false;
//        }
//
//        return tableConfiguration.isSelectByExampleStatementEnabled();
        return false;
    }

    /**
     * Implements the rule for generating the select by example with BLOBs SQL
     * Map element and DAO method. If the table has BLOB fields and the
     * selectByExample statement is allowed, then generate the element and
     * method.
     *
     * @return true if the element and method should be generated
     */
    @Override
    public boolean generateSelectByExampleWithBLOBs() {
//        if (isModelOnly) {
//            return false;
//        }
//
//        boolean rc = tableConfiguration.isSelectByExampleStatementEnabled()
//                && introspectedTable.hasBLOBColumns();
//
//        return rc;
        return false;
    }

    /**
     * Implements the rule for generating an example class. The class should be
     * generated if the selectByExample or deleteByExample or countByExample
     * methods are allowed.
     *
     * @return true if the example class should be generated
     */
    @Override
    public boolean generateDtoClass() {
        if (introspectedTable.getContext().getBuiltInGeneratorConfiguration() == null
                && introspectedTable.getContext().getBuiltInGeneratorConfiguration() == null) {
            // this is a model only context - don't generate the example class
            return false;
        }

        if (isModelOnly) {
            return false;
        }

        boolean rc = tableConfiguration.isEnableDTO();

        return rc;
    }

    @Override
    public boolean generateCountByExample() {
//        if (isModelOnly) {
//            return false;
//        }
//
//        boolean rc = tableConfiguration.isCountByExampleStatementEnabled();
//
//        return rc;
        return false;
    }

    @Override
    public boolean generateUpdateByExampleSelective() {
//        if (isModelOnly) {
//            return false;
//        }
//
//        boolean rc = tableConfiguration.isUpdateByExampleStatementEnabled();
//
//        return rc;
        return false;
    }

    @Override
    public boolean generateUpdateByExampleWithoutBLOBs() {
//        if (isModelOnly) {
//            return false;
//        }
//
//        boolean rc = tableConfiguration.isUpdateByExampleStatementEnabled()
//                && (introspectedTable.hasPrimaryKeyColumns() || introspectedTable
//                .hasBaseColumns());
//
//        return rc;
        return false;
    }

    @Override
    public boolean generateUpdateByExampleWithBLOBs() {
//        if (isModelOnly) {
//            return false;
//        }
//
//        boolean rc = tableConfiguration.isUpdateByExampleStatementEnabled()
//                && introspectedTable.hasBLOBColumns();
//
//        return rc;
        return false;
    }

    @Override
    public IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    @Override
    public boolean generateBaseColumnList() {
        if (isModelOnly) {
            return false;
        }

        return generateSelectByPrimaryKey()
                || generateSelectByExampleWithoutBLOBs();
    }

    @Override
    public boolean generateBlobColumnList() {
//        if (isModelOnly) {
//            return false;
//        }
//
//        return introspectedTable.hasBLOBColumns()
//                && (tableConfiguration.isSelectByExampleStatementEnabled() || tableConfiguration
//                .isEnableSelectByPrimaryKey());
        return false;
    }

    @Override
    public boolean generateJavaClient() {
        return !isModelOnly;
    }
}
