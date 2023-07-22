/**
 * @version 1.0
 * @author Bappi Mazumder
 * @since 7/22/2023
 * Project Name : spring-security-OAuth2-Google-impl
 */

package com.bappi.controller;

import com.bappi.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping("/")
    public ResponseEntity<String> getPublicData() {
        return ResponseEntity.ok("This is public data");
    }

    @GetMapping("/user-data")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity.ok(appService.getJwtToken());
    }

}
