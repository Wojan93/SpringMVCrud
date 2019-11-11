package com.example.java.moto.model;

import javax.persistence.Entity;

@Entity
public class Category extends BaseEntity  {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
