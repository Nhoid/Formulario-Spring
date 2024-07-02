package com.project.form.configuration;


import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({FlywayProperties.class})
public class DataBaseConfig {

    //CONFIGURACOES DEFINIDAS NO APPLICATION PROPERTIES
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String usarname;

    @Value("${spring.datasource.password}")
    private String password;


    //CONFIGURACAO DO FLYWAY
    @Bean(initMethod = "migrate")
    public Flyway flyway(FlywayProperties flywayProperties) {

        return Flyway.configure()
                .dataSource(url, usarname, password)
                .locations(flywayProperties.getLocations().toArray(String[]::new))
                .baselineOnMigrate(false).load();

    }

    //CONFIGURACOES DO DATASOURCE
    @Bean
    @Primary
    public DataSource dataSource(){

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.url(url);
        dataSourceBuilder.username(usarname);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();


    }



}
