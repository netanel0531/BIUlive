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
    private List<Test> tests = new ArrayList<>(2);
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
        this.tests.set(0, null);
        this.tests.set(1, null);
    }

    public View.OnClickListener getListener() {
        return listener;
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
        return courseName;
    }

    public String getCourseGrade() {
        return Integer.toString(this.courseGrade);
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

    public void setCourseGrade(int courseGrade) {
        this.courseGrade = courseGrade;
    }

    public void setCourseSemester(String semester) {
        this.courseSemester = semester;
    }
    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseBuilding() {
        return this.courseBuilding;
    }

    public String getCourseClass() {
        return this.courseClass;
    }

    public String getCourseStartHour() {
        return this.courseStartHour;
    }

    public String getCourseEndHour() {
        return this.courseEndHour;
    }

    public String getCourseDay() {
        return this.courseDay;
    }

    public String getCourseSemester() {
        return this.courseSemester;
    }

}
