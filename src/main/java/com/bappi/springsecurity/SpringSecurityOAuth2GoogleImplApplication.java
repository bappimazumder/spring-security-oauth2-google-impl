package com.bappi.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class SpringSecurityOAuth2GoogleImplApplication {

    @GetMapping
    public String greetings(){
        return "Welcome to Google Signing!!!";
    }
    @GetMapping("/user")
    public Principal greetings(Principal principal){
        System.out.println("username : " + principal.getName());
        return principal;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOAuth2GoogleImplApplication.class, args);
    }

}
