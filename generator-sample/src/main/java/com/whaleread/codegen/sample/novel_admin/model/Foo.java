package com.whaleread.codegen.sample.novel_admin.model;

import java.util.Date;
import javax.annotation.Generated;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Database Table Remarks:
 *   foo
 *
 * <br/>
 * table: novel_admin.foo
 */
@Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.foo")
public class Foo {
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String BASE_COLUMNS = "id, `name`, birth, avatar, email, age, `status`, gmt_create, gmt_modify";

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String ALIASED_BASE_COLUMNS = "f.id AS f_id, f.`name` AS f_name, f.birth AS f_birth, f.avatar AS f_avatar, f.email AS f_email, f.age AS f_age, f.`status` AS f_status, f.gmt_create AS f_gmt_create, f.gmt_modify AS f_gmt_modify";

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_NAME = "foo";

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_ALIAS = "f";

    /**
     *
     * column: novel_admin.foo.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_ID = "id";

    /**
     *
     * column: novel_admin.foo.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_NAME = "name";

    /**
     *
     * column: novel_admin.foo.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_NAME_QUOTED = "`name`";

    /**
     * Database Column Remarks:
     *   生日
     *
     * column: novel_admin.foo.birth
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_BIRTH = "birth";

    /**
     *
     * column: novel_admin.foo.avatar
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_AVATAR = "avatar";

    /**
     *
     * column: novel_admin.foo.email
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_EMAIL = "email";

    /**
     *
     * column: novel_admin.foo.age
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_AGE = "age";

    /**
     * Database Column Remarks:
     *   , 0:,1:
     *
     * column: novel_admin.foo.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_STATUS = "status";

    /**
     * Database Column Remarks:
     *   , 0:,1:
     *
     * column: novel_admin.foo.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_STATUS_QUOTED = "`status`";

    /**
     *
     * column: novel_admin.foo.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_CREATE = "gmt_create";

    /**
     *
     * column: novel_admin.foo.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_MODIFY = "gmt_modify";

    /**
     *
     * column: novel_admin.foo.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.id")
    private Long id;

    /**
     *
     * column: novel_admin.foo.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.name")
    private String name;

    /**
     * Database Column Remarks:
     *   生日
     *
     * column: novel_admin.foo.birth
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.birth")
    private Date birth;

    /**
     *
     * column: novel_admin.foo.avatar
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.avatar")
    private String avatar;

    /**
     *
     * column: novel_admin.foo.email
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.email")
    private String email;

    /**
     *
     * column: novel_admin.foo.age
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.age")
    private Integer age;

    /**
     * Database Column Remarks:
     *   , 0:,1:
     *
     * column: novel_admin.foo.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.status")
    private Integer status;

    /**
     *
     * column: novel_admin.foo.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.gmt_create")
    private Date gmtCreate;

    /**
     *
     * column: novel_admin.foo.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.gmt_modify")
    private Date gmtModify;

    /**
     * column: novel_admin.foo.id
     *
     * @return the value of novel_admin.foo.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.id")
    public Long getId() {
        return id;
    }

    /**
     * column: novel_admin.foo.id
     *
     * @param id the value for novel_admin.foo.id
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * column: novel_admin.foo.name
     *
     * @return the value of novel_admin.foo.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.name")
    public String getName() {
        return name;
    }

    /**
     * column: novel_admin.foo.name
     *
     * @param name the value for novel_admin.foo.name
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * column: novel_admin.foo.birth
     *
     * @return the value of novel_admin.foo.birth
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.birth")
    public Date getBirth() {
        return birth;
    }

    /**
     * column: novel_admin.foo.birth
     *
     * @param birth the value for novel_admin.foo.birth
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.birth")
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * column: novel_admin.foo.avatar
     *
     * @return the value of novel_admin.foo.avatar
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.avatar")
    public String getAvatar() {
        return avatar;
    }

    /**
     * column: novel_admin.foo.avatar
     *
     * @param avatar the value for novel_admin.foo.avatar
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * column: novel_admin.foo.email
     *
     * @return the value of novel_admin.foo.email
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.email")
    public String getEmail() {
        return email;
    }

    /**
     * column: novel_admin.foo.email
     *
     * @param email the value for novel_admin.foo.email
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.email")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * column: novel_admin.foo.age
     *
     * @return the value of novel_admin.foo.age
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.age")
    public Integer getAge() {
        return age;
    }

    /**
     * column: novel_admin.foo.age
     *
     * @param age the value for novel_admin.foo.age
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.age")
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * column: novel_admin.foo.status
     *
     * @return the value of novel_admin.foo.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.status")
    public Integer getStatus() {
        return status;
    }

    /**
     * column: novel_admin.foo.status
     *
     * @param status the value for novel_admin.foo.status
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * column: novel_admin.foo.gmt_create
     *
     * @return the value of novel_admin.foo.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.gmt_create")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * column: novel_admin.foo.gmt_create
     *
     * @param gmtCreate the value for novel_admin.foo.gmt_create
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.gmt_create")
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * column: novel_admin.foo.gmt_modify
     *
     * @return the value of novel_admin.foo.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.gmt_modify")
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * column: novel_admin.foo.gmt_modify
     *
     * @param gmtModify the value for novel_admin.foo.gmt_modify
     */
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source field: novel_admin.foo.gmt_modify")
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.foo")
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.foo")
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.foo")
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }
}