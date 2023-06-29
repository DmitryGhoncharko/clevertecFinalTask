package ru.clevertec.ecl.clevertecfinaltask.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.liquibase.change-log}")
    private String changeLog;

    @Value("classpath:db.changelog/data.sql")
    private org.springframework.core.io.Resource dataScript;

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
    @PostConstruct
    public void init() {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(prodDataSource());
        initializer.setDatabasePopulator(getDatabasePopulator());
        initializer.setEnabled(true);
        initializer.afterPropertiesSet();
    }

    private DatabasePopulator getDatabasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dataScript);
        return populator;
    }
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changeLog);
        return liquibase;
    }
}

