package com.filmdatabase.filmdb.configuration;

import com.filmdatabase.filmdb.application.commons.CacheConstants;
import com.filmdatabase.filmdb.configuration.common.ConfigurationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
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
@EnableCaching
//@PropertySource("classpath:application.yml")
public class JpaConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaConfiguration.class);

    @Autowired
    private Environment env;

    /**
     * Populate SpringBoot DataSourceProperties object directly from application.yml
     * based on prefix.Thanks to .yml, Hierachical data is mapped out of the box with matching-name
     * properties of DataSourceProperties object].
     */
    @Bean
    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        LOGGER.debug("DataSource file initialized");
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(ConfigurationConstants.DATASOURCE_PATH));
        dataSource.setUrl(env.getProperty(ConfigurationConstants.DATASOURCE_URL));
        dataSource.setUsername(env.getProperty(ConfigurationConstants.DATASOURCE_USERNAME));
        dataSource.setPassword(env.getProperty(ConfigurationConstants.DATASOURCE_PASSWORD));
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

    /**
     * Here you can specify any provider specific properties.
     * todo add properites for cache and cache provider
     */
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put(ConfigurationConstants.HIBERNATE_DIALECT_KEY,
                env.getRequiredProperty(ConfigurationConstants.HIBERNATE_DIALECT_PROP_VAL));
        properties.put(ConfigurationConstants.HIBERNATE_HBM2DLL_KEY,
                env.getRequiredProperty(ConfigurationConstants.HIBERNATE_HBM2DLL_PROP_VAL));
        properties.put(ConfigurationConstants.HIBERNATE_SHOW_SQL_KEY,
                env.getRequiredProperty(ConfigurationConstants.HIBERNATE_SHOW_SQL_PROP_VAL));
        properties.put(ConfigurationConstants.HIBERNATE_FORMAT_SQL_KEY,
                env.getRequiredProperty(ConfigurationConstants.HIBERNATE_FORMAT_SQL_PROP_VAL));
        properties.put(ConfigurationConstants.HIBERNATE_DEFAULT_SCHEMA_KEY,
                env.getRequiredProperty(ConfigurationConstants.HIBERNATE_DEFAULT_SCHEMA_PROP_VAL));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        try {
            txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        } catch (NamingException e) {
            LOGGER.error("Error while initializing transaction manager " + e.getMessage() +
                    " ,casusd by " + e.getCause());
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

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
//        LOGGER.info("property file initialized");
//        return new PropertySourcesPlaceholderConfigurer();
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        LOGGER.info("Properties loaded " + yaml.toString());
        return propertySourcesPlaceholderConfigurer;
    }

}