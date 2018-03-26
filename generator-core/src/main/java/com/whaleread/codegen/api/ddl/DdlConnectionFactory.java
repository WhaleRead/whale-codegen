package com.whaleread.codegen.api.ddl;

import com.whaleread.codegen.api.ConnectionFactory;
import com.whaleread.codegen.internal.ObjectFactory;
import com.whaleread.codegen.utils.IOUtility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import static com.whaleread.codegen.internal.util.StringUtility.stringHasValue;

/**
 * properties:
 * <ul>
 * <li>type: DdlParser type name</li>
 * <li>path: ddl file path</li>
 * <li>catalog: default catalog</li>
 * <li>schema: default schema</li>
 * <li>charset: ddl file's charset</li>
 * </ul>
 * Created by Dolphin on 2018/1/17
 */
public class DdlConnectionFactory implements ConnectionFactory {
    /**
     * DdlParser type name
     */
    private DdlParser parser;
    /**
     * ddl file path
     */
    private String path;
    /**
     * default catalog
     */
    private String catalog;
    /**
     * default schema
     */
    private String schema;
    /**
     * ddl file's charset
     */
    private String charset = "UTF-8";

    @Override
    public Connection getConnection() {
        InputStreamReader reader = null;
        try {
            InputStream is;
            if (path.startsWith("classpath:")) {
                is = ObjectFactory.getResource(path).openStream();
            } else {
                is = new FileInputStream(path);
            }
            reader = new InputStreamReader(is, charset);
            Map<String, Map<String, Map<String, DdlTableDefinition>>> metadata = parser.parse(catalog, schema, reader);
            return new DdlConnection(new DdlDatabaseMetaData(metadata));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ddl!", e);
        } finally {
            IOUtility.silentClose(reader);
        }
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        try {
            //noinspection unchecked
            Class<? extends DdlParser> clazz = (Class<? extends DdlParser>) ObjectFactory.internalClassForName(properties.getProperty("parser"));
            this.parser = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("DdlParser[" + properties.getProperty("parser") + "] not found!", e);
        }
        this.path = properties.getProperty("path");
        this.catalog = properties.getProperty("catalog");
        this.schema = properties.getProperty("schema");
        if (stringHasValue(properties.getProperty("charset"))) {
            this.charset = properties.getProperty("charset");
        }
    }
}
