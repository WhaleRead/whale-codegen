package com.whaleread.codegen.api.ddl;

import javax.sql.RowSet;
import javax.sql.RowSetListener;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by Dolphin on 2018/1/16
 */
public abstract class DdlResultSet implements RowSet {
    @Override
    public String getUrl() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUrl(String url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDataSourceName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDataSourceName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUsername(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPassword(String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTransactionIsolation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTransactionIsolation(int level) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Class<?>> getTypeMap() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getCommand() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCommand(String cmd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isReadOnly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setReadOnly(boolean value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxFieldSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaxFieldSize(int max) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxRows() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaxRows(int max) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getEscapeProcessing() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEscapeProcessing(boolean enable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getQueryTimeout() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setQueryTimeout(int seconds) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setType(int type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setConcurrency(int concurrency) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNull(String parameterName, int sqlType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNull(int paramIndex, int sqlType, String typeName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBoolean(String parameterName, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setByte(int parameterIndex, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setByte(String parameterName, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShort(int parameterIndex, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setShort(String parameterName, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setInt(int parameterIndex, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setInt(String parameterName, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLong(int parameterIndex, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLong(String parameterName, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFloat(int parameterIndex, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFloat(String parameterName, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDouble(int parameterIndex, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDouble(String parameterName, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setString(int parameterIndex, String x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setString(String parameterName, String x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBytes(String parameterName, byte[] x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDate(int parameterIndex, Date x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(int parameterIndex, Time x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObject(String parameterName, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setObject(int parameterIndex, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRef(int i, Ref x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBlob(int i, Blob x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBlob(String parameterName, Blob x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClob(int i, Clob x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClob(String parameterName, Clob x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setClob(String parameterName, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setArray(int i, Array x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDate(String parameterName, Date x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(String parameterName, Time x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearParameters() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addRowSetListener(RowSetListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeRowSetListener(RowSetListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRowId(String parameterName, RowId x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNString(int parameterIndex, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNString(String parameterName, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNClob(String parameterName, NClob value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNClob(String parameterName, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setURL(int parameterIndex, URL x) {
        throw new UnsupportedOperationException();
    }

//    @Override
//    public boolean next() throws SQLException {
//        return false;
//    }

    @Override
    public void close() {
        // ignore
    }

    @Override
    public boolean wasNull() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(int columnIndex) {
        return null;
    }

    @Override
    public boolean getBoolean(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getByte(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getShort(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInt(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFloat(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getDouble(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getBytes(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Date getDate(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Time getTime(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) {
        throw new UnsupportedOperationException();
    }

//    @Override
//    public String getString(String columnLabel) throws SQLException {
//        return null;
//    }

    @Override
    public boolean getBoolean(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getByte(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getShort(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInt(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFloat(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getDouble(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getBytes(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Date getDate(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Time getTime(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SQLWarning getWarnings() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearWarnings() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getCursorName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ResultSetMetaData getMetaData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getObject(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getObject(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int findColumn(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reader getCharacterStream(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reader getCharacterStream(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBeforeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAfterLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void beforeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void afterLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean first() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean last() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getRow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean absolute(int row) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean relative(int rows) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean previous() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFetchDirection(int direction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFetchDirection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFetchSize(int rows) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFetchSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getConcurrency() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean rowUpdated() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean rowInserted() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean rowDeleted() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNull(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateByte(int columnIndex, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateShort(int columnIndex, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateInt(int columnIndex, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateLong(int columnIndex, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFloat(int columnIndex, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateDouble(int columnIndex, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateString(int columnIndex, String x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateDate(int columnIndex, Date x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTime(int columnIndex, Time x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateObject(int columnIndex, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNull(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateByte(String columnLabel, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateShort(String columnLabel, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateInt(String columnLabel, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateLong(String columnLabel, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFloat(String columnLabel, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateDouble(String columnLabel, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateString(String columnLabel, String x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateDate(String columnLabel, Date x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTime(String columnLabel, Time x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateObject(String columnLabel, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertRow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateRow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteRow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void refreshRow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void cancelRowUpdates() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveToInsertRow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveToCurrentRow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Statement getStatement() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Ref getRef(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Blob getBlob(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Clob getClob(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Array getArray(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Ref getRef(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Blob getBlob(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Clob getClob(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Array getArray(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public URL getURL(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public URL getURL(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateRef(int columnIndex, Ref x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateRef(String columnLabel, Ref x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateClob(int columnIndex, Clob x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateClob(String columnLabel, Clob x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateArray(int columnIndex, Array x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateArray(String columnLabel, Array x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RowId getRowId(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RowId getRowId(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getHoldability() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isClosed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNString(int columnIndex, String nString) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNString(String columnLabel, String nString) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NClob getNClob(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NClob getNClob(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getNString(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getNString(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        throw new UnsupportedOperationException();
    }
}
