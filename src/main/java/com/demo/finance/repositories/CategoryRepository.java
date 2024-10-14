package com.demo.finance.repositories;

import com.demo.finance.exceptions.FinanceBadRequestException;
import com.demo.finance.exceptions.FinanceResourceNotFoundException;
import com.demo.finance.models.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll(Integer user_id) throws FinanceResourceNotFoundException;
    Category findById(Integer user_id, Integer category_id) throws FinanceResourceNotFoundException;
    Integer create(Integer user_id, String title, String description) throws FinanceBadRequestException;
    void update(Integer user_id, Integer category_id, Category category) throws FinanceBadRequestException;
    void deleteById(Integer user_id, Integer category_id);
}
