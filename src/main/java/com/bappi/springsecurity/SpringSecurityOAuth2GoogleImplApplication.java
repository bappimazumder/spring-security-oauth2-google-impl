package com.bappi.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class SpringSecurityOAuth2GoogleImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOAuth2GoogleImplApplication.class, args);
    }

}
