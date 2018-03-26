package com.whaleread.codegen.api.ddl;

import java.sql.ResultSetMetaData;

/**
 * Created by Dolphin on 2018/1/19
 */
public class DdlEmptyResultSet extends DdlResultSet {
    @Override
    public boolean next() {
        return false;
    }

    @Override
    public String getString(int columnIndex) {
        return null;
    }

    @Override
    public String getString(String columnLabel) {
        return null;
    }

    @Override
    public ResultSetMetaData getMetaData() {
        return new MetaData();
    }

    class MetaData extends ResultSetMetaDataAdapter {
        @Override
        public int getColumnCount() {
            return 0;
        }
    }
}
