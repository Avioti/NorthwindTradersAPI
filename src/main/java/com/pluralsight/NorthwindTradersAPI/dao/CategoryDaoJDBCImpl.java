package com.pluralsight.NorthwindTradersAPI.dao;


import com.pluralsight.NorthwindTradersAPI.model.Category;
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
public class CategoryDaoJDBCImpl implements CategoryDao {
    private List<Category> categories;
    private DataSource dataSource;

    @Autowired
    public CategoryDaoJDBCImpl(DataSource dataSource) {
        this.categories = new ArrayList<>();
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAllCategories() {
        this.categories.clear();
        String sql = "SELECT CategoryID, CategoryName FROM categories;";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                this.categories.add(new Category(rows.getInt(1), rows.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.categories;
    }

    @Override
    public List<Category> getCategoryId(int categoryId) {
        this.categories.clear();
        String sql = "SELECT CategoryID, CategoryName FROM categories WHERE CategoryID = ?;";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet row = statement.executeQuery();
            if (row.next()) {
                this.categories.add(new Category(row.getInt(1), row.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.categories;
    }

    @Override
    public void insertCategory(Category category) {
        this.categories.clear();
        String sql = "INSERT INTO categories (CategoryName) VALUES (?);";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(int id,Category category) {
        this.categories.clear();
        String sql = "UPDATE categories SET CategoryName = ? WHERE CategoryID = " + id + ";";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteCategory(int id){
        String query = "DELETE FROM categories WHERE CategoryID = ?;";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
