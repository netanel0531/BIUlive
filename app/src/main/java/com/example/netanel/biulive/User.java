package com.example.netanel.biulive;

public class User {

    String name, username, password,response;
    int age;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(User u) {

        this.username = u.username;
        this.password = u.password;
    }
    public User(User u,String res) {

        this.username = u.username;
        this.password = u.password;
        this.response = res;
    }
    public void setResponse(String r) {
        this.response = r;
    }
}