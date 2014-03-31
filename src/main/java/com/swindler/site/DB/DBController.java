package com.swindler.site.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by sw on 26.03.14.
 */
@Component
public class DBController {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DBConfiguration dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
