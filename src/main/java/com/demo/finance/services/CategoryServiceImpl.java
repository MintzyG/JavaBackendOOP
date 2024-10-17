package com.demo.finance.services;

import com.demo.finance.exceptions.FinanceBadRequestException;
import com.demo.finance.exceptions.FinanceResourceNotFoundException;
import com.demo.finance.models.Category;
import com.demo.finance.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> fetchAllCategories(Integer user_id) {
        return categoryRepository.findAll(user_id);
    }

    @Override
    public Category fetchCategoryById(Integer user_id, Integer category_id) throws FinanceResourceNotFoundException {
        return categoryRepository.findById(user_id, category_id);
    }

    @Override
    public Category addCategory(Integer user_id, String title, String description) throws FinanceBadRequestException {
        int category_id = categoryRepository.create(user_id, title, description);
        return categoryRepository.findById(user_id, category_id);
    }

    @Override
    public void updateCategory(Integer user_id, Integer category_id, Category category) throws FinanceBadRequestException {
        categoryRepository.update(user_id, category_id, category);
    }

    @Override
    public void deleteCategoryWithAllTransactions(Integer user_id, Integer category_id) throws FinanceResourceNotFoundException {

    }
}
