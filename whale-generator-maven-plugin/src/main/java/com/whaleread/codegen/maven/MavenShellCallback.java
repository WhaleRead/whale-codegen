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

import com.whaleread.codegen.exception.ShellException;
import com.whaleread.codegen.internal.DefaultShellCallback;
import com.whaleread.codegen.internal.util.messages.Messages;

import java.io.File;
import java.util.StringTokenizer;

/**
 * Shell callback that calculates the Maven output directory.
 *
 * @author Jeff Butler
 */
public class MavenShellCallback extends DefaultShellCallback {
    private WhaleGeneratorMojo mybatisGeneratorMojo;

    public MavenShellCallback(WhaleGeneratorMojo mybatisGeneratorMojo, boolean overwrite) {
        super(overwrite);
        this.mybatisGeneratorMojo = mybatisGeneratorMojo;
    }

    @Override
    public File getDirectory(String targetProject, String targetPackage)
            throws ShellException {
        if (!"MAVEN".equals(targetProject)) {
            return super.getDirectory(targetProject, targetPackage);
        }

        // targetProject is the output directory from the MyBatis generator
        // Mojo. It will be created if necessary
        //
        // targetPackage is interpreted as a sub directory, but in package
        // format (with dots instead of slashes).  The sub directory will be created
        // if it does not already exist

        File project = mybatisGeneratorMojo.getOutputDirectory();
        if (!project.exists()) {
            project.mkdirs();
        }

        if (!project.isDirectory()) {
            throw new ShellException(Messages.getString("Warning.9", //$NON-NLS-1$
                    project.getAbsolutePath()));
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, "."); //$NON-NLS-1$
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                throw new ShellException(Messages.getString("Warning.10", //$NON-NLS-1$
                        directory.getAbsolutePath()));
            }
        }

        return directory;
    }

    @Override
    public boolean isMergeSupported() {
        return mybatisGeneratorMojo.isMergeEnabled();
    }
}
