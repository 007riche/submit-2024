//package com.todo.company.hai704.restapi.service.trash;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Primary;
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
//        entityManagerFactoryRef = "nodeH2EntityManagerFactory",
//        transactionManagerRef = "nodeH2TransactionManager",
//        basePackages = { "com.todo.company.hai704.restapi.service.data.service_node.repository.h2" }
//)
//public class MainServiceNodeH2DataSourceConfiguration {
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name="nodeH2DataSourceProperties")
////    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.db4.h2")
//    public DataSourceProperties nodeH2DataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name="nodeH2DataSource")
////    @Primary
////    @ConfigurationProperties(prefix="spring.datasource.db2")
//    public HikariDataSource nodeH2DataSource(DataSourceProperties nodeH2DataSourceProperties) {
//        return nodeH2DataSourceProperties.initializeDataSourceBuilder()
//                .type(HikariDataSource.class)
//                .build();
//    }
//
////    public DataSource nodeDataSource() {
////        return DataSourceBuilder.create().build();
////    }
//
////    @Primary
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name = "nodeH2EntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean nodeH2EntityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("nodeH2DataSource") DataSource nodeDataSource) {
//        return builder
//                .dataSource(nodeDataSource)
//                .packages("com.todo.company.hai704.restapi.service.data.service_node.entity")
//                .build();
//    }
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name = "nodeH2TransactionManager")
//    public PlatformTransactionManager nodeH2TransactionManager(
//            @Qualifier("nodeH2EntityManagerFactory") EntityManagerFactory nodeH2EntityManagerFactory) {
//        return new JpaTransactionManager(nodeH2EntityManagerFactory);
//    }
//}
