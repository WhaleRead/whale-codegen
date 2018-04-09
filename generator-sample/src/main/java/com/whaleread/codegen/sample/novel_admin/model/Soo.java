package com.whaleread.codegen.sample.novel_admin.model;

import javax.annotation.Generated;

/**
 * Database Table Remarks:
 * soo
 *
 * <br/>
 * table: novel_admin.soo
 */
@Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source Table: novel_admin.soo")
public class Soo {

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String BASE_COLUMNS = "id, `name`";

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String ALIASED_BASE_COLUMNS = "s.id AS s_id, s.`name` AS s_name";

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_NAME = "soo";

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_ALIAS = "s";

    /**
     * column: novel_admin.soo.id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_ID = "id";

    /**
     * column: novel_admin.soo.name
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_NAME = "`name`";

    /**
     * column: novel_admin.soo.id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.soo.id")
    private Long id;

    /**
     * column: novel_admin.soo.name
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.soo.name")
    private String name;

    /**
     * column: novel_admin.soo.id
     *
     * @return the value of novel_admin.soo.id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.soo.id")
    public Long getId() {
        return id;
    }

    /**
     * column: novel_admin.soo.id
     *
     * @param id the value for novel_admin.soo.id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.soo.id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * column: novel_admin.soo.name
     *
     * @return the value of novel_admin.soo.name
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.soo.name")
    public String getName() {
        return name;
    }

    /**
     * column: novel_admin.soo.name
     *
     * @param name the value for novel_admin.soo.name
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.soo.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
