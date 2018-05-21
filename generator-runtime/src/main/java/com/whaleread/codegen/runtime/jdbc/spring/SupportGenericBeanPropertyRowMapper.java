package com.whaleread.codegen.runtime.jdbc.spring;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * So we don't need to copy properties cross beans
 *
 * @author Dolphin
 */
public interface SupportGenericBeanPropertyRowMapper<T> extends RowMapper<T> {
    <S extends T> S mapRow(ResultSet rs, int rowNumber, Class<S> expectedType) throws SQLException;
}