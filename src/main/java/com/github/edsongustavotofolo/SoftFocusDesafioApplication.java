package com.github.edsongustavotofolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.github.edsongustavotofolo.domain.repository")
@SpringBootApplication
public class SoftFocusDesafioApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SoftFocusDesafioApplication.class, args);
    }
}
