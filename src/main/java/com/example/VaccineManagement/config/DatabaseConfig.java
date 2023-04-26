package com.example.VaccineManagement.config;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SonPhung on 15-Dec-2017
 */
@Configuration
public class DatabaseConfig {

    @Autowired
    private DataSource dataSource;

//    @Autowired
//    private SchemaPerTenantConnectionProvider schemaPerTenantConnectionProvider;
//
//    @Autowired
//    private CustomsTenantIdentifierResolver customsTenantIdentifierResolver;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Primary
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("ols-pu");
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.VaccineManagement.entity");

        em.setJpaVendorAdapter(this.jpaVendorAdapter());

        em.setMappingResources("orm.xml");

        Map<String, Object> jpaProperties = new HashMap<>();
//        jpaProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
//        jpaProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, schemaPerTenantConnectionProvider);
//        jpaProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, customsTenantIdentifierResolver);
        //jpaProperties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
        jpaProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

        // Fix PgConnection.createClob() is not yet implemented
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");

        em.setJpaPropertyMap(jpaProperties);
        return em;
    }

    @Primary
    @Bean("transactionManager")
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

}
