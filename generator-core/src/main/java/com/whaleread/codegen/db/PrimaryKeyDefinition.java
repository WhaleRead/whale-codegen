package com.whaleread.codegen.db;

/**
 * Created by dolphin on 2018/1/13
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getSequence() {
        return sequence;
    }

    public void setSequence(short sequence) {
        this.sequence = sequence;
    }
}
