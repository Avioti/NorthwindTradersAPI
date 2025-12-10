package com.pluralsight.NorthwindTradersAPI.model;

public class Category {
    int categoryID;
    String categoryName;

    public Category(int categoryID,String categoryName) {
        this.categoryName = categoryName;
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
