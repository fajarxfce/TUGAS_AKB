package com.a10120014.nurulfajar.model;

/*
Nama    : Nurul Fajar
NIM     : 10120014
Kelas   : IF-1
Matkul  : Aplikasi Komputer Bergerak
*/


public class Data {
    private int Id;
    private String Title, Category, Description, UpdatedAt, CreatedAt;

    public Data(int id, String title, String category, String description, String updatedAt, String createdAt) {

        Id = id;
        Title = title;
        Category = category;
        Description = description;
        UpdatedAt = updatedAt;
        CreatedAt = createdAt;
    }

    public Data(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }
}
