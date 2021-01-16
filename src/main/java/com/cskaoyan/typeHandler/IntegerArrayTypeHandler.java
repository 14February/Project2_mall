package com.cskaoyan.typeHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Integer[].class)
public class IntegerArrayTypeHandler implements TypeHandler<Integer[]>{

    ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement preparedStatement,int i,Integer[] integers,JdbcType jdbcType) throws SQLException
    {
        String value = mapper.writeValueAsString(integers);
        preparedStatement.setString(i, value);
    }

    @SneakyThrows
    @Override
    public Integer[] getResult(ResultSet resultSet,String columnName) throws SQLException
    {
        String s = resultSet.getString(columnName);
        if("".equals(s)) return null;
        return mapper.readValue(s,Integer[].class);
    }

    @SneakyThrows
    @Override
    public Integer[] getResult(ResultSet resultSet,int index) throws SQLException
    {
        String s = resultSet.getString(index);
        return mapper.readValue(s,Integer[].class);
    }

    @SneakyThrows
    @Override
    public Integer[] getResult(CallableStatement callableStatement,int index) throws SQLException
    {
        String s = callableStatement.getString(index);
        return mapper.readValue(s,Integer[].class);
    }
}
