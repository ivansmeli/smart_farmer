package com.example.user.smartfarmer;

public class Model {
    private int id;
    private String category;
    private String breed;
    private String age;
    private String price;
    private byte [] image;
    public Model(){

    }

    public Model(int id, String category, String breed, String age, String price, byte[] image) {
        this.id = id;
        this.category = category;
        this.breed = breed;
        this.age = age;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
