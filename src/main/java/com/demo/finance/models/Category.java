package com.demo.finance.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {
    private Integer category_id;
    private Integer user_id;
    private String title;
    private String description;
    private double total_expense;

    public Category(Integer category_id, Integer user_id, String title, String description, double total_expense) {
        this.category_id = category_id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.total_expense = total_expense;
    }
}
