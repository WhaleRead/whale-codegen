package com.whaleread.codegen.sample.novel_admin.model;

import java.util.Date;
import javax.annotation.Generated;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.perm")
public class Perm {
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String BASE_COLUMNS = "id, `name`, `value`, group_id, remark, gmt_create, gmt_modify";

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_NAME = "perm";

    /**
     *
     * column: novel_admin.perm.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_ID = "id";

    /**
     *
     * column: novel_admin.perm.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_NAME = "`name`";

    /**
     *
     * column: novel_admin.perm.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_VALUE = "`value`";

    /**
     * Database Column Remarks:
     *   ID
     *
     * column: novel_admin.perm.group_id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GROUP_ID = "group_id";

    /**
     *
     * column: novel_admin.perm.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_REMARK = "remark";

    /**
     *
     * column: novel_admin.perm.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_CREATE = "gmt_create";

    /**
     *
     * column: novel_admin.perm.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_MODIFY = "gmt_modify";

    /**
     *
     * column: novel_admin.perm.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.id")
    private Long id;

    /**
     *
     * column: novel_admin.perm.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.name")
    private String name;

    /**
     *
     * column: novel_admin.perm.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.value")
    private String value;

    /**
     * Database Column Remarks:
     *   ID
     *
     * column: novel_admin.perm.group_id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.group_id")
    private Long groupId;

    /**
     *
     * column: novel_admin.perm.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.remark")
    private String remark;

    /**
     *
     * column: novel_admin.perm.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.gmt_create")
    private Date gmtCreate;

    /**
     *
     * column: novel_admin.perm.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.gmt_modify")
    private Date gmtModify;

    /**
     * column: novel_admin.perm.id
     *
     * @return the value of novel_admin.perm.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.id")
    public Long getId() {
        return id;
    }

    /**
     * column: novel_admin.perm.id
     *
     * @param id the value for novel_admin.perm.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * column: novel_admin.perm.name
     *
     * @return the value of novel_admin.perm.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.name")
    public String getName() {
        return name;
    }

    /**
     * column: novel_admin.perm.name
     *
     * @param name the value for novel_admin.perm.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * column: novel_admin.perm.value
     *
     * @return the value of novel_admin.perm.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.value")
    public String getValue() {
        return value;
    }

    /**
     * column: novel_admin.perm.value
     *
     * @param value the value for novel_admin.perm.value
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.value")
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * column: novel_admin.perm.group_id
     *
     * @return the value of novel_admin.perm.group_id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.group_id")
    public Long getGroupId() {
        return groupId;
    }

    /**
     * column: novel_admin.perm.group_id
     *
     * @param groupId the value for novel_admin.perm.group_id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.group_id")
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * column: novel_admin.perm.remark
     *
     * @return the value of novel_admin.perm.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.remark")
    public String getRemark() {
        return remark;
    }

    /**
     * column: novel_admin.perm.remark
     *
     * @param remark the value for novel_admin.perm.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.remark")
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * column: novel_admin.perm.gmt_create
     *
     * @return the value of novel_admin.perm.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.gmt_create")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * column: novel_admin.perm.gmt_create
     *
     * @param gmtCreate the value for novel_admin.perm.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.gmt_create")
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * column: novel_admin.perm.gmt_modify
     *
     * @return the value of novel_admin.perm.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.gmt_modify")
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * column: novel_admin.perm.gmt_modify
     *
     * @param gmtModify the value for novel_admin.perm.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.perm.gmt_modify")
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.perm")
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.perm")
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.perm")
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }
}