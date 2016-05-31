package com.example.netanel.biulive;

import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Course implements Serializable {
    final String MOED_A = "א";
    final String MOED_B = "ב";
    private String courseName = null;
    private String courseNumber = null;
    private int courseGrade = -1;
    private String courseBuilding = null;
    private String courseClass = null;
    private String courseStartHour = null;
    private String courseEndHour = null;
    private String courseDay = null;
    private String courseSemester = null;
    private List<Test> tests = new ArrayList<>();

    View.OnClickListener listener;

    public Course(String courseName, String courseNumber,int courseGrade, View.OnClickListener listener){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.courseGrade = courseGrade;
        this.listener = listener;
    }
    public Course(String courseName, String courseNumber,String testDay,String testHour,String moed)  {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.tests.add( new Test(testDay, testHour, moed));

    }

    public Course(String courseNumber){
        this.courseNumber = courseNumber;
    }

    public Course(String courseName, String courseNumber,String courseDay,String courseStartHour, View.OnClickListener listener){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.courseDay = courseDay;
        this.courseStartHour = courseStartHour;
        this.listener = listener;
    }
    public View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };
    }
    public void setMoedA(String testDay,String testHour,String moed) {
        this.tests.set(0, new Test(testDay, testHour, moed));
    }
    public void setMoedB(String testDay,String testHour,String moed) {
        this.tests.set(1, new Test(testDay, testHour, moed));
    }
    public Test getMoedA() {
        return this.tests.get(0);
    }

    public Test getMoedB() {
        return this.tests.get(1);
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getCourseName() {
        return "אלגברה לינארית";
    }

    public String getCourseGrade() {
        return Integer.toString(courseGrade);
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
    public void setCourseDay(String courseDay) { this.courseDay = courseDay;   }
    public void setCourseGrade(int courseGrade) {
        this.courseGrade = courseGrade;
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
