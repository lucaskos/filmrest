package com.filmdatabase.filmdb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.filmdatabase.filmdb",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class JpaConfiguration {

    @Autowired
    private Environment env;

    @Value("10")
    private int maxPoolSize;

    /*
 * Populate SpringBoot DataSourceProperties object directly from application.yml
 * based on prefix.Thanks to .yml, Hierachical data is mapped out of the box with matching-name
 * properties of DataSourceProperties object].
 */
    @Bean
    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/films");
        dataSource.setUsername("root");
        dataSource.setPassword("lucas7");
        return dataSource;
    }

    /*
     * Entity Manager Factory setup.
     */
    @Bean(name = "em")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[]{"com.filmdatabase.filmdb"});
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    /*
     * Provider specific adapter.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        //todo check this
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    /*
     * Here you can specify any provider specific properties.
     * todo add properites for cache and cache provider
     */
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("spring.datasource.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.datasource.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("spring.datasource.hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("spring.datasource.hibernate.format_sql"));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        try {
            txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return txManager;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.filmdatabase.filmdb.application.model"});
        sessionFactory.setHibernateProperties(jpaProperties());
        return sessionFactory;
    }

}