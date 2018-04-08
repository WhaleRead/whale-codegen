package com.whaleread.codegen.sample.novel_admin.repository;

import com.whaleread.codegen.runtime.jdbc.Criteria;
import com.whaleread.codegen.sample.novel_admin.dto.RoleDTO;
import com.whaleread.codegen.sample.novel_admin.model.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * table: novel_admin.role
 */
@Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.role")
@Repository
public class RoleRepository extends NamedParameterJdbcDaoSupport {
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    private RowMapper<RoleDTO> rowMapper = new BeanPropertyRowMapper<>(RoleDTO.class);

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    private static final String INSERT_SQL = "INSERT INTO " + Role.TABLE_NAME + "(`name`, `value`, remark) VALUES (:name, :value, :remark)";

    @Autowired
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void inject(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public Optional<RoleDTO> selectByPrimaryKey(Long id) {
        return getJdbcTemplate().query("SELECT " + Role.BASE_COLUMNS + " FROM " + Role.TABLE_NAME + " WHERE id = ? ", new Object[]{id}, rs -> rs.next() ? Optional.of(rowMapper.mapRow(rs, 0)) : Optional.empty());
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public int countByCriteria(Criteria criteria) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().queryForObject("SELECT COUNT(0) FROM " + Role.TABLE_NAME + "  " + criteria.getWhereClause(), params, int.class);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public List<RoleDTO> selectByCriteria(Criteria criteria, int offset, int count) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().query("SELECT " + Role.BASE_COLUMNS + " FROM " + Role.TABLE_NAME + "  " + criteria.getWhereClause() + criteria.getOrderByClause() + " LIMIT " + offset + ',' + count, params, rowMapper);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void insert(Role record) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", record.getName());
        params.put("value", record.getValue());
        params.put("remark", record.getRemark());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(INSERT_SQL, new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void insertSelective(Role record) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder columnsFragment = new StringBuilder();
        StringBuilder valuesFragment = new StringBuilder();
        if (record.getName() != null) {
            columnsFragment.append("`name`, ");
            valuesFragment.append(":name, ");
            params.put("name", record.getName());
        }
        if (record.getValue() != null) {
            columnsFragment.append("`value`, ");
            valuesFragment.append(":value, ");
            params.put("value", record.getValue());
        }
        if (record.getRemark() != null) {
            columnsFragment.append("remark, ");
            valuesFragment.append(":remark, ");
            params.put("remark", record.getRemark());
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
        if (columnsFragment.length() > 0) {
            columnsFragment.setLength(columnsFragment.length() - 2);
            valuesFragment.setLength(valuesFragment.length() - 2);
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update("INSERT INTO " + Role.TABLE_NAME + " (" + columnsFragment + ") VALUES (" + valuesFragment + ")", new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void updateByPrimaryKeySelective(Role record) {
        StringBuilder fragment = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("id", record.getId());
        if (record.getName() != null) {
            fragment.append("`name` = :name, ");
            params.put("name", record.getName());
        }
        if (record.getValue() != null) {
            fragment.append("`value` = :value, ");
            params.put("value", record.getValue());
        }
        if (record.getRemark() != null) {
            fragment.append("remark = :remark, ");
            params.put("remark", record.getRemark());
        }
        if (record.getGmtCreate() != null) {
            fragment.append("gmt_create = :gmtCreate, ");
            params.put("gmtCreate", record.getGmtCreate());
        }
        if (record.getGmtModify() != null) {
            fragment.append("gmt_modify = :gmtModify, ");
            params.put("gmtModify", record.getGmtModify());
        }
        if (fragment.length() == 0) {
            return;
        }
        fragment.setLength(fragment.length() - 2);
        getNamedParameterJdbcTemplate().update("UPDATE " + Role.TABLE_NAME + " SET " + fragment + " WHERE id = :id ", params);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public int deleteByPrimaryKey(Long id) {
        return getJdbcTemplate().update("DELETE FROM " + Role.TABLE_NAME + " WHERE id = ? ", id);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void deleteByCriteria(Criteria criteria) {
        Map<String, Object> params = criteria.toSql();
        getNamedParameterJdbcTemplate().update("DELETE FROM " + Role.TABLE_NAME + ' ' + criteria.getWhereClause(), params);
    }
}