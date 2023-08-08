package com.sparta.developmentgroup1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // JPA Auditing 활성화
public class DevelopmentGroup1Application {

    public static void main(String[] args) {
        SpringApplication.run(DevelopmentGroup1Application.class, args);
    }

}
