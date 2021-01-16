package com.cskaoyan.typeHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
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


@MappedTypes(String[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class String2StringArrayTypeHandler implements TypeHandler<String[]> {
    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        String value = objectMapper.writeValueAsString(strings);
        preparedStatement.setString(i, value);

    }

    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
//        String[] strings = objectMapper.readValue(string, String[].class);
        String[] strings = parse(string);
        return strings;
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {


        String result = resultSet.getString(i);
        return parse(result);

    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return parse(string);
    }

    private String[] parse(String result) { //转换成对应的类型
        String[] strings = new String[0];
        if (result == null) {
            return strings;
        }
        try {
            strings = objectMapper.readValue(result, String[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return strings;
    }

}
