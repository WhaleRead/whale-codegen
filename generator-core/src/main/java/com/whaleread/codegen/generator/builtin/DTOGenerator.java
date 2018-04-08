package com.whaleread.codegen.generator.builtin;

import com.whaleread.codegen.api.CommentGenerator;
import com.whaleread.codegen.api.FullyQualifiedTable;
import com.whaleread.codegen.api.dom.java.CompilationUnit;
import com.whaleread.codegen.api.dom.java.FullyQualifiedJavaType;
import com.whaleread.codegen.api.dom.java.JavaVisibility;
import com.whaleread.codegen.api.dom.java.TopLevelClass;
import com.whaleread.codegen.generator.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.whaleread.codegen.internal.util.messages.Messages.getString;

/**
 * Created by Dolphin on 2018/1/18
 */
public class DTOGenerator extends AbstractJavaGenerator {
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        if (!introspectedTable.getTableConfiguration().isEnableDTO()) {
            return Collections.emptyList();
        }
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.6", table.toString())); //$NON-NLS-1$
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getDtoType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);
        commentGenerator.addGeneratedAnnotation(topLevelClass, topLevelClass.getImportedTypes());

        FullyQualifiedJavaType modelType = new FullyQualifiedJavaType(introspectedTable.getModelType());
        topLevelClass.setSuperClass(modelType);
        topLevelClass.addImportedType(modelType);

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().modelBaseRecordClassGenerated(topLevelClass,
                introspectedTable)) {
            answer.add(topLevelClass);
        }
        return answer;
    }
}
