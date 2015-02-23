package com.sapient.couponclues.config;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@ComponentScan("com.sapient")
@EnableElasticsearchRepositories(basePackages = "com.sapient.couponclues.repository")
public class CouponCluesApplication extends SpringBootServletInitializer {
    public static void main(final String[] args) throws Exception {

        SpringApplication.run(CouponCluesApplication.class, args);
    }

    @Autowired
    Client elasticSearchClient;

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {

        return application.sources(CouponCluesApplication.class);
    }

}