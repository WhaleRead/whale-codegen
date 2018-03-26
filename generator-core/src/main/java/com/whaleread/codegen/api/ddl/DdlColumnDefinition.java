package com.whaleread.codegen.api.ddl;

import com.whaleread.codegen.db.ResultSetRow;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Column definition read from database metadata
 * Created by dolphin on 2018/1/13
 */
@Getter
@Setter
public class DdlColumnDefinition implements ResultSetRow {
    private final String catalog;
    private final String schema;
    private final String table;
    private final String name;
    private boolean quoted;
    private Integer nullable;
    /**
     * {@link java.sql.Types}
     */
    private int type;
    private String originType;
    private int size;
    private int scale;
    private String remarks;
    private String defaultValue;
    private short keySequence;
    private boolean autoIncrement;
    private boolean generatedColumn;
    private int originalPosition;
    private boolean primaryKey;
    private String key;
    private List<String> enums;

    public DdlColumnDefinition(String catalog, String schema, String table, String name) {
        this.catalog = catalog;
        this.schema = schema;
        this.table = table;
        this.name = name;
    }

    @Override
    public Object get(String label) {
        if ("DATA_TYPE".equals(label)) {
            return type;
        }
        if ("COLUMN_SIZE".equals(label)) {
            return size;
        }
        if ("COLUMN_NAME".equals(label)) {
            return name;
        }
        if ("NULLABLE".equals(label)) {
            return nullable == null ? 0 : nullable;
        }
        if ("DECIMAL_DIGITS".equals(label)) {
            return scale;
        }
        if ("REMARKS".equals(label)) {
            return remarks;
        }
        if ("COLUMN_DEF".equals(label)) {
            return defaultValue;
        }
        if ("TABLE_CAT".equals(label)) {
            return catalog;
        }
        if ("TABLE_SCHEM".equals(label)) {
            return null;
        }
        if ("TABLE_NAME".equals(label)) {
            return name;
        }
        if ("KEY_SEQ".equals(label)) {
            return keySequence;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (quoted) {
            result.append('`');
        }
        result.append(name);
        if (quoted) {
            result.append('`');
        }
        result.append(' ').append(originType);
        if (size > 0) {
            result.append('(').append(size);
            if (scale > 0) {
                result.append(", ").append(scale);
            }
            result.append(")");
        }
        if (nullable == 1) {
            result.append(" NULL DEFAULT ").append(defaultValue);
        } else {
            result.append(" NOT NULL");
            if (defaultValue != null) {
                result.append(" DEFAULT ").append(defaultValue);
            }
        }
        if (remarks != null) {
            result.append(" COMMENT '").append(remarks).append("'");
        }
        return result.toString();
    }
}
