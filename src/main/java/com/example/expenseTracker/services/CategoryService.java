package com.example.expenseTracker.services;

import com.example.expenseTracker.domain.Category;
import com.example.expenseTracker.exceptions.EtResourceNotFoundException;
import java.util.List;

public interface CategoryService {

    List<Category> fetchAllCategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Category addCategory(Integer userId, String title, String description) throws EtResourceNotFoundException;

    void updateCategory(Integer userId, Integer categoryId, Category category) throws EtResourceNotFoundException;

    void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

}
