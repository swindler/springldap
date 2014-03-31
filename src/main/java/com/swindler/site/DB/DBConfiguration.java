package com.swindler.site.DB;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by sw on 26.03.14.
 */
@Configuration
public class DBConfiguration extends DriverManagerDataSource {
    public DBConfiguration() {
        this.setDriverClassName("org.postgresql.Driver");
        this.setUrl("jdbc:postgresql://localhost/db");
        this.setUsername("postgres");
        this.setPassword("******");
    }
}
