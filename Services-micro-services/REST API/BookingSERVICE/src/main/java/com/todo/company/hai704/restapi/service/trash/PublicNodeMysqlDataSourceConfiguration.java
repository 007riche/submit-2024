//package com.todo.company.hai704.restapi.service.trash;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
////        (proxyBeanMethods = true)
//@DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "publicEntityManagerFactory",
//        transactionManagerRef = "publicTransactionManager",
//        basePackages = { "com.todo.company.hai704.restapi.service.data.publisher_node.repository.mysql" }
//)
//public class PublicNodeMysqlDataSourceConfiguration {
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name="publicDataSourceProperties")
////    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.db1.mysql")
//    public DataSourceProperties publicDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name="publicDataSource")
////    @Primary
////    @ConfigurationProperties(prefix="spring.datasource.db2")
////    public HikariDataSource publicDataSource(DataSourceProperties publicDataSourceProperties) {
////        return publicDataSourceProperties.initializeDataSourceBuilder()
////                .type(HikariDataSource.class)
////                .build();
////    }
//
//    public DataSource publicDataSource(DataSourceProperties publicDataSourceProperties) {
//        return DataSourceBuilder.create().build();
//    }
//
////    @Primary
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name = "publicEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean publicEntityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("publicDataSource")
//    DataSource publicDataSource) {
//        return builder
//                .dataSource(publicDataSource)
//                .packages("com.todo.company.hai704.restapi.service.data.publisher_node.entity")
//                .build();
//    }
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
////    @Primary
//    @Bean(name = "publicTransactionManager")
//    public PlatformTransactionManager publicTransactionManager(
//            @Qualifier("publicEntityManagerFactory") EntityManagerFactory publicEntityManagerFactory) {
//        return new JpaTransactionManager(publicEntityManagerFactory);
//    }
//}
