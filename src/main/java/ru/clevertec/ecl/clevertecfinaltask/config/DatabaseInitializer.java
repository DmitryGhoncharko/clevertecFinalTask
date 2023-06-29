package ru.clevertec.ecl.clevertecfinaltask.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import javax.sql.DataSource;

@Configuration
public class DatabaseInitializer {
    private final DataSource dataSource;
    private final org.springframework.core.io.Resource dataScript;

    public DatabaseInitializer(DataSource dataSource, @Value("classpath:db.changelog/changes/data.sql") org.springframework.core.io.Resource dataScript) {
        this.dataSource = dataSource;
        this.dataScript = dataScript;
    }

    @PostConstruct
    public void init() {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(getDatabasePopulator());
        initializer.setEnabled(true);
        initializer.afterPropertiesSet();
    }

    private DatabasePopulator getDatabasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dataScript);
        return populator;
    }
}


