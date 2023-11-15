package com.dac.api.app.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.api.app.dto.AuthDTO;
import com.dac.api.app.service.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth Controller")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody AuthDTO user) {
        try {
            var response = this.userService.authenticate(user);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
