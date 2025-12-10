package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private static final String BASE_PATH = "/{id}";
    private static final String ID_PATH = "/id/{productId}";
    private static final String NAME_PATH = "/name/{productName}";
    private static final String CATEGORY_PATH = "/category/{categoryName}";
    private static final String PRICE_PATH = "/price/{price}";

    private final ProductDao productDao;

    @Autowired
    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @GetMapping(BASE_PATH)
    public Product getProduct(@PathVariable int id) {
        return productDao.getProductById(id).stream().findFirst().orElse(null);
    }

    @GetMapping(CATEGORY_PATH)
    public List<Product> getProductsByCategory(@PathVariable int CategoryId) {
        return productDao.getProductByCategoryId(CategoryId);
    }

    @GetMapping(ID_PATH)
    public List<Product> getProductById(@PathVariable int productId) {
        return productDao.getProductById(productId);
    }

    @GetMapping(NAME_PATH)
    public List<Product> getProductByName(@PathVariable String productName) {
        return productDao.getProductByName(productName);
    }

    @GetMapping(PRICE_PATH)
    public List<Product> getProductsByPrice(@PathVariable double price) {
        return productDao.getProductByPrice(price);
    }

    @PostMapping
    public void add(@RequestBody Product product) {
        productDao.add(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productDao.updateProduct(product.getProductId(),product);
    }


    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productDao.deleteProduct(product.getProductId());
    }


}

