package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> getAllCategories();
    public List<Category> getCategoryId(int id);
    public void insertCategory(Category category);
    public void updateCategory(int id,Category category);
    public void deleteCategory(int id);
}
