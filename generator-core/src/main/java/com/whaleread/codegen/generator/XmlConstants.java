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
package com.whaleread.codegen.generator;

/**
 * Constants for iBatis/MyBatis XML IDs.
 *
 * @author Jeff Butler
 */
public class XmlConstants {

    /**
     * Utility Class, no instances.
     */
    private XmlConstants() {
        super();
    }

    public static final String WHALE_GENERATOR_CONFIG_SYSTEM_ID =
            "http://whaleread.com/dtd/whale-generator-config_1_0.dtd"; //$NON-NLS-1$

    public static final String WHALE_GENERATOR_CONFIG_PUBLIC_ID =
            "-//whaleread.com//DTD Whale Generator Configuration 1.0//EN"; //$NON-NLS-1$
}
