package com.whaleread.codegen.sample.novel_admin.model;

import java.util.Date;
import javax.annotation.Generated;

/**
 * Database Table Remarks:
 * 漫画订阅表
 *
 * <br/>
 * table: novel_admin.boo
 */
@Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source Table: novel_admin.boo")
public class Boo {

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String BASE_COLUMNS = "id, user_id, comic_id, issue_id, price, amount, coupon, product, channel, gmt_create, gmt_modify, ip";

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String ALIASED_BASE_COLUMNS = "b.id AS b_id, b.user_id AS b_user_id, b.comic_id AS b_comic_id, b.issue_id AS b_issue_id, b.price AS b_price, b.amount AS b_amount, b.coupon AS b_coupon, b.product AS b_product, b.channel AS b_channel, b.gmt_create AS b_gmt_create, b.gmt_modify AS b_gmt_modify, b.ip AS b_ip";

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_NAME = "boo";

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String TABLE_ALIAS = "b";

    /**
     * column: novel_admin.boo.id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_ID = "id";

    /**
     * Database Column Remarks:
     * 用户编号
     *
     * column: novel_admin.boo.user_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_USER_ID = "user_id";

    /**
     * Database Column Remarks:
     * 漫画编号
     *
     * column: novel_admin.boo.comic_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_COMIC_ID = "comic_id";

    /**
     * Database Column Remarks:
     * 话编号
     *
     * column: novel_admin.boo.issue_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_ISSUE_ID = "issue_id";

    /**
     * Database Column Remarks:
     * 价格
     *
     * column: novel_admin.boo.price
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_PRICE = "price";

    /**
     * Database Column Remarks:
     * 实际付费金额
     *
     * column: novel_admin.boo.amount
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_AMOUNT = "amount";

    /**
     * Database Column Remarks:
     * 书券金额
     *
     * column: novel_admin.boo.coupon
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_COUPON = "coupon";

    /**
     * Database Column Remarks:
     * 产品编号
     *
     * column: novel_admin.boo.product
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_PRODUCT = "product";

    /**
     * Database Column Remarks:
     * 渠道号
     *
     * column: novel_admin.boo.channel
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_CHANNEL = "channel";

    /**
     * Database Column Remarks:
     * 创建时间
     *
     * column: novel_admin.boo.gmt_create
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_CREATE = "gmt_create";

    /**
     * Database Column Remarks:
     * 更新时间
     *
     * column: novel_admin.boo.gmt_modify
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_GMT_MODIFY = "gmt_modify";

    /**
     * Database Column Remarks:
     * IP地址(ATON后)
     *
     * column: novel_admin.boo.ip
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public static final String COLUMN_IP = "ip";

    /**
     * column: novel_admin.boo.id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.id")
    private Long id;

    /**
     * Database Column Remarks:
     * 用户编号
     *
     * column: novel_admin.boo.user_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.user_id")
    private Long userId;

    /**
     * Database Column Remarks:
     * 漫画编号
     *
     * column: novel_admin.boo.comic_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.comic_id")
    private Long comicId;

    /**
     * Database Column Remarks:
     * 话编号
     *
     * column: novel_admin.boo.issue_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.issue_id")
    private Long issueId;

    /**
     * Database Column Remarks:
     * 价格
     *
     * column: novel_admin.boo.price
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.price")
    private Integer price;

    /**
     * Database Column Remarks:
     * 实际付费金额
     *
     * column: novel_admin.boo.amount
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.amount")
    private Integer amount;

    /**
     * Database Column Remarks:
     * 书券金额
     *
     * column: novel_admin.boo.coupon
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.coupon")
    private Integer coupon;

    /**
     * Database Column Remarks:
     * 产品编号
     *
     * column: novel_admin.boo.product
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.product")
    private Long product;

    /**
     * Database Column Remarks:
     * 渠道号
     *
     * column: novel_admin.boo.channel
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.channel")
    private Long channel;

    /**
     * Database Column Remarks:
     * 创建时间
     *
     * column: novel_admin.boo.gmt_create
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.gmt_create")
    private Date gmtCreate;

    /**
     * Database Column Remarks:
     * 更新时间
     *
     * column: novel_admin.boo.gmt_modify
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.gmt_modify")
    private Date gmtModify;

    /**
     * Database Column Remarks:
     * IP地址(ATON后)
     *
     * column: novel_admin.boo.ip
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.ip")
    private byte[] ip;

    /**
     * column: novel_admin.boo.id
     *
     * @return the value of novel_admin.boo.id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.id")
    public Long getId() {
        return id;
    }

    /**
     * column: novel_admin.boo.id
     *
     * @param id the value for novel_admin.boo.id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * column: novel_admin.boo.user_id
     *
     * @return the value of novel_admin.boo.user_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.user_id")
    public Long getUserId() {
        return userId;
    }

    /**
     * column: novel_admin.boo.user_id
     *
     * @param userId the value for novel_admin.boo.user_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.user_id")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * column: novel_admin.boo.comic_id
     *
     * @return the value of novel_admin.boo.comic_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.comic_id")
    public Long getComicId() {
        return comicId;
    }

    /**
     * column: novel_admin.boo.comic_id
     *
     * @param comicId the value for novel_admin.boo.comic_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.comic_id")
    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    /**
     * column: novel_admin.boo.issue_id
     *
     * @return the value of novel_admin.boo.issue_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.issue_id")
    public Long getIssueId() {
        return issueId;
    }

    /**
     * column: novel_admin.boo.issue_id
     *
     * @param issueId the value for novel_admin.boo.issue_id
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.issue_id")
    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    /**
     * column: novel_admin.boo.price
     *
     * @return the value of novel_admin.boo.price
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.price")
    public Integer getPrice() {
        return price;
    }

    /**
     * column: novel_admin.boo.price
     *
     * @param price the value for novel_admin.boo.price
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * column: novel_admin.boo.amount
     *
     * @return the value of novel_admin.boo.amount
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.amount")
    public Integer getAmount() {
        return amount;
    }

    /**
     * column: novel_admin.boo.amount
     *
     * @param amount the value for novel_admin.boo.amount
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * column: novel_admin.boo.coupon
     *
     * @return the value of novel_admin.boo.coupon
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.coupon")
    public Integer getCoupon() {
        return coupon;
    }

    /**
     * column: novel_admin.boo.coupon
     *
     * @param coupon the value for novel_admin.boo.coupon
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.coupon")
    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    /**
     * column: novel_admin.boo.product
     *
     * @return the value of novel_admin.boo.product
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.product")
    public Long getProduct() {
        return product;
    }

    /**
     * column: novel_admin.boo.product
     *
     * @param product the value for novel_admin.boo.product
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.product")
    public void setProduct(Long product) {
        this.product = product;
    }

    /**
     * column: novel_admin.boo.channel
     *
     * @return the value of novel_admin.boo.channel
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.channel")
    public Long getChannel() {
        return channel;
    }

    /**
     * column: novel_admin.boo.channel
     *
     * @param channel the value for novel_admin.boo.channel
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.channel")
    public void setChannel(Long channel) {
        this.channel = channel;
    }

    /**
     * column: novel_admin.boo.gmt_create
     *
     * @return the value of novel_admin.boo.gmt_create
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.gmt_create")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * column: novel_admin.boo.gmt_create
     *
     * @param gmtCreate the value for novel_admin.boo.gmt_create
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.gmt_create")
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * column: novel_admin.boo.gmt_modify
     *
     * @return the value of novel_admin.boo.gmt_modify
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.gmt_modify")
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * column: novel_admin.boo.gmt_modify
     *
     * @param gmtModify the value for novel_admin.boo.gmt_modify
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.gmt_modify")
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * column: novel_admin.boo.ip
     *
     * @return the value of novel_admin.boo.ip
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.ip")
    public byte[] getIp() {
        return ip;
    }

    /**
     * column: novel_admin.boo.ip
     *
     * @param ip the value for novel_admin.boo.ip
     */
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source field: novel_admin.boo.ip")
    public void setIp(byte[] ip) {
        this.ip = ip;
    }
}
