//package com.todo.company.hai704.restapi.service.trash;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration(proxyBeanMethods = true)
//@DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//@EnableTransactionManagement
//@EnableSpringConfigured
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "publicH2EntityManagerFactory",
//        transactionManagerRef = "publicH2TransactionManager",
//        basePackages = { "com.todo.company.hai704.restapi.service.data.publisher_node.repository.h2" }
//)
//public class PublicNodeH2DataSourceConfiguration {
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name="publicH2DataSourceProperties")
////    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.db3.h2")
//    public DataSourceProperties publicH2DataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name="publicH2DataSource")
////    @Primary
////    @ConfigurationProperties(prefix="spring.datasource.db2")
//    public HikariDataSource publicH2DataSource(DataSourceProperties publicH2DataSourceProperties) {
//        return publicH2DataSourceProperties.initializeDataSourceBuilder()
//                .type(HikariDataSource.class)
//                .build();
//    }
//
////    public DataSource publicH2DataSource() {
////        return DataSourceBuilder.create().build();
////    }
//
////    @Primary
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name = "publicH2EntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean publicH2EntityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("publicH2DataSource") DataSource publicH2DataSource) {
//        return builder
//                .dataSource(publicH2DataSource)
//                .packages("com.todo.company.hai704.restapi.service.data.publisher_node.entity")
//                .build();
//    }
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name = "publicH2TransactionManager")
//    public PlatformTransactionManager publicH2TransactionManager(
//            @Qualifier("publicH2EntityManagerFactory") EntityManagerFactory publicH2EntityManagerFactory) {
//        return new JpaTransactionManager(publicH2EntityManagerFactory);
//    }
//}
