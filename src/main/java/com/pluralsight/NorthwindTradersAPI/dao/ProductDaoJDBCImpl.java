package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoJDBCImpl implements ProductDao {

    private List<Product> products;
    private DataSource dataSource;

    @Autowired
    public ProductDaoJDBCImpl(DataSource dataSource) {
        this.products = new ArrayList<>();
        this.dataSource = dataSource;
    }


    @Override
    public void add(Product product) {
        String sql = "INSERT INTO Products (ProductName, CategoryID, UnitPrice) VALUES (?,?,?);";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products;";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getInt(3), rows.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public List<Product> getProductById(int id) {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products WHERE ProductID = ?;";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rows = statement.executeQuery();
            if (rows.next()) {
                this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getInt(3), rows.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public List<Product> getProductByName(String name) {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products WHERE ProductName LIKE ?;";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getInt(3), rows.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.products;
    }


    @Override
    public List<Product> getProductByCategoryId(int categoryId) {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products WHERE CategoryID = ?;";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getInt(3), rows.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public List<Product> getProductByPrice(double price) {
        this.products.clear();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products WHERE UnitPrice = ?;";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, price);
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                this.products.add(new Product(rows.getInt(1), rows.getString(2), rows.getInt(3), rows.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public void updateProduct(int id,Product product) {
        String sql = "UPDATE Products SET ProductName = ?, CategoryID = ?, UnitPrice = ? WHERE ProductID = " + id + ";";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }}


}
