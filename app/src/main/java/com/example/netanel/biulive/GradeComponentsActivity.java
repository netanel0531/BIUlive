package com.example.netanel.biulive;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GradeComponentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grade_components);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle b = getIntent().getExtras();
        String courseNum = b.getString("courseNumber");
        ft.add(R.id.gradeCompFragment_toActivity, GradeComponentsFragment.newInstance(courseNum,""));
        ft.addToBackStack("menu");
        ft.commit();
    }
}
