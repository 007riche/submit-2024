//package com.todo.company.hai704.restapi.service.trash;
//
//import com.zaxxer.hikari.HikariDataSource;
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
//        entityManagerFactoryRef = "nodeEntityManagerFactory",
//        transactionManagerRef = "nodeTransactionManager",
//        basePackages = { "com.todo.company.hai704.restapi.service.data.service_node.repository.mysql" }
//)
//public class MainServiceNodeMysqlDataSourceConfiguration {
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name="nodeDataSourceProperties")
//    @Primary
//    @ConfigurationProperties("spring.datasource.db2.mysql")
//    public DataSourceProperties nodeDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name="nodeDataSource")
//    @Primary
//    public DataSource nodeDataSource(@Qualifier("nodeDataSourceProperties") DataSourceProperties nodeDataSourceProperties) {
//        System.err.println("In MainSql: ");
//        System.err.println(nodeDataSourceProperties.getDriverClassName());
//        return DataSourceBuilder.create()
//                .build();
////        return nodeDataSourceProperties.initializeDataSourceBuilder()
////                .type(Mysql.class)
////                .build();
//    }
//
//    @Primary
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Bean(name = "nodeEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean nodeEntityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("nodeDataSource") DataSource nodeDataSource) {
//
//        return builder
//                .dataSource(nodeDataSource)
//                .packages("com.todo.company.hai704.restapi.service.data.service_node.repository.mysql")
//                .build();
//    }
//
//    @DependsOn({"dataSourcesInitialization", "compleleteProperties"})
//    @Primary
//    @Bean(name = "nodeTransactionManager")
//    public PlatformTransactionManager nodeTransactionManager(
//            @Qualifier("nodeEntityManagerFactory") EntityManagerFactory nodeEntityManagerFactory) {
//        return new JpaTransactionManager(nodeEntityManagerFactory);
//    }
//}
