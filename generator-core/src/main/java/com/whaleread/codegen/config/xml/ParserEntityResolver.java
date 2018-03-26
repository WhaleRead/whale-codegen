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
package com.whaleread.codegen.config.xml;

import com.whaleread.codegen.generator.XmlConstants;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.InputStream;

/**
 * @author Jeff Butler
 */
public class ParserEntityResolver implements EntityResolver {

    /**
     *
     */
    public ParserEntityResolver() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String,
     * java.lang.String)
     */
    @Override
    public InputSource resolveEntity(String publicId, String systemId) {
        if (XmlConstants.WHALE_GENERATOR_CONFIG_PUBLIC_ID.equalsIgnoreCase(publicId)) {
            InputStream is = getClass().getClassLoader().getResourceAsStream(
                    "com/whaleread/codegen/config/xml/whale-generator-config_1_0.dtd"); //$NON-NLS-1$
            InputSource ins = new InputSource(is);

            return ins;
        } else {
            return null;
        }
    }
}
