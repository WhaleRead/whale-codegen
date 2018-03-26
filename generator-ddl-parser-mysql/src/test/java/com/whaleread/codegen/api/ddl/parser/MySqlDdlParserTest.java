package com.whaleread.codegen.api.ddl.parser;

import com.whaleread.codegen.api.ddl.DdlTableDefinition;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Dolphin on 2018/1/18
 */
public class MySqlDdlParserTest {

    @Test
    public void simple() throws Exception {
        MySqlDdlParser parser = new MySqlDdlParser();
//        Reader reader = new InputStreamReader(getClass().getResourceAsStream("/tdd/foo.sql"), "UTF-8");
        String ddl = "create table log_table(row varchar(512));";
        Reader reader = new StringReader(ddl);
        Map<String, Map<String, Map<String, DdlTableDefinition>>> metadata = parser.parse("novel", null, reader);
        reader.close();
        DdlTableDefinition table = metadata.get("novel").get("").get("log_table");
        assertNotNull(table);
        assertEquals(1, table.getColumns().size());
        assertEquals("row", table.getColumns().get(0).getName());
        assertEquals("varchar", table.getColumns().get(0).getOriginType());
        assertEquals(512, table.getColumns().get(0).getSize());
    }

    @Test
    public void banner() throws Exception {
        MySqlDdlParser parser = new MySqlDdlParser();
        Reader reader = new InputStreamReader(getClass().getResourceAsStream("/tdd/banner.sql"), "UTF-8");
        Map<String, Map<String, Map<String, DdlTableDefinition>>> metadata = parser.parse("novel", null, reader);
        reader.close();
        DdlTableDefinition table = metadata.get("novel").get("").get("banner");
        assertNotNull(table);
        System.out.println(table);
        assertEquals(11, table.getColumns().size());
        assertEquals("id", table.getColumns().get(0).getName());
        assertEquals("bigint", table.getColumns().get(0).getOriginType());
        assertEquals("id", table.getPrimaryKeys().get(0));
        assertEquals("修改时间", table.getColumns().get(table.getColumns().size() - 1).getRemarks());
        assertEquals(20, table.getColumns().get(0).getSize());
    }

    @Test
    public void multiple() throws Exception {
        MySqlDdlParser parser = new MySqlDdlParser();
        Reader reader = new InputStreamReader(getClass().getResourceAsStream("/tdd/multiple.sql"), "UTF-8");
        Map<String, Map<String, Map<String, DdlTableDefinition>>> metadata = parser.parse("novel", null, reader);
        reader.close();
        assertNotNull(metadata.get("novel..user"));
        assertNotNull(metadata.get("novel..role"));
    }
}