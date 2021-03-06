/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.whaleread.codegen.internal.rules;

import com.whaleread.codegen.api.IntrospectedTable;

/**
 * This class encapsulates all the code generation rules for a table using the
 * flat model.
 * 
 * @author Jeff Butler
 * 
 */
public class FlatModelRules extends BaseRules {

    /**
     * Instantiates a new flat model rules.
     *
     * @param introspectedTable
     *            the introspected table
     */
    public FlatModelRules(IntrospectedTable introspectedTable) {
        super(introspectedTable);
    }

    /**
     * We never generate a primary key in the flat model.
     * 
     * @return true if the primary key should be generated
     */
    @Override
    public boolean generatePrimaryKeyClass() {
        return false;
    }

    /**
     * We always generate a base record in the flat model.
     * 
     * @return true if the class should be generated
     */
    @Override
    public boolean generateBaseRecordClass() {
        return true;
    }

    /**
     * We never generate a record with BLOBs class in the flat model.
     * 
     * @return true if the record with BLOBs class should be generated
     */
    @Override
    public boolean generateRecordWithBLOBsClass() {
        return false;
    }

    @Override
    public boolean generateUpdateByPrimaryKeyWithoutBLOBs() {
        return false;
    }

    @Override
    public boolean generateUpdateByPrimaryKeyWithBLOBs() {
        return false;
    }

    @Override
    public boolean generateUpdateByPrimaryKeySelective() {
        return false;
    }

    @Override
    public boolean generateSQLExampleWhereClause() {
        return false;
    }

    @Override
    public boolean generateMyBatis3UpdateByExampleWhereClause() {
        return false;
    }

    @Override
    public boolean generateExampleClass() {
        return false;
    }
}
