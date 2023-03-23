package com.blog.Blog.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String category;
    private String color_code;


    public Category(Long id, String category, String color_code) {
        this.id = id;
        this.category = category;
        this.color_code = color_code;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return category;
    }

    public void setCategoryName(String category) {
        this.category = category;
    }

    public String getCategoryColor() {
        return color_code;
    }

    public void setCategoryColor(String color_code) {
        this.color_code = color_code;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }

}
