package com.example.netanel.biulive;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    static SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserAuth(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("username", user.username);
        userLocalDatabaseEditor.putString("password", user.password);
        userLocalDatabaseEditor.commit();
    }
    public void storeUserData(ArrayList<Course> courses) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        Gson gson = new Gson();
        String json = gson.toJson(courses);
        userLocalDatabaseEditor.putString("MyObject", json);
        userLocalDatabaseEditor.commit();
    }
    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User user = new User(username, password);
        return user;
    }

    public ArrayList<Course> getUserData(){
        Gson gson = new Gson();
        String json = userLocalDatabase.getString("MyObject", "");
        Type type = new TypeToken<List<Course>>(){}.getType();
        return gson.fromJson(json,type);
    }
}