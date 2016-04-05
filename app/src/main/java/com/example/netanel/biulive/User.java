package com.example.netanel.biulive;

public class User {

    public String name, username, password;
    int age;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(User u) {

        this.username = u.username;
        this.password = u.password;
    }
}