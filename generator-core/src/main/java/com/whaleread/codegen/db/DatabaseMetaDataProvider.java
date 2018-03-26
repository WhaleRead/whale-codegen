package com.whaleread.codegen.db;

import java.sql.DatabaseMetaData;

/**
 * Created by dolphin on 2018/1/13
 */
public interface DatabaseMetaDataProvider {
    DatabaseMetaData getMetaData();
}
