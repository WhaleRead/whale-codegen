package com.whaleread.codegen.api.ddl;

import java.util.List;

/**
 * Created by Dolphin on 2018/1/19
 */
public class DdlPrimaryKeysResultSet extends DdlResultSet {
    private List<String> primaryKeys;
    private int cursor;

    public DdlPrimaryKeysResultSet(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    @Override
    public boolean next() {
        return cursor++ < primaryKeys.size();
    }

    @Override
    public String getString(String columnLabel) {
        switch (columnLabel) {
            case "COLUMN_NAME":
                return primaryKeys.get(cursor - 1);
            default:
                throw new IllegalArgumentException("column not found:" + columnLabel);
        }
    }

    @Override
    public short getShort(String columnLabel) {
        switch (columnLabel) {
            case "KEY_SEQ":
                return (short) cursor;
            default:
                throw new IllegalArgumentException("column not found:" + columnLabel);
        }
    }
}
