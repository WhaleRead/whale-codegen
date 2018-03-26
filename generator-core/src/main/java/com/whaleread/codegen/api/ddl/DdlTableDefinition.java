package com.whaleread.codegen.api.ddl;

import com.whaleread.codegen.db.ResultSetRow;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Table definition read from database metadata
 * Created by dolphin on 2018/1/13
 */
@Getter
@Setter
public class DdlTableDefinition implements ResultSetRow {
    private final String catalog;
    private final String schema;
    private final String name;
    private boolean catalogQuoted;
    private boolean schemaQuoted;
    private boolean nameQuoted;
    private List<String> primaryKeys = new ArrayList<>();
    private String remarks;
    private List<DdlColumnDefinition> columns = new ArrayList<>();

    public DdlTableDefinition(String catalog, String schema, String name) {
        this.catalog = catalog;
        this.schema = schema;
        this.name = name;
    }

    @Override
    public Object get(String label) {
        switch (label) {
            case "CATALOG":
                return catalog;
            case "SCHEMA":
                return schema;
            case "NAME":
                return name;
            case "REMARKS":
                return remarks;
            default:
                throw new RuntimeException("Label " + label + " not found!");
        }
    }

    public void addPrimaryKey(String key) {
        this.primaryKeys.add(key);
    }

    public void addColumn(DdlColumnDefinition column) {
        this.columns.add(column);
    }

    public DdlColumnDefinition findColumn(String name) {
        if (columns.isEmpty()) {
            return null;
        }
        for (DdlColumnDefinition column : columns) {
            if (column.getName().equals(name)) {
                return column;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("CREATE TABLE ");
        if(nameQuoted) {
            result.append('`');
        }
        result.append(name);
        if(nameQuoted) {
            result.append('`');
        }
        result.append(" (\n");
        for (DdlColumnDefinition column : columns) {
            result.append(column.toString()).append(",\n");
        }
        if (primaryKeys.size() > 0) {
            result.append("PRIMARY KEY (");
            for (String pk : primaryKeys) {
                result.append(pk).append(',');
            }
            result.setLength(result.length() - 1);
            result.append(")");
        }
        if (result.charAt(result.length() - 2) == ',') {
            result.setLength(result.length() - 2);
        }
        result.append("\n)");
        if(remarks != null) {
            result.append(" COMMENT '").append(remarks).append("'");
        }
        result.append(";\n");
        return result.toString();
    }
}
