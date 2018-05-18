package com.whaleread.codegen.sample.novel_admin.repository;

import com.whaleread.codegen.runtime.jdbc.Criteria;
import com.whaleread.codegen.runtime.jdbc.spring.AliasBeanPropertyRowMapper;
import com.whaleread.codegen.sample.novel_admin.model.Boo;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * table: novel_admin.boo
 */
@Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source Table: novel_admin.boo")
@Repository
public class BooRepository extends NamedParameterJdbcDaoSupport {

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    private RowMapper<Boo> rowMapper = new AliasBeanPropertyRowMapper<>(Boo.TABLE_ALIAS, Boo.class);

    @Autowired
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void inject(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public int countByCriteria(Criteria criteria) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().queryForObject("SELECT COUNT(0) FROM " + Boo.TABLE_NAME + " b " + criteria.getWhereClause(), params, int.class);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void insertSelective(Boo record) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder columnsFragment = new StringBuilder();
        StringBuilder valuesFragment = new StringBuilder();
        if (record.getUserId() != null) {
            columnsFragment.append("user_id, ");
            valuesFragment.append(":userId, ");
            params.put("userId", record.getUserId());
        }
        if (record.getComicId() != null) {
            columnsFragment.append("comic_id, ");
            valuesFragment.append(":comicId, ");
            params.put("comicId", record.getComicId());
        }
        if (record.getIssueId() != null) {
            columnsFragment.append("issue_id, ");
            valuesFragment.append(":issueId, ");
            params.put("issueId", record.getIssueId());
        }
        if (record.getPrice() != null) {
            columnsFragment.append("price, ");
            valuesFragment.append(":price, ");
            params.put("price", record.getPrice());
        }
        if (record.getAmount() != null) {
            columnsFragment.append("amount, ");
            valuesFragment.append(":amount, ");
            params.put("amount", record.getAmount());
        }
        if (record.getCoupon() != null) {
            columnsFragment.append("coupon, ");
            valuesFragment.append(":coupon, ");
            params.put("coupon", record.getCoupon());
        }
        if (record.getProduct() != null) {
            columnsFragment.append("product, ");
            valuesFragment.append(":product, ");
            params.put("product", record.getProduct());
        }
        if (record.getChannel() != null) {
            columnsFragment.append("channel, ");
            valuesFragment.append(":channel, ");
            params.put("channel", record.getChannel());
        }
        if (record.getGmtCreate() != null) {
            columnsFragment.append("gmt_create, ");
            valuesFragment.append(":gmtCreate, ");
            params.put("gmtCreate", record.getGmtCreate());
        }
        if (record.getGmtModify() != null) {
            columnsFragment.append("gmt_modify, ");
            valuesFragment.append(":gmtModify, ");
            params.put("gmtModify", record.getGmtModify());
        }
        if (record.getIp() != null) {
            columnsFragment.append("ip, ");
            valuesFragment.append(":ip, ");
            params.put("ip", record.getIp());
        }
        if (columnsFragment.length() > 0) {
            columnsFragment.setLength(columnsFragment.length() - 2);
            valuesFragment.setLength(valuesFragment.length() - 2);
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update("INSERT INTO " + Boo.TABLE_NAME + " (" + columnsFragment + ") VALUES (" + valuesFragment + ")", new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }
}
