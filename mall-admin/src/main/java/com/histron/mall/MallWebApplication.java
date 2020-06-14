package com.histron.mall;

import com.histron.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.histron")
@EntityScan(value="com.histron.model.entity")
@EnableJpaRepositories("com.histron.mall.dao")
public class MallWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallWebApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }


}
