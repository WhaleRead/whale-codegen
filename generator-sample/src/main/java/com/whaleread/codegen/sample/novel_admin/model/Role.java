package com.whaleread.codegen.sample.novel_admin.model;

import java.util.Date;
import javax.annotation.Generated;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.role")
public class Role {
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String BASE_COLUMNS = "id, `name`, `value`, remark, gmt_create, gmt_modify";

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_NAME = "role";

    /**
     *
     * column: novel_admin.role.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_ID = "id";

    /**
     *
     * column: novel_admin.role.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_NAME = "name";

    /**
     *
     * column: novel_admin.role.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_NAME_QUOTED = "`name`";

    /**
     *
     * column: novel_admin.role.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_VALUE = "value";

    /**
     *
     * column: novel_admin.role.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_VALUE_QUOTED = "`value`";

    /**
     *
     * column: novel_admin.role.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_REMARK = "remark";

    /**
     *
     * column: novel_admin.role.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_CREATE = "gmt_create";

    /**
     *
     * column: novel_admin.role.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_MODIFY = "gmt_modify";

    /**
     *
     * column: novel_admin.role.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.id")
    private Long id;

    /**
     *
     * column: novel_admin.role.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.name")
    private String name;

    /**
     *
     * column: novel_admin.role.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.value")
    private String value;

    /**
     *
     * column: novel_admin.role.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.remark")
    private String remark;

    /**
     *
     * column: novel_admin.role.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.gmt_create")
    private Date gmtCreate;

    /**
     *
     * column: novel_admin.role.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.gmt_modify")
    private Date gmtModify;

    /**
     * column: novel_admin.role.id
     *
     * @return the value of novel_admin.role.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.id")
    public Long getId() {
        return id;
    }

    /**
     * column: novel_admin.role.id
     *
     * @param id the value for novel_admin.role.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * column: novel_admin.role.name
     *
     * @return the value of novel_admin.role.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.name")
    public String getName() {
        return name;
    }

    /**
     * column: novel_admin.role.name
     *
     * @param name the value for novel_admin.role.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * column: novel_admin.role.value
     *
     * @return the value of novel_admin.role.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.value")
    public String getValue() {
        return value;
    }

    /**
     * column: novel_admin.role.value
     *
     * @param value the value for novel_admin.role.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.value")
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * column: novel_admin.role.remark
     *
     * @return the value of novel_admin.role.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.remark")
    public String getRemark() {
        return remark;
    }

    /**
     * column: novel_admin.role.remark
     *
     * @param remark the value for novel_admin.role.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.remark")
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * column: novel_admin.role.gmt_create
     *
     * @return the value of novel_admin.role.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.gmt_create")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * column: novel_admin.role.gmt_create
     *
     * @param gmtCreate the value for novel_admin.role.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.gmt_create")
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * column: novel_admin.role.gmt_modify
     *
     * @return the value of novel_admin.role.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.gmt_modify")
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * column: novel_admin.role.gmt_modify
     *
     * @param gmtModify the value for novel_admin.role.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.role.gmt_modify")
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.role")
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.role")
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.role")
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }
}