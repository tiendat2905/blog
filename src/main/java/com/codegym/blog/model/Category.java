package com.codegym.blog.model;

public class Category {
    private int id;
    private String nameCategory;
    private String description;

    public Category() {
    }

    public Category(int id, String nameCategory, String description) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nameCategory='" + nameCategory + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
