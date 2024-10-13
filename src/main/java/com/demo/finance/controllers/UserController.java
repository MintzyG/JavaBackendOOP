package com.demo.finance.controllers;

import com.demo.finance.Constants;
import com.demo.finance.models.User;
import com.demo.finance.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/login")

    public ResponseEntity<Map<String, String>> login_user(@RequestBody Map<String, Object> userMap) {
        String email = userMap.get("email").toString();
        String password = userMap.get("password").toString();
        User user = userService.ValidateUser(email, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("user_id", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getFirst_name())
                .compact();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return tokenMap;
    }
}
