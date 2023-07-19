package com.vadim.springcore.config;

import com.zaxxer.hikari.HikariConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "com.vadim.springcore")
@RequiredArgsConstructor
@PropertySource("classpath:properties/application.properties")
public class ApplicationConfig implements WebApplicationInitializer {

    private final Environment environment;


    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getRequiredProperty("db.url"));
        hikariConfig.setPassword(environment.getRequiredProperty("db.password"));
        hikariConfig.setUsername(environment.getRequiredProperty("db.username"));
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "512");
        return hikariConfig.getDataSource();
    }

//    @Bean
//    @Profile("dev")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername(environment.getRequiredProperty("db.username"));
//        dataSource.setPassword(environment.getRequiredProperty("db.password"));
//        dataSource.setDriverClassName(environment.getRequiredProperty("db.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("db.url"));
//        return dataSource;
//    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db-master-changelog.yaml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    @Bean
    public PlatformTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("spring.profile.active", "dev");
    }
}
