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
package com.whaleread.codegen.api;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.printer.PrettyPrinter;
import com.whaleread.codegen.api.dom.java.CompilationUnit;
import com.whaleread.codegen.exception.ShellException;

import javax.annotation.Generated;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * The Class GeneratedJavaFile.
 *
 * @author Jeff Butler
 */
public class GeneratedJavaFile extends GeneratedFile {

    /**
     * The compilation unit.
     */
    private CompilationUnit compilationUnit;

    /**
     * The file encoding.
     */
    private String fileEncoding;

    /**
     * The java formatter.
     */
    private JavaFormatter javaFormatter;

    /**
     * Default constructor.
     *
     * @param compilationUnit the compilation unit
     * @param targetProject   the target project
     * @param fileEncoding    the file encoding
     * @param javaFormatter   the java formatter
     */
    public GeneratedJavaFile(CompilationUnit compilationUnit,
                             String targetProject,
                             String fileEncoding,
                             JavaFormatter javaFormatter) {
        super(targetProject);
        this.compilationUnit = compilationUnit;
        this.fileEncoding = fileEncoding;
        this.javaFormatter = javaFormatter;
    }

    /**
     * Instantiates a new generated java file.
     *
     * @param compilationUnit the compilation unit
     * @param targetProject   the target project
     * @param javaFormatter   the java formatter
     */
    public GeneratedJavaFile(CompilationUnit compilationUnit,
                             String targetProject,
                             JavaFormatter javaFormatter) {
        this(compilationUnit, targetProject, null, javaFormatter);
    }

    /* (non-Javadoc)
     * @see com.whaleread.codegen.api.GeneratedFile#getFormattedContent()
     */
    @Override
    public String getFormattedContent() {
        return javaFormatter.getFormattedContent(compilationUnit);
    }

    /* (non-Javadoc)
     * @see com.whaleread.codegen.api.GeneratedFile#getFileName()
     */
    @Override
    public String getFileName() {
        return compilationUnit.getType().getShortNameWithoutTypeArguments() + ".java"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see com.whaleread.codegen.api.GeneratedFile#getTargetPackage()
     */
    @Override
    public String getTargetPackage() {
        return compilationUnit.getType().getPackageName();
    }

    /**
     * This method is required by the Eclipse Java merger. If you are not
     * running in Eclipse, or some other system that implements the Java merge
     * function, you may return null from this method.
     *
     * @return the CompilationUnit associated with this file, or null if the
     * file is not mergeable.
     */
    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    /**
     * A Java file is mergeable if the getCompilationUnit() method returns a valid compilation unit.
     *
     * @return true, if is mergeable
     */
    @Override
    public boolean isMergeable() {
        return true;
    }

    /**
     * Gets the file encoding.
     *
     * @return the file encoding
     */
    public String getFileEncoding() {
        return fileEncoding;
    }

    @Override
    public String merge(File target) throws ShellException {
        String source = getFormattedContent();
        try {
            com.github.javaparser.ast.CompilationUnit targetCompilationUnit = JavaParser.parse(target);
            com.github.javaparser.ast.CompilationUnit sourceCompilationUnit = JavaParser.parse(source);
            mergeImports(sourceCompilationUnit.getImports(), targetCompilationUnit.getImports());
            mergeTypes(sourceCompilationUnit.getTypes(), targetCompilationUnit.getTypes());
            PrettyPrinter printer = new PrettyPrinter();
            return printer.print(sourceCompilationUnit);
        } catch (FileNotFoundException e) {
            throw new ShellException(e);
        }
    }

    private void mergeImports(NodeList<ImportDeclaration> dest, NodeList<ImportDeclaration> target) {
        for (ImportDeclaration importDeclaration : target) {
            if (!dest.contains(importDeclaration)) {
                dest.add(importDeclaration);
            }
        }
    }

    private void mergeTypes(NodeList<TypeDeclaration<?>> dest, NodeList<TypeDeclaration<?>> target) {
        for (TypeDeclaration type : target) {
            Optional<Node> parentNode = type.getParentNode();
            // 有非CompilationUnit类型的父元素且带Generated注解的，跳过
            if (parentNode.isPresent() && !com.github.javaparser.ast.CompilationUnit.class.isAssignableFrom(parentNode.get().getClass()) && type.getAnnotationByClass(Generated.class).isPresent()) {
                continue;
            }
            TypeDeclaration d = findFromNodeList(dest, n -> type.getName().equals(n.getName()));
            if (d == null) {
                dest.add(type.clone());
            } else {
                mergeType(d, type);
            }
        }
    }

    private void mergeType(TypeDeclaration<?> dest, TypeDeclaration<?> target) {
        if (dest.getClass().equals(ClassOrInterfaceDeclaration.class)) {
            mergeClassOrInterfaceDeclaration((ClassOrInterfaceDeclaration) dest, (ClassOrInterfaceDeclaration) target);
            return;
        }
//                if(d.getClass().equals(AnnotationDeclaration.class)) {
//                    continue;
//                }
//                if(d.getClass().equals(EnumDeclaration.class)) {
//
//                }
        dest.replace(dest, target.clone());
    }

    private void mergeClassOrInterfaceDeclaration(ClassOrInterfaceDeclaration dest, ClassOrInterfaceDeclaration target) {
        if (target.getExtendedTypes().size() > 0 && dest.getExtendedTypes().size() == 0) {
            dest.setExtendedType(0, target.getExtendedTypes(0));
        }

        if (target.getImplementedTypes().size() > 0) {
            mergeClassOrInterfaceTypes(dest.getImplementedTypes(), target.getImplementedTypes());
        }
        mergeAnnotations(dest, target);
        mergeFields(dest, target);
        mergeConstructors(dest, target);
        mergeMethods(dest, target);
        mergeInnerClasses(dest, target);
    }

    private void mergeClassOrInterfaceTypes(NodeList<ClassOrInterfaceType> dest, NodeList<ClassOrInterfaceType> target) {
        for (ClassOrInterfaceType t : target) {
            if (!dest.contains(t)) {
                dest.add(t.clone());
            }
        }
    }

    private void mergeAnnotations(ClassOrInterfaceDeclaration dest, ClassOrInterfaceDeclaration target) {
        for(AnnotationExpr t : target.getAnnotations()) {
            if(t.getNameAsString().equals("Generated")) {
                continue;
            }
            Optional<AnnotationExpr> d = dest.findFirst(AnnotationExpr.class, m -> m.getName().equals(t.getName()));
            if (d.isPresent()) {
                dest.getAnnotations().replace(d.get(), t.clone());
            } else {
                dest.getAnnotations().add(t.clone());
            }
        }
        for (AnnotationDeclaration t : target.findAll(AnnotationDeclaration.class)) {
            Optional<AnnotationDeclaration> d = dest.findFirst(AnnotationDeclaration.class, m -> m.getName().equals(t.getName()));
            if (d.isPresent()) {
                dest.replace(d.get(), t.clone());
            } else {
                dest.addMember(t.clone());
            }
        }
    }

    private void mergeFields(ClassOrInterfaceDeclaration dest, ClassOrInterfaceDeclaration target) {
        for (FieldDeclaration t : target.findAll(FieldDeclaration.class, m -> !m.getAnnotationByClass(Generated.class).isPresent())) {
            Optional<FieldDeclaration> d = dest.findFirst(FieldDeclaration.class, m -> m.getVariables().get(0).getName().equals(t.getVariables().get(0).getName()));
            if (d.isPresent()) {
                dest.replace(d.get(), t.clone());
            } else {
                dest.addMember(t.clone());
            }
        }
    }

    private void mergeConstructors(ClassOrInterfaceDeclaration dest, ClassOrInterfaceDeclaration target) {
        for (ConstructorDeclaration t : target.findAll(ConstructorDeclaration.class, m -> !m.getAnnotationByClass(Generated.class).isPresent())) {
            Optional<ConstructorDeclaration> d = dest.findFirst(ConstructorDeclaration.class, m -> m.getName().equals(t.getName()) && parametersEquals(m.getParameters(), t.getParameters()));
            if (d.isPresent()) {
                dest.replace(d.get(), t.clone());
            } else {
                dest.addMember(t.clone());
            }
        }
    }

    private void mergeMethods(ClassOrInterfaceDeclaration dest, ClassOrInterfaceDeclaration target) {
        for (MethodDeclaration t : target.findAll(MethodDeclaration.class, m -> !m.getAnnotationByClass(Generated.class).isPresent())) {
            Optional<MethodDeclaration> d = dest.findFirst(MethodDeclaration.class, m -> m.getName().equals(t.getName()) && parametersEquals(m.getParameters(), t.getParameters()));
            if (d.isPresent()) {
                dest.replace(d.get(), t.clone());
            } else {
                dest.addMember(t.clone());
            }
        }
    }

    private void mergeInnerClasses(ClassOrInterfaceDeclaration dest, ClassOrInterfaceDeclaration target) {
        for (Node n : target.getChildNodes()) {
            if (!TypeDeclaration.class.isAssignableFrom(n.getClass())) {
                continue;
            }
            TypeDeclaration t = (TypeDeclaration) n;
            if (t.getAnnotationByClass(Generated.class).isPresent()) {
                continue;
            }
            Optional<TypeDeclaration> d = dest.findFirst(TypeDeclaration.class, m -> m.getName().equals(t.getName()));
            if (d.isPresent()) {
                mergeType(d.get(), t);
            } else {
                dest.addMember(t.clone());
            }
        }
    }

    private <T extends Node> T findFromNodeList(NodeList<T> list, Predicate<T> predicate) {
        for (T t : list) {
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    private boolean parametersEquals(NodeList<Parameter> left, NodeList<Parameter> right) {
        if (left.size() != right.size()) {
            return false;
        }
        for (int i = 0; i < left.size(); i++) {
            if (!parameterEquals(left.get(i), right.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean parameterEquals(Parameter a, Parameter b) {
        return a.getType().equals(b.getType());
    }
}
