package com.whaleread.codegen.api.ddl;

/**
 * Created by Dolphin on 2018/1/19
 */
public class DdlTablesResultSet extends DdlResultSet {
    private int cursor = 0;
    private DdlTableDefinition tableMetaData;

    public DdlTablesResultSet(DdlTableDefinition tableMetaData) {
        this.tableMetaData = tableMetaData;
    }

    @Override
    public boolean next() {
        return cursor++ == 0;
    }

    @Override
    public String getString(String columnLabel) {
        switch (columnLabel) {
            case "REMARKS":
                return tableMetaData.getRemarks();
            case "TABLE_CAT":
                return tableMetaData.getCatalog();
            case "TABLE_SCHEM":
                return tableMetaData.getSchema();
            case "TABLE_NAME":
                return tableMetaData.getName();
            case "TABLE_TYPE":
                return "TABLE";
            default:
                throw new IllegalArgumentException("column not found:" + columnLabel);
        }
    }
}
