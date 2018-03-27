package com.whaleread.codegen.api.ddl;

import java.sql.ResultSetMetaData;
import java.util.List;

/**
 * Created by Dolphin on 2018/1/19
 */
public class DdlColumnsResultSet extends DdlResultSet {
    private int cursor;
    private List<DdlColumnDefinition> columns;
    private DdlColumnDefinition current;

    public DdlColumnsResultSet(List<DdlColumnDefinition> columns) {
        this.columns = columns;
    }

    @Override
    public boolean next() {
        if (cursor++ >= columns.size()) {
            return false;
        }
        current = columns.get(cursor - 1);
        return true;
    }

    @Override
    public int getInt(String columnLabel) {
        switch (columnLabel) {
            case "NULLABLE":
                return current.getNullable() == null ? 0 : current.getNullable();
            case "COLUMN_SIZE":
                return current.getSize();
            case "DECIMAL_DIGITS":
                return current.getScale();
            case "DATA_TYPE":
                return current.getType();
            default:
                throw new IllegalArgumentException("column not found:" + columnLabel);
        }
    }

    @Override
    public String getString(String columnLabel) {
        switch (columnLabel) {
            case "TABLE_CAT":
                return current.getCatalog();
            case "TABLE_SCHEM":
                return current.getSchema();
            case "TABLE_NAME":
                return current.getTable();
            case "COLUMN_NAME":
                return current.getName();
            case "REMARKS":
                return current.getRemarks();
            case "COLUMN_DEF":
                return current.getDefaultValue();
            case "IS_AUTOINCREMENT":
                return current.isAutoIncrement() ? "YES" : "NO";
            case "IS_GENERATEDCOLUMN":
                return current.isGeneratedColumn() ? "YES" : "NO";
            default:
                throw new IllegalArgumentException("column not found:" + columnLabel);
        }
    }

    @Override
    public ResultSetMetaData getMetaData() {
        return new Metadata();
    }

    private static final String[] columnNames = {
            "TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "COLUMN_NAME", "DATA_TYPE", "TYPE_NAME", "COLUMN_SIZE", "BUFFER_LENGTH", "DECIMAL_DIGITS", "NUM_PREC_RADIX", "NULLABLE",
            "REMARKS", "COLUMN_DEF", "SQL_DATA_TYPE", "SQL_DATETIME_SUB", "CHAR_OCTET_LENGTH", "ORDINAL_POSITION", "IS_NULLABLE", "SCOPE_CATALOG", "SCOPE_SCHEMA", "SCOPE_TABLE",
            "SOURCE_DATA_TYPE", "IS_AUTOINCREMENT", "IS_GENERATEDCOLUMN",
    };

    class Metadata extends ResultSetMetaDataAdapter {
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column - 1];
        }
    }
}
