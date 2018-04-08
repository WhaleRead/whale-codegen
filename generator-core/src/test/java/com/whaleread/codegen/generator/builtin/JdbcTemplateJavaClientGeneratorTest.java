package com.whaleread.codegen.generator.builtin;

import com.whaleread.codegen.IntrospectedTableWhaleImpl;
import com.whaleread.codegen.api.FullyQualifiedTable;
import com.whaleread.codegen.api.IntrospectedColumn;
import com.whaleread.codegen.api.IntrospectedTable;
import com.whaleread.codegen.api.VerboseProgressCallback;
import com.whaleread.codegen.api.dom.java.CompilationUnit;
import com.whaleread.codegen.api.dom.java.FullyQualifiedJavaType;
import com.whaleread.codegen.config.*;
import com.whaleread.codegen.internal.types.JdbcTypeNameTranslator;
import org.junit.Test;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dolphin on 2018/3/23
 */
public class JdbcTemplateJavaClientGeneratorTest {

    @Test
    public void getCompilationUnits() {
        JdbcTemplateJavaClientGenerator modelGenerator = new JdbcTemplateJavaClientGenerator();
        modelGenerator.setProgressCallback(new VerboseProgressCallback());
        Context context = new Context();
        context.initPlugins(new ArrayList<>());
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.addProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE, "true");
        commentGeneratorConfiguration.addProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS, "true");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
        BuiltInGeneratorConfiguration builtInGeneratorConfiguration = new BuiltInGeneratorConfiguration();
        builtInGeneratorConfiguration.setTargetPackage("com.whaleread");
//        builtInGeneratorConfiguration.setTargetProject("E:/data/codegen/");
        context.setBuiltInGeneratorConfiguration(builtInGeneratorConfiguration);
        modelGenerator.setContext(context);
        IntrospectedTable introspectedTable = new IntrospectedTableWhaleImpl();
        introspectedTable.setContext(context);
        TableConfiguration tc = new TableConfiguration(context);
        tc.setEnableDTO(true);
        introspectedTable.setTableConfiguration(tc);
        introspectedTable.setRemarks("测试表");
        FullyQualifiedTable fullyQualifiedTable = new FullyQualifiedTable("novel", null, "foo", null, "f", true, null, null, null, false, null, context);
        introspectedTable.setFullyQualifiedTable(fullyQualifiedTable);
        IntrospectedColumn column = new IntrospectedColumn();
        column.setContext(context);
        column.setIntrospectedTable(introspectedTable);
        column.setActualColumnName("id");
        column.setJdbcTypeName("BIGINT");
        column.setJdbcType(JdbcTypeNameTranslator.getJdbcType(column.getJdbcTypeName()));
        column.setJavaProperty("id");
        column.setFullyQualifiedJavaType(new FullyQualifiedJavaType("java.lang.Long"));
        column.setRemarks("编号");
        introspectedTable.addColumn(column);
        column = new IntrospectedColumn();
        column.setContext(context);
        column.setIntrospectedTable(introspectedTable);
        column.setActualColumnName("name");
        column.setJdbcTypeName(JDBCType.VARCHAR.getName());
        column.setJdbcType(JDBCType.VARCHAR.getVendorTypeNumber());
        column.setJavaProperty("name");
        column.setFullyQualifiedJavaType(new FullyQualifiedJavaType("java.lang.String"));
        column.setRemarks("名称");
        introspectedTable.addColumn(column);
        introspectedTable.addPrimaryKeyColumn("id");
        modelGenerator.setIntrospectedTable(introspectedTable);
        introspectedTable.initialize();
        List<CompilationUnit> compilationUnits = modelGenerator.getCompilationUnits();
        System.out.println(compilationUnits.get(0).getFormattedContent());
    }
}