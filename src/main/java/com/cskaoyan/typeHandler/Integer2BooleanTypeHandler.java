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

@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(Boolean.class)
public class Integer2BooleanTypeHandler implements TypeHandler<Boolean> {

    ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
        String value = mapper.writeValueAsString(aBoolean);
        if("true".equals(value)){
            preparedStatement.setInt(i,1);
        }else {
            preparedStatement.setInt(i,0);
        }
    }

    @Override
    public Boolean getResult(ResultSet resultSet, String s) throws SQLException {
        int anInt = resultSet.getInt(s);
        return parse(anInt);
    }

    private boolean parse(int anInt) {
        if(anInt==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Boolean getResult(ResultSet resultSet, int i) throws SQLException {
        int anInt = resultSet.getInt(i);
        return parse(anInt);
    }

    @Override
    public Boolean getResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getBoolean(i);
    }
}
