package com.whaleread.codegen.api.ddl;

import java.io.Reader;
import java.util.Map;

/**
 * Created by Dolphin on 2018/1/17
 */
public interface DdlParser {
    /**
     * Caller is respond to close the Reader
     *
     * @param defaultCatalog use this as the catalog if the table is not qualified by a catalog.
     * @param defaultSchema  use this as the schema if the table is not qualified by a schema.
     * @param in             Reader where read ddl from
     */
    Map<String, Map<String, Map<String, DdlTableDefinition>>> parse(String defaultCatalog, String defaultSchema, Reader in);
}
