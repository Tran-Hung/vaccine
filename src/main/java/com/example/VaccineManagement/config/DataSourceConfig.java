package com.example.VaccineManagement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by SonPhung on Friday, 14-Apr-2023
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        log.info("Configuring OLS-DS");


        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/vaccine_management");
        config.setUsername("root");
        config.setPassword("");

        return new HikariDataSource(config);
    }
}
