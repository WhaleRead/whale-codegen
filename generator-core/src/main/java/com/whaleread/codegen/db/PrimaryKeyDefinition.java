package com.whaleread.codegen.db;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by dolphin on 2018/1/13
 */
@Getter
@Setter
public class PrimaryKeyDefinition implements ResultSetRow{
    private String name;
    private short sequence;

    public PrimaryKeyDefinition(String name, short sequence) {
        this.name = name;
        this.sequence = sequence;
    }

    @Override
    public Object get(String label) {
        if ("COLUMN_NAME".equalsIgnoreCase(label)) {
            return name;
        }
        if ("KEY_SEQ".equalsIgnoreCase(label)) {
            return sequence;
        }
        return null;
    }
}
