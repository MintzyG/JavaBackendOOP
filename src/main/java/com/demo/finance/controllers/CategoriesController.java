package com.demo.finance.controllers;

import com.demo.finance.models.Category;
import com.demo.finance.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String getAllCategories(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("user_id");
        return "Authenticated user: " + userId;
    }

    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request,
                                                @RequestBody Map<String, Object> categoryMap) {
        int user_id = (Integer) request.getAttribute("user_id");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(user_id, title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
