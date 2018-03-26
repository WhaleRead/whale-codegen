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
package com.whaleread.codegen.maven;

import com.whaleread.codegen.api.ShellCallback;
import com.whaleread.codegen.api.WhaleGenerator;
import com.whaleread.codegen.config.Configuration;
import com.whaleread.codegen.config.xml.ConfigurationParser;
import com.whaleread.codegen.exception.InvalidConfigurationException;
import com.whaleread.codegen.exception.XMLParserException;
import com.whaleread.codegen.internal.ObjectFactory;
import com.whaleread.codegen.internal.util.ClassloaderUtility;
import com.whaleread.codegen.internal.util.StringUtility;
import com.whaleread.codegen.internal.util.messages.Messages;
import com.whaleread.codegen.logging.LogFactory;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Goal which generates MyBatis/iBATIS artifacts.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        requiresDependencyResolution = ResolutionScope.TEST)
public class WhaleGeneratorMojo extends AbstractMojo {

    private ThreadLocal<ClassLoader> savedClassloader = new ThreadLocal<ClassLoader>();

    /**
     * Maven Project.
     */
    @Parameter(property = "project", required = true, readonly = true)
    private MavenProject project;

    /**
     * Output Directory.
     */
    @Parameter(property = "whale.generator.outputDirectory",
            defaultValue = "${project.build.directory}/generated-sources/whale-codegen", required = true)
    private File outputDirectory;

    /**
     * Location of the configuration file.
     */
    @Parameter(property = "whale.generator.configurationFile",
            defaultValue = "${project.basedir}/src/main/codegen/whale-generator.xml", required = true)
    private File configurationFile;

    /**
     * Specifies whether the mojo writes progress messages to the log.
     */
    @Parameter(property = "whale.generator.verbose", defaultValue = "false")
    private boolean verbose;

    /**
     * Specifies whether the mojo overwrites existing Java files. Default is false.
     * <br>
     */
    @Parameter(property = "whale.generator.overwrite", defaultValue = "false")
    private boolean overwrite;

    /**
     * Specifies whether the mojo merge existing Java files. Default is false.
     * <br>
     */
    @Parameter(property = "whale.generator.merge", defaultValue = "false")
    private boolean merge;

    /**
     * Location of a SQL script file to run before generating code. If null,
     * then no script will be run. If not null, then jdbcDriver, jdbcURL must be
     * supplied also, and jdbcUserId and jdbcPassword may be supplied.
     */
    @Parameter(property = "whale.generator.sqlScript")
    private String sqlScript;

    /**
     * JDBC Driver to use if a sql.script.file is specified.
     */
    @Parameter(property = "whale.generator.jdbcDriver")
    private String jdbcDriver;

    /**
     * JDBC URL to use if a sql.script.file is specified.
     */
    @Parameter(property = "whale.generator.jdbcURL")
    private String jdbcURL;

    /**
     * JDBC user ID to use if a sql.script.file is specified.
     */
    @Parameter(property = "whale.generator.jdbcUserId")
    private String jdbcUserId;

    /**
     * JDBC password to use if a sql.script.file is specified.
     */
    @Parameter(property = "whale.generator.jdbcPassword")
    private String jdbcPassword;

    /**
     * Comma delimited list of table names to generate.
     */
    @Parameter(property = "whale.generator.tableNames")
    private String tableNames;

    /**
     * Comma delimited list of contexts to generate.
     */
    @Parameter(property = "whale.generator.contexts")
    private String contexts;

    /**
     * Skip generator.
     */
    @Parameter(property = "whale.generator.skip", defaultValue = "false")
    private boolean skip;

    /**
     * If true, then dependencies in scope compile, provided, and system scopes will be
     * added to the classpath of the generator.  These dependencies will be searched for
     * JDBC drivers, root classes, root interfaces, generator plugins, etc.
     */
    @Parameter(property = "whale.generator.includeCompileDependencies", defaultValue = "false")
    private boolean includeCompileDependencies;

    /**
     * If true, then dependencies in all scopes will be
     * added to the classpath of the generator.  These dependencies will be searched for
     * JDBC drivers, root classes, root interfaces, generator plugins, etc.
     */
    @Parameter(property = "whale.generator.includeAllDependencies", defaultValue = "false")
    private boolean includeAllDependencies;

    @Override
    public void execute() throws MojoExecutionException {
        if (skip) {
            getLog().info("MyBatis generator is skipped.");
            return;
        }

        saveClassLoader();

        LogFactory.setLogFactory(new MavenLogFactory(this));

        calculateClassPath();

        // add resource directories to the classpath.  This is required to support
        // use of a properties file in the build.  Typically, the properties file
        // is in the project's source tree, but the plugin classpath does not
        // include the project classpath.
        List<Resource> resources = project.getResources();
        List<String> resourceDirectories = new ArrayList<String>();
        for (Resource resource : resources) {
            resourceDirectories.add(resource.getDirectory());
        }
        ClassLoader cl = ClassloaderUtility.getCustomClassloader(resourceDirectories);
        ObjectFactory.addExternalClassLoader(cl);

        if (configurationFile == null) {
            throw new MojoExecutionException(
                    Messages.getString("RuntimeError.0")); //$NON-NLS-1$
        }

        List<String> warnings = new ArrayList<String>();

        if (!configurationFile.exists()) {
            throw new MojoExecutionException(Messages.getString(
                    "RuntimeError.1", configurationFile.toString())); //$NON-NLS-1$
        }

        runScriptIfNecessary();

        Set<String> fullyqualifiedTables = new HashSet<String>();
        if (StringUtility.stringHasValue(tableNames)) {
            StringTokenizer st = new StringTokenizer(tableNames, ","); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String s = st.nextToken().trim();
                if (s.length() > 0) {
                    fullyqualifiedTables.add(s);
                }
            }
        }

        Set<String> contextsToRun = new HashSet<String>();
        if (StringUtility.stringHasValue(contexts)) {
            StringTokenizer st = new StringTokenizer(contexts, ","); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String s = st.nextToken().trim();
                if (s.length() > 0) {
                    contextsToRun.add(s);
                }
            }
        }

        try {
            ConfigurationParser cp = new ConfigurationParser(
                    project.getProperties(), warnings);
            if (project.getProperties().getProperty("basedir") == null) {
                project.getProperties().setProperty("basedir", project.getBasedir().getAbsolutePath());
            }
            Configuration config = cp.parseConfiguration(configurationFile);

            ShellCallback callback = new MavenShellCallback(this, overwrite);

            WhaleGenerator generator = new WhaleGenerator(config,
                    callback, warnings);

            generator.generate(new MavenProgressCallback(getLog(),
                    verbose), contextsToRun, fullyqualifiedTables);

        } catch (XMLParserException e) {
            for (String error : e.getErrors()) {
                getLog().error(error);
            }

            throw new MojoExecutionException(e.getMessage());
        } catch (SQLException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (InvalidConfigurationException e) {
            getLog().error(e);
            for (String error : e.getErrors()) {
                getLog().error(error);
            }

            throw new MojoExecutionException(e.getMessage());
        } catch (InterruptedException e) {
            // ignore (will never happen with the DefaultShellCallback)
        }

        for (String error : warnings) {
            getLog().warn(error);
        }

        if (project != null && outputDirectory != null
                && outputDirectory.exists()) {
            project.addCompileSourceRoot(outputDirectory.getAbsolutePath());

            Resource resource = new Resource();
            resource.setDirectory(outputDirectory.getAbsolutePath());
            resource.addInclude("**/*.xml");
            project.addResource(resource);
        }

        restoreClassLoader();
    }

    private void calculateClassPath() throws MojoExecutionException {
        if (includeCompileDependencies || includeAllDependencies) {
            try {
                // add the project compile classpath to the plugin classpath,
                // so that the project dependency classes can be found
                // directly, without adding the classpath to configuration's classPathEntries
                // repeatedly.Examples are JDBC drivers, root classes, root interfaces, etc.
                Set<String> entries = new HashSet<String>();
                if (includeCompileDependencies) {
                    entries.addAll(project.getCompileClasspathElements());
                }

                if (includeAllDependencies) {
                    entries.addAll(project.getTestClasspathElements());
                }

                // remove the output directories (target/classes and target/test-classes)
                // because this mojo runs in the generate-sources phase and
                // those directories have not been created yet (typically)
                entries.remove(project.getBuild().getOutputDirectory());
                entries.remove(project.getBuild().getTestOutputDirectory());

                ClassLoader contextClassLoader = ClassloaderUtility.getCustomClassloader(entries);
                Thread.currentThread().setContextClassLoader(contextClassLoader);
            } catch (DependencyResolutionRequiredException e) {
                throw new MojoExecutionException("Dependency Resolution Required", e);
            }
        }
    }

    private void runScriptIfNecessary() throws MojoExecutionException {
        if (sqlScript == null) {
            return;
        }

        SqlScriptRunner scriptRunner = new SqlScriptRunner(sqlScript,
                jdbcDriver, jdbcURL, jdbcUserId, jdbcPassword);
        scriptRunner.setLog(getLog());
        scriptRunner.executeScript();
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    private void saveClassLoader() {
        savedClassloader.set(Thread.currentThread().getContextClassLoader());
    }

    private void restoreClassLoader() {
        Thread.currentThread().setContextClassLoader(savedClassloader.get());
    }

    boolean isMergeEnabled() {
        return merge;
    }
}
