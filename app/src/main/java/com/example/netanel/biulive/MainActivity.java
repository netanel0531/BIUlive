package com.example.netanel.biulive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.Collection;


public class MainActivity extends ActionBarActivity {
    public static ArrayList<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);

    }

}
