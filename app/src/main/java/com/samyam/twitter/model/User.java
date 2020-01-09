package com.samyam.twitter.model;

public class User {
    String email;
    String password;
    String name;
    String image;

    public User(String email) {
        this.email = email;
    }

    public User(String email, String password, String name, String image) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.image = image;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
