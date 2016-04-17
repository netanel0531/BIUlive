package com.example.netanel.biulive;

import android.view.View;


public class Course {
    private String courseName;
    private String courseNumber;
    private int courseGrade;
    View.OnClickListener listener;


    public Course(String courseName, String courseNumber,int courseGrade, View.OnClickListener listener){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.courseGrade = courseGrade;
        this.listener = listener;
    }
    public View.OnClickListener getListener() {
        return listener;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getCourseName() {

        return courseName;
    }

    public String getCourseGrade() {

        return Integer.toString(courseGrade);
    }
}
