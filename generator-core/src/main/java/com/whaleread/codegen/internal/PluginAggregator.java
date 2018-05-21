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
package com.whaleread.codegen.internal;

import com.whaleread.codegen.api.*;
import com.whaleread.codegen.api.dom.java.Field;
import com.whaleread.codegen.api.dom.java.Interface;
import com.whaleread.codegen.api.dom.java.Method;
import com.whaleread.codegen.api.dom.java.TopLevelClass;
import com.whaleread.codegen.api.dom.xml.Document;
import com.whaleread.codegen.api.dom.xml.XmlElement;
import com.whaleread.codegen.config.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class is for internal use only. It contains a list of plugins for the
 * current context and is used to aggregate plugins together. This class
 * implements the rule that if any plugin returns "false" from a method, then no
 * other plugin is called.
 * <p>
 * <p>This class does not follow the normal plugin lifecycle and should not be
 * subclassed by clients.
 *
 * @author Jeff Butler
 */
public final class PluginAggregator implements Plugin {
    private List<Plugin> plugins;

    public PluginAggregator() {
        plugins = new ArrayList<Plugin>();
    }

    public void addPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    @Override
    public void setContext(Context context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setProperties(Properties properties) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean validate(List<String> warnings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass tlc,
                                                 IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelBaseRecordClassGenerated(tlc, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass tlc,
                                                      IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelRecordWithBLOBsClassGenerated(tlc,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element,
                                                        IntrospectedTable table) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapCountByExampleElementGenerated(element, table)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element,
                                                         IntrospectedTable table) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapDeleteByExampleElementGenerated(element, table)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element,
                                                            IntrospectedTable table) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin
                    .sqlMapDeleteByPrimaryKeyElementGenerated(element, table)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass tlc,
                                              IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelExampleClassGenerated(tlc, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles(
            IntrospectedTable introspectedTable) {
        List<GeneratedFile> answer = new ArrayList<GeneratedFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedFile> temp = plugin
                    .contextGenerateAdditionalFiles(introspectedTable);
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    @Override
    public List<GeneratedFile> contextGenerateAdditionalFiles() {
        List<GeneratedFile> answer = new ArrayList<GeneratedFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedFile> temp = plugin
                    .contextGenerateAdditionalFiles();
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass tlc,
                                                 IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelPrimaryKeyClassGenerated(tlc, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapResultMapWithoutBLOBsElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element,
                                                            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapExampleWhereClauseElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element,
                                                IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin
                    .sqlMapInsertElementGenerated(element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element,
                                                            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapResultMapWithBLOBsElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapSelectByExampleWithoutBLOBsElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapSelectByExampleWithBLOBsElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element,
                                                            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapSelectByPrimaryKeyElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap,
                                   IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapGenerated(sqlMap, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByExampleSelectiveElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByExampleWithBLOBsElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientBasicCountMethodGenerated(Method method, Interface interfaze,
                                                   IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientBasicCountMethodGenerated(method, interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientBasicDeleteMethodGenerated(Method method, Interface interfaze,
                                                    IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientBasicDeleteMethodGenerated(method, interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientBasicInsertMethodGenerated(Method method, Interface interfaze,
                                                    IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientBasicInsertMethodGenerated(method, interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientBasicSelectManyMethodGenerated(Method method, Interface interfaze,
                                                        IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientBasicSelectManyMethodGenerated(method, interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientBasicSelectOneMethodGenerated(Method method, Interface interfaze,
                                                       IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientBasicSelectOneMethodGenerated(method, interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientBasicUpdateMethodGenerated(Method method, Interface interfaze,
                                                    IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientBasicUpdateMethodGenerated(method, interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method,
                                                       Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientCountByExampleMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method,
                                                       TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientCountByExampleMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method,
                                                        Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByExampleMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method,
                                                        TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByExampleMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
                                                           Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
                                                           TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByPrimaryKeyMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
                                               IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInsertMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method,
                                               TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInsertMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientGenerated(Interface interfaze,
                                   TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientGenerated(interfaze, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method,
                                                  TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectAllMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method,
                                                  Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectAllMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
                                                                 Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByExampleWithBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
                                                                 TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByExampleWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method,
                                                                    Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByExampleWithoutBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method,
                                                                    TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByExampleWithoutBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
                                                           Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByPrimaryKeyMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
                                                           TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByPrimaryKeyMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method,
                                                                 Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByExampleSelectiveMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method,
                                                                 TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByExampleSelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method,
                                                                 Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByExampleWithBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method,
                                                                 TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByExampleWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method,
                                                                    Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByExampleWithoutBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method,
                                                                    TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByExampleWithoutBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
                                                                    Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeySelectiveMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
                                                                    TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeySelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method,
                                                                    Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method,
                                                                    TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
            Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
                    method, interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
                    method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document,
                                           IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapDocumentGenerated(document, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean modelFieldGenerated(Field field,
                                       TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable,
                                       Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelFieldGenerated(field, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelGetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelSetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element,
                                                         IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapInsertSelectiveElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method,
                                                        Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInsertSelectiveMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method,
                                                        TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInsertSelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        for (Plugin plugin : plugins) {
            plugin.initialized(introspectedTable);
        }
    }

    @Override
    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element,
                                                        IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapBaseColumnListElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapBlobColumnListElementGenerated(XmlElement element,
                                                        IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapBlobColumnListElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerGenerated(TopLevelClass topLevelClass,
                                     IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerGenerated(topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerApplyWhereMethodGenerated(Method method,
                                                     TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerApplyWhereMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerCountByExampleMethodGenerated(Method method,
                                                         TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerCountByExampleMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerDeleteByExampleMethodGenerated(Method method,
                                                          TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerDeleteByExampleMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerInsertSelectiveMethodGenerated(Method method,
                                                          TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerInsertSelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerSelectByExampleWithBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerSelectByExampleWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerSelectByExampleWithoutBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerSelectByExampleWithoutBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerUpdateByExampleSelectiveMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerUpdateByExampleSelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerUpdateByExampleWithBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerUpdateByExampleWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerUpdateByExampleWithoutBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerUpdateByExampleWithoutBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean providerUpdateByPrimaryKeySelectiveMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerUpdateByPrimaryKeySelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element,
                                                   IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapSelectAllElementGenerated(element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientDeleteByCriteriaMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectByCriteriaMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientSelectByCriteriaSubclassMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByCriteriaSubclassMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientCountByCriteriaMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientCountByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientInjectMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInjectMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean clientRowMapperFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientRowMapperFieldGenerated(field, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean serviceFindByIdMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.serviceFindByIdMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean serviceCountByCriteriaMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.serviceCountByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean serviceFindByCriteriaMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.serviceFindByCriteriaMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean serviceSaveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.serviceSaveMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean serviceUpdateMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.serviceUpdateMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean serviceDeleteByIdMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.serviceDeleteByIdMethodGenerated(method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    @Override
    public boolean dtoClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.dtoClassGenerated(topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
}
