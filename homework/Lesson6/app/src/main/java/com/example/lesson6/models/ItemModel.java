package com.example.lesson6.models;

public class ItemModel {
    private int position;
    private int id;
    private String image;
    private String title;
    private String description;
    private String author;

    public ItemModel(int id, String image, String title, String description, String author) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toString() {
        return "Story{" +
                "id=" + id +
                ", author='" + author + '\'' +
                '}';
    }
}
