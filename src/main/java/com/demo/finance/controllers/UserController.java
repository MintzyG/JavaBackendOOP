package com.demo.finance.controllers;

import com.demo.finance.models.User;
import com.demo.finance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register_user(@RequestBody Map<String, Object> userMap) {
        String firstName = userMap.get("first_name").toString();
        String lastName = userMap.get("last_name").toString();
        String email = userMap.get("email").toString();
        String password = userMap.get("password").toString();
        User user = userService.RegisterUser(firstName, lastName, email, password);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Registered successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")

    public ResponseEntity<Map<String, String>> login_user(@RequestBody Map<String, Object> userMap) {
        String email = userMap.get("email").toString();
        String password = userMap.get("password").toString();
        User user = userService.ValidateUser(email, password);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Login successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
