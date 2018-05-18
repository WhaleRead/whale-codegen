package com.whaleread.codegen.api.ddl;

import com.whaleread.codegen.db.ResultSetRow;

import java.util.List;

/**
 * Column definition read from database metadata
 * Created by dolphin on 2018/1/13
 */
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

    public String getCatalog() {
        return catalog;
    }

    public String getSchema() {
        return schema;
    }

    public String getTable() {
        return table;
    }

    public String getName() {
        return name;
    }

    public boolean isQuoted() {
        return quoted;
    }

    public void setQuoted(boolean quoted) {
        this.quoted = quoted;
    }

    public Integer getNullable() {
        return nullable;
    }

    public void setNullable(Integer nullable) {
        this.nullable = nullable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public short getKeySequence() {
        return keySequence;
    }

    public void setKeySequence(short keySequence) {
        this.keySequence = keySequence;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isGeneratedColumn() {
        return generatedColumn;
    }

    public void setGeneratedColumn(boolean generatedColumn) {
        this.generatedColumn = generatedColumn;
    }

    public int getOriginalPosition() {
        return originalPosition;
    }

    public void setOriginalPosition(int originalPosition) {
        this.originalPosition = originalPosition;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getEnums() {
        return enums;
    }

    public void setEnums(List<String> enums) {
        this.enums = enums;
    }
}
