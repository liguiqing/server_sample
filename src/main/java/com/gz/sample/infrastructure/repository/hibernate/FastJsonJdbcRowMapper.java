package com.gz.sample.infrastructure.repository.hibernate;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FastJsonJdbcRowMapper implements RowMapper<JSONObject> {
    @Override
    public JSONObject mapRow(ResultSet rs, int index) throws SQLException {
        var count = rs.getMetaData().getColumnCount();
        var json = new JSONObject(count);
        for(int i = 1;i <= count; i++){
            var field = rs.getMetaData().getColumnLabel(i);
            json.put(field, rs.getObject(i));
        }
        return json;
    }
}
