package com.demo.finance.services;

import com.demo.finance.exceptions.FinanceBadRequestException;
import com.demo.finance.exceptions.FinanceResourceNotFoundException;
import com.demo.finance.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> fetchAllCategories(Integer user_id);
    Category fetchCategoryById(Integer user_id, Integer category_id) throws FinanceResourceNotFoundException;
    Category addCategory(Integer user_id, String title, String description) throws FinanceBadRequestException;
    void updateCategory(Integer user_id, Integer category_id, Category category) throws FinanceBadRequestException;
    void deleteCategoryWithAllTransactions(Integer user_id, Integer category_id) throws FinanceResourceNotFoundException;
}
