package com.whaleread.codegen.sample.novel_admin.model;

import java.util.Date;
import javax.annotation.Generated;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Database Table Remarks:
 *   用户表
 *
 * <br/>
 * table: novel_admin.user
 */
@Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.user")
public class User {
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String BASE_COLUMNS = "id, username, `password`, display_name, avatar, email, age, `status`, `type`, remark, gmt_create, gmt_modify";

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String ALIASED_BASE_COLUMNS = "u.id AS u_id, u.username AS u_username, u.`password` AS u_password, u.display_name AS u_display_name, u.avatar AS u_avatar, u.email AS u_email, u.age AS u_age, u.`status` AS u_status, u.`type` AS u_type, u.remark AS u_remark, u.gmt_create AS u_gmt_create, u.gmt_modify AS u_gmt_modify";

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_NAME = "user";

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_ALIAS = "u";

    /**
     *
     * column: novel_admin.user.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_ID = "id";

    /**
     *
     * column: novel_admin.user.username
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_USERNAME = "username";

    /**
     *
     * column: novel_admin.user.password
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_PASSWORD = "`password`";

    /**
     *
     * column: novel_admin.user.display_name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_DISPLAY_NAME = "display_name";

    /**
     *
     * column: novel_admin.user.avatar
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_AVATAR = "avatar";

    /**
     *
     * column: novel_admin.user.email
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_EMAIL = "email";

    /**
     *
     * column: novel_admin.user.age
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_AGE = "age";

    /**
     * Database Column Remarks:
     *   , 0:,1:
     *
     * column: novel_admin.user.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_STATUS = "`status`";

    /**
     * Database Column Remarks:
     *   账号类型,admin:管理,channel:渠道,author:作者
     *
     * column: novel_admin.user.type
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_TYPE = "`type`";

    /**
     *
     * column: novel_admin.user.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_REMARK = "remark";

    /**
     *
     * column: novel_admin.user.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_CREATE = "gmt_create";

    /**
     *
     * column: novel_admin.user.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_MODIFY = "gmt_modify";

    /**
     *
     * column: novel_admin.user.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.id")
    private Long id;

    /**
     *
     * column: novel_admin.user.username
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.username")
    private String username;

    /**
     *
     * column: novel_admin.user.password
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.password")
    private String password;

    /**
     *
     * column: novel_admin.user.display_name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.display_name")
    private String displayName;

    /**
     *
     * column: novel_admin.user.avatar
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.avatar")
    private String avatar;

    /**
     *
     * column: novel_admin.user.email
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.email")
    private String email;

    /**
     *
     * column: novel_admin.user.age
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.age")
    private Integer age;

    /**
     * Database Column Remarks:
     *   , 0:,1:
     *
     * column: novel_admin.user.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.status")
    private Integer status;

    /**
     * Database Column Remarks:
     *   账号类型,admin:管理,channel:渠道,author:作者
     *
     * column: novel_admin.user.type
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.type")
    private String type;

    /**
     *
     * column: novel_admin.user.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.remark")
    private String remark;

    /**
     *
     * column: novel_admin.user.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.gmt_create")
    private Date gmtCreate;

    /**
     *
     * column: novel_admin.user.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.gmt_modify")
    private Date gmtModify;

    /**
     * column: novel_admin.user.id
     *
     * @return the value of novel_admin.user.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.id")
    public Long getId() {
        return id;
    }

    /**
     * column: novel_admin.user.id
     *
     * @param id the value for novel_admin.user.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * column: novel_admin.user.username
     *
     * @return the value of novel_admin.user.username
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.username")
    public String getUsername() {
        return username;
    }

    /**
     * column: novel_admin.user.username
     *
     * @param username the value for novel_admin.user.username
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.username")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * column: novel_admin.user.password
     *
     * @return the value of novel_admin.user.password
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.password")
    public String getPassword() {
        return password;
    }

    /**
     * column: novel_admin.user.password
     *
     * @param password the value for novel_admin.user.password
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.password")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * column: novel_admin.user.display_name
     *
     * @return the value of novel_admin.user.display_name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.display_name")
    public String getDisplayName() {
        return displayName;
    }

    /**
     * column: novel_admin.user.display_name
     *
     * @param displayName the value for novel_admin.user.display_name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.display_name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * column: novel_admin.user.avatar
     *
     * @return the value of novel_admin.user.avatar
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.avatar")
    public String getAvatar() {
        return avatar;
    }

    /**
     * column: novel_admin.user.avatar
     *
     * @param avatar the value for novel_admin.user.avatar
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * column: novel_admin.user.email
     *
     * @return the value of novel_admin.user.email
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.email")
    public String getEmail() {
        return email;
    }

    /**
     * column: novel_admin.user.email
     *
     * @param email the value for novel_admin.user.email
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.email")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * column: novel_admin.user.age
     *
     * @return the value of novel_admin.user.age
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.age")
    public Integer getAge() {
        return age;
    }

    /**
     * column: novel_admin.user.age
     *
     * @param age the value for novel_admin.user.age
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.age")
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * column: novel_admin.user.status
     *
     * @return the value of novel_admin.user.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.status")
    public Integer getStatus() {
        return status;
    }

    /**
     * column: novel_admin.user.status
     *
     * @param status the value for novel_admin.user.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * column: novel_admin.user.type
     *
     * @return the value of novel_admin.user.type
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.type")
    public String getType() {
        return type;
    }

    /**
     * column: novel_admin.user.type
     *
     * @param type the value for novel_admin.user.type
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.type")
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * column: novel_admin.user.remark
     *
     * @return the value of novel_admin.user.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.remark")
    public String getRemark() {
        return remark;
    }

    /**
     * column: novel_admin.user.remark
     *
     * @param remark the value for novel_admin.user.remark
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.remark")
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * column: novel_admin.user.gmt_create
     *
     * @return the value of novel_admin.user.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.gmt_create")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * column: novel_admin.user.gmt_create
     *
     * @param gmtCreate the value for novel_admin.user.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.gmt_create")
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * column: novel_admin.user.gmt_modify
     *
     * @return the value of novel_admin.user.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.gmt_modify")
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * column: novel_admin.user.gmt_modify
     *
     * @param gmtModify the value for novel_admin.user.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.user.gmt_modify")
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.user")
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.user")
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.user")
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }
}