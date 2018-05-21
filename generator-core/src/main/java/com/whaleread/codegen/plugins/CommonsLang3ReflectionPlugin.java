package com.whaleread.codegen.plugins;

import com.whaleread.codegen.api.IntrospectedTable;
import com.whaleread.codegen.api.PluginAdapter;
import com.whaleread.codegen.api.dom.java.FullyQualifiedJavaType;
import com.whaleread.codegen.api.dom.java.JavaVisibility;
import com.whaleread.codegen.api.dom.java.Method;
import com.whaleread.codegen.api.dom.java.Parameter;
import com.whaleread.codegen.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

/**
 * @author Dolphin
 */
public class CommonsLang3ReflectionPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Properties props = getProperties();
        if (props.getProperty("enableToString", "true").equals("true")) {
            addToString(topLevelClass, introspectedTable);
        }
        if (props.getProperty("enableEquals", "true").equals("true")) {
            addEquals(topLevelClass, introspectedTable);
        }
        if (props.getProperty("enableHashCode", "true").equals("true")) {
            addHashCode(topLevelClass, introspectedTable);
        }
        return true;
    }

    private void addToString(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Method method = new Method("toString");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@Override");
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.addBodyLine("return ReflectionToStringBuilder.toString(this);");
        topLevelClass.addImportedType("org.apache.commons.lang3.builder.ReflectionToStringBuilder");
        context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addEquals(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Method method = new Method("equals");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@Override");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "obj"));
        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        method.addBodyLine("return EqualsBuilder.reflectionEquals(this, obj, false);");
        topLevelClass.addImportedType("org.apache.commons.lang3.builder.EqualsBuilder");
        context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }

    private void addHashCode(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Method method = new Method("hashCode");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@Override");
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.addBodyLine("return HashCodeBuilder.reflectionHashCode(this, false);");
        topLevelClass.addImportedType("org.apache.commons.lang3.builder.HashCodeBuilder");
        context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, topLevelClass.getImportedTypes());
        topLevelClass.addMethod(method);
    }
}
