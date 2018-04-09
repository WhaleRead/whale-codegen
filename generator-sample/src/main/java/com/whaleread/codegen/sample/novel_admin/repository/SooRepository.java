package com.whaleread.codegen.sample.novel_admin.repository;

import com.whaleread.codegen.runtime.jdbc.Criteria;
import com.whaleread.codegen.runtime.jdbc.spring.AliasBeanPropertyRowMapper;
import com.whaleread.codegen.sample.novel_admin.dto.SooDTO;
import com.whaleread.codegen.sample.novel_admin.model.Soo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.whaleread.codegen.sample.novel_admin.ShardingUtils;

/**
 * table: novel_admin.soo
 */
@Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source Table: novel_admin.soo")
@Repository
public class SooRepository extends NamedParameterJdbcDaoSupport {

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    private RowMapper<SooDTO> rowMapper = new AliasBeanPropertyRowMapper<>(Soo.TABLE_ALIAS, SooDTO.class);

    @Autowired
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void inject(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public Optional<SooDTO> selectByPrimaryKey(Long id, long userId) {
        return getJdbcTemplate().query("SELECT " + Soo.BASE_COLUMNS + " FROM " + Soo.TABLE_NAME + ShardingUtils.nodeSuffix(userId, shardingCount) + " WHERE id = ? ", new Object[] { id }, rs -> rs.next() ? Optional.of(rowMapper.mapRow(rs, 0)) : Optional.empty());
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public int countByCriteria(Criteria criteria, long userId) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().queryForObject("SELECT COUNT(0) FROM " + Soo.TABLE_NAME + ShardingUtils.nodeSuffix(userId, shardingCount) + " s " + criteria.getWhereClause(), params, int.class);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public List<SooDTO> selectByCriteria(Criteria criteria, int offset, int count, long userId) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().query("SELECT " + Soo.ALIASED_BASE_COLUMNS + " FROM " + Soo.TABLE_NAME + ShardingUtils.nodeSuffix(userId, shardingCount) + " s " + criteria.getWhereClause() + criteria.getOrderByClause() + " LIMIT " + offset + ',' + count, params, rowMapper);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void insert(Soo record, long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", record.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update("INSERT INTO " + Soo.TABLE_NAME + ShardingUtils.nodeSuffix(userId, shardingCount) + "(`name`) VALUES (:name)", new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void insertSelective(Soo record, long userId) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder columnsFragment = new StringBuilder();
        StringBuilder valuesFragment = new StringBuilder();
        if (record.getName() != null) {
            columnsFragment.append("`name`, ");
            valuesFragment.append(":name, ");
            params.put("name", record.getName());
        }
        if (columnsFragment.length() > 0) {
            columnsFragment.setLength(columnsFragment.length() - 2);
            valuesFragment.setLength(valuesFragment.length() - 2);
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update("INSERT INTO " + Soo.TABLE_NAME + ShardingUtils.nodeSuffix(userId, shardingCount) + " (" + columnsFragment + ") VALUES (" + valuesFragment + ")", new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void updateByPrimaryKeySelective(Soo record, long userId) {
        StringBuilder fragment = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("id", record.getId());
        if (record.getName() != null) {
            fragment.append("`name` = :name, ");
            params.put("name", record.getName());
        }
        if (fragment.length() == 0) {
            return;
        }
        fragment.setLength(fragment.length() - 2);
        getNamedParameterJdbcTemplate().update("UPDATE " + Soo.TABLE_NAME + ShardingUtils.nodeSuffix(userId, shardingCount) + " SET " + fragment + " WHERE id = :id ", params);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public int deleteByPrimaryKey(Long id, long userId) {
        return getJdbcTemplate().update("DELETE FROM " + Soo.TABLE_NAME + ShardingUtils.nodeSuffix(userId, shardingCount) + " WHERE id = ? ", id);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void deleteByCriteria(Criteria criteria, long userId) {
        Map<String, Object> params = criteria.toSql();
        getNamedParameterJdbcTemplate().update("DELETE FROM " + Soo.TABLE_NAME + ShardingUtils.nodeSuffix(userId, shardingCount) + ' ' + criteria.getWhereClause(), params);
    }

    private int shardingCount;
}
