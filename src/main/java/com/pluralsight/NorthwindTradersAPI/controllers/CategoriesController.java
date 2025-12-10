package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;

import com.pluralsight.NorthwindTradersAPI.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {


    private static final String ID_PATH = "/{id}";


    private final CategoryDao categoryDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @GetMapping
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @GetMapping(ID_PATH)
    public Category getCategoryById(@PathVariable int id) {
        return categoryDao.getCategoryId(id).stream().findFirst().orElse(null);
    }

    @PostMapping
    public void insertCategory(@RequestBody Category category) {
        categoryDao.insertCategory(category);
    }

    @PutMapping
    public void updateCategory(@RequestBody Category category) {
        categoryDao.updateCategory(category.getCategoryID(), category);
    }


}
