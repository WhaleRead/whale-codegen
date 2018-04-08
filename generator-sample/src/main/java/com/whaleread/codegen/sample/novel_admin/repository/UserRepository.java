package com.whaleread.codegen.sample.novel_admin.repository;

import com.whaleread.codegen.runtime.jdbc.Criteria;
import com.whaleread.codegen.runtime.jdbc.spring.AliasBeanPropertyRowMapper;
import com.whaleread.codegen.sample.novel_admin.dto.UserDTO;
import com.whaleread.codegen.sample.novel_admin.model.User;
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

/**
 * table: novel_admin.user
 */
@Generated(value="com.whaleread.codegen.api.WhaleGenerator", comments="Source Table: novel_admin.user")
@Repository
public class UserRepository extends NamedParameterJdbcDaoSupport {
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    private RowMapper<UserDTO> rowMapper = new AliasBeanPropertyRowMapper<>(User.TABLE_ALIAS, UserDTO.class);

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    private static final String INSERT_SQL = "INSERT INTO " + User.TABLE_NAME + "(username, `password`, display_name, avatar, email, age, `status`, `type`, remark) VALUES (:username, :password, :displayName, :avatar, :email, :age, :status, :type, :remark)";

    @Autowired
    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void inject(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public Optional<UserDTO> selectByPrimaryKey(Long id) {
        return getJdbcTemplate().query("SELECT " + User.BASE_COLUMNS + " FROM " + User.TABLE_NAME + " WHERE id = ? ", new Object[]{id}, rs -> rs.next() ? Optional.of(rowMapper.mapRow(rs, 0)) : Optional.empty());
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public int countByCriteria(Criteria criteria) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().queryForObject("SELECT COUNT(0) FROM " + User.TABLE_NAME + " u " + criteria.getWhereClause(), params, int.class);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public List<UserDTO> selectByCriteria(Criteria criteria, int offset, int count) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().query("SELECT " + User.ALIASED_BASE_COLUMNS + " FROM " + User.TABLE_NAME + " u " + criteria.getWhereClause() + criteria.getOrderByClause() + " LIMIT " + offset + ',' + count, params, rowMapper);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void insert(User record) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", record.getUsername());
        params.put("password", record.getPassword());
        params.put("displayName", record.getDisplayName());
        params.put("avatar", record.getAvatar());
        params.put("email", record.getEmail());
        params.put("age", record.getAge());
        params.put("status", record.getStatus());
        params.put("type", record.getType());
        params.put("remark", record.getRemark());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(INSERT_SQL, new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void insertSelective(User record) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder columnsFragment = new StringBuilder();
        StringBuilder valuesFragment = new StringBuilder();
        if (record.getUsername() != null) {
            columnsFragment.append("username, ");
            valuesFragment.append(":username, ");
            params.put("username", record.getUsername());
        }
        if (record.getPassword() != null) {
            columnsFragment.append("`password`, ");
            valuesFragment.append(":password, ");
            params.put("password", record.getPassword());
        }
        if (record.getDisplayName() != null) {
            columnsFragment.append("display_name, ");
            valuesFragment.append(":displayName, ");
            params.put("displayName", record.getDisplayName());
        }
        if (record.getAvatar() != null) {
            columnsFragment.append("avatar, ");
            valuesFragment.append(":avatar, ");
            params.put("avatar", record.getAvatar());
        }
        if (record.getEmail() != null) {
            columnsFragment.append("email, ");
            valuesFragment.append(":email, ");
            params.put("email", record.getEmail());
        }
        if (record.getAge() != null) {
            columnsFragment.append("age, ");
            valuesFragment.append(":age, ");
            params.put("age", record.getAge());
        }
        if (record.getStatus() != null) {
            columnsFragment.append("`status`, ");
            valuesFragment.append(":status, ");
            params.put("status", record.getStatus());
        }
        if (record.getType() != null) {
            columnsFragment.append("`type`, ");
            valuesFragment.append(":type, ");
            params.put("type", record.getType());
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
        getNamedParameterJdbcTemplate().update("INSERT INTO " + User.TABLE_NAME + " (" + columnsFragment + ") VALUES (" + valuesFragment + ")", new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void updateByPrimaryKeySelective(User record) {
        StringBuilder fragment = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("id", record.getId());
        if (record.getUsername() != null) {
            fragment.append("username = :username, ");
            params.put("username", record.getUsername());
        }
        if (record.getPassword() != null) {
            fragment.append("`password` = :password, ");
            params.put("password", record.getPassword());
        }
        if (record.getDisplayName() != null) {
            fragment.append("display_name = :displayName, ");
            params.put("displayName", record.getDisplayName());
        }
        if (record.getAvatar() != null) {
            fragment.append("avatar = :avatar, ");
            params.put("avatar", record.getAvatar());
        }
        if (record.getEmail() != null) {
            fragment.append("email = :email, ");
            params.put("email", record.getEmail());
        }
        if (record.getAge() != null) {
            fragment.append("age = :age, ");
            params.put("age", record.getAge());
        }
        if (record.getStatus() != null) {
            fragment.append("`status` = :status, ");
            params.put("status", record.getStatus());
        }
        if (record.getType() != null) {
            fragment.append("`type` = :type, ");
            params.put("type", record.getType());
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
        getNamedParameterJdbcTemplate().update("UPDATE " + User.TABLE_NAME + " SET " + fragment + " WHERE id = :id ", params);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public int deleteByPrimaryKey(Long id) {
        return getJdbcTemplate().update("DELETE FROM " + User.TABLE_NAME + " WHERE id = ? ", id);
    }

    @Generated(value="com.whaleread.codegen.api.WhaleGenerator")
    public void deleteByCriteria(Criteria criteria) {
        Map<String, Object> params = criteria.toSql();
        getNamedParameterJdbcTemplate().update("DELETE FROM " + User.TABLE_NAME + ' ' + criteria.getWhereClause(), params);
    }
}