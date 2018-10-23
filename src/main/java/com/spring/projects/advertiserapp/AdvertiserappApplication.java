package com.spring.projects.advertiserapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.flywaydb.core.Flyway;
//import org.h2.jdbcx.JdbcConnectionPool;
//import org.h2.tools.Server;

@SpringBootApplication(scanBasePackages = {"com.spring.projects.advertiserapp"})
@EnableSwagger2
public class AdvertiserappApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvertiserappApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/advertiser/**"))
                .build();
    }

	   /*private static void initSchema() {
	       Flyway flyway = new Flyway();
	       flyway.setDataSource(JDBC_URL, "sa", "");
	       flyway.migrate();
	   }*/
}
