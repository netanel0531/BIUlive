package com.example.netanel.biulive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public static ArrayList<Course> courses = new ArrayList<>();
    public static UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLocalStore = new UserLocalStore(this);
/*
        if (userLocalStore.getLoggedInUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {

            courses.addAll(userLocalStore.getUserData());
            startActivity(new Intent(MainActivity.this, MenuActivity.class));
        }
*/
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

}
