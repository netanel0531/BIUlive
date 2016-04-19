package com.example.netanel.biulive;

import android.view.View;

import java.io.Serializable;


public class Course implements Serializable {
    private String courseName = null;
    private String courseNumber = null;
    private int courseGrade = -1;
    private String courseBuilding = null;
    private String courseClass = null;
    private String courseStartHour = null;
    private String courseEndHour = null;
    private String courseDay = null;
    private String courseSemester = null;
    View.OnClickListener listener;

    public Course(String courseName, String courseNumber,int courseGrade, View.OnClickListener listener){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.courseGrade = courseGrade;
        this.listener = listener;
    }

    public Course(String courseNumber){
        this.courseNumber = courseNumber;
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
    public void setCourseBuilding(String building) {
        this.courseBuilding = building;
    }
    public void setCourseClass(String classroom) {
        this.courseClass = classroom;
    }
    public void setCourseStartHour(String startHour) {
        this.courseStartHour = startHour;
    }
    public void setCourseEndHour(String endHour) {
        this.courseEndHour = endHour;
    }

    public void setCourseSemester(String semester) {
        this.courseSemester = semester;
    }
    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
    }
    public String getCourseBuilding() {
        return courseBuilding;
    }

    public String getCourseClass() {
        return courseClass;
    }

    public String getCourseStartHour() {
        return courseStartHour;
    }

    public String getCourseEndHour() {
        return courseEndHour;
    }

    public String getCourseDay() {
        return courseDay;
    }

    public String getCourseSemester() {
        return courseSemester;
    }
}
