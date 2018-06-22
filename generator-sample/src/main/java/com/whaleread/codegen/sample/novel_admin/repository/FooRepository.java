package com.whaleread.codegen.sample.novel_admin.repository;

import com.whaleread.codegen.runtime.jdbc.Criteria;
import com.whaleread.codegen.runtime.jdbc.spring.AliasBeanPropertyRowMapper;
import com.whaleread.codegen.sample.novel_admin.model.Foo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * table: novel_admin.foo
 */
@Generated(value = "com.whaleread.codegen.api.WhaleGenerator", comments = "Source Table: novel_admin.foo")
@Repository
public class FooRepository extends NamedParameterJdbcDaoSupport {

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    private AliasBeanPropertyRowMapper<Foo> rowMapper = new AliasBeanPropertyRowMapper<>(Foo.TABLE_ALIAS, Foo.class);

    @Autowired
    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void inject(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public Optional<Foo> selectByPrimaryKey(Long id) {
        return getJdbcTemplate().query("SELECT " + Foo.BASE_COLUMNS + " FROM " + Foo.TABLE_NAME + " WHERE id = ? ", new Object[] { id }, rs -> rs.next() ? Optional.of(rowMapper.mapRow(rs, 0)) : Optional.empty());
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public <T extends Foo> Optional<T> selectByPrimaryKey(Long id, Class<T> expectedType) {
        return getJdbcTemplate().query("SELECT " + Foo.BASE_COLUMNS + " FROM " + Foo.TABLE_NAME + " WHERE id = ? ", new Object[] { id }, rs -> rs.next() ? Optional.of(rowMapper.mapRow(rs, 0, expectedType)) : Optional.empty());
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public int countByCriteria(Criteria criteria) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().queryForObject("SELECT COUNT(0) FROM " + Foo.TABLE_NAME + " f " + criteria.getWhereClause(), params, int.class);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public List<Foo> selectByCriteria(Criteria criteria, int offset, int count) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().query("SELECT " + Foo.ALIASED_BASE_COLUMNS + " FROM " + Foo.TABLE_NAME + " f " + criteria.getWhereClause() + criteria.getOrderByClause() + " LIMIT " + offset + ',' + count, params, rowMapper);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public <T extends Foo> List<T> selectByCriteria(Criteria criteria, int offset, int count, Class<T> expectedType) {
        Map<String, Object> params = criteria.toSql();
        return getNamedParameterJdbcTemplate().query("SELECT " + Foo.ALIASED_BASE_COLUMNS + " FROM " + Foo.TABLE_NAME + " f " + criteria.getWhereClause() + criteria.getOrderByClause() + " LIMIT " + offset + ',' + count, params, (rs, rowNum) -> rowMapper.mapRow(rs, rowNum, expectedType));
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void insert(Foo record) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", record.getName());
        params.put("birth", record.getBirth());
        params.put("avatar", record.getAvatar());
        params.put("email", record.getEmail());
        params.put("age", record.getAge());
        params.put("status", record.getStatus());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update("INSERT INTO " + Foo.TABLE_NAME + "(`name`, birth, avatar, email, age, `status`) VALUES (:name, :birth, :avatar, :email, :age, :status)", new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void insertSelective(Foo record) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder columnsFragment = new StringBuilder();
        StringBuilder valuesFragment = new StringBuilder();
        if (record.getName() != null) {
            columnsFragment.append("`name`, ");
            valuesFragment.append(":name, ");
            params.put("name", record.getName());
        }
        if (record.getBirth() != null) {
            columnsFragment.append("birth, ");
            valuesFragment.append(":birth, ");
            params.put("birth", record.getBirth());
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
        getNamedParameterJdbcTemplate().update("INSERT INTO " + Foo.TABLE_NAME + " (" + columnsFragment + ") VALUES (" + valuesFragment + ")", new MapSqlParameterSource(params), keyHolder);
        record.setId(keyHolder.getKey().longValue());
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void updateByPrimaryKeySelective(Foo record) {
        StringBuilder fragment = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("id", record.getId());
        if (record.getId() != null) {
            fragment.append("id = :id, ");
            params.put("id", record.getId());
        }
        if (record.getName() != null) {
            fragment.append("`name` = :name, ");
            params.put("name", record.getName());
        }
        if (record.getBirth() != null) {
            fragment.append("birth = :birth, ");
            params.put("birth", record.getBirth());
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
        getNamedParameterJdbcTemplate().update("UPDATE " + Foo.TABLE_NAME + " SET " + fragment + " WHERE id = :id ", params);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public int deleteByPrimaryKey(Long id) {
        return getJdbcTemplate().update("DELETE FROM " + Foo.TABLE_NAME + " WHERE id = ? ", id);
    }

    @Generated(value = "com.whaleread.codegen.api.WhaleGenerator")
    public void deleteByCriteria(Criteria criteria) {
        Map<String, Object> params = criteria.toSql();
        getNamedParameterJdbcTemplate().update("DELETE FROM " + Foo.TABLE_NAME + ' ' + criteria.getWhereClause(), params);
    }
}
