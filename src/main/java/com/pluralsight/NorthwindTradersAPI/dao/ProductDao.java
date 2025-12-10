package com.pluralsight.NorthwindTradersAPI.dao;



import com.pluralsight.NorthwindTradersAPI.model.Product;

import java.util.List;

public interface ProductDao {

    public void add(Product product);
    public List<Product> getAllProducts();
    public List<Product>getProductByName(String name);
    public List<Product> getProductByCategoryId(int categoryId);
    public List<Product> getProductByPrice(double price);
    public List<Product> getProductById(int id);
    public void updateProduct(int id, Product product);
    public void deleteProduct(int id);
}
