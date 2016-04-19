package com.example.netanel.biulive;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class ScheduleActivity extends AppCompatActivity {
    TextView textView;
    TextView textViewSem;
    TextView textViewDay;
    TextView textViewStart;
    TextView textViewEnd;
    TextView textViewBuild;
    TextView textViewClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        textView = (TextView) findViewById(R.id.schText);
        textView.append("\n\n\n\n");
        /*
        textViewSem = (TextView) findViewById(R.id.schText);
        textViewSem.append("\n");
        textViewDay = (TextView) findViewById(R.id.schText);
        textViewDay.append("\n");
        textViewStart = (TextView) findViewById(R.id.schText);
        textViewStart.append("\n");
        textViewEnd = (TextView) findViewById(R.id.schText);
        textViewEnd.append("\n");
        textViewBuild = (TextView) findViewById(R.id.schText);
        textViewBuild.append("\n");
        textViewClass = (TextView) findViewById(R.id.schText);
        textViewClass.append("\n");
        */
        final RequestParams params = new RequestParams();
        params.put("yearValue", "%FA%F9%F2%22%E5");
        params.put("yearSelected", "40");
        ServerRequests.get("HourScheduale.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);

                Pattern pattern = Pattern.compile("\\d\\d\\-\\d\\d\\d\\-\\d\\d");
                Matcher matcher = pattern.matcher(res);
                List<String> listMatches = new ArrayList<String>();
                String current = "";
                while (matcher.find()) {
                    current = matcher.group();


                    listMatches.add(current);
                    /*
                    if (!listMatches.contains(current)) {

                    }*/
                }

                for (String s : listMatches) {
                    MainActivity.courses.add(new Course(s));
//                    textView.append(s + "\n");
                }
//                textView.append(Integer.toString(MainActivity.courses.size()) + "\n");

                for (final Course course : MainActivity.courses) {

                    final String[] parts = course.getCourseNumber().split("-");
                    /*
                    HashMap<String, String> paramMap = new HashMap<>();
                    paramMap.put("year", "%FA%F9%F2%22%E5");
                    paramMap.put("CourseId1", parts[0]);
                    paramMap.put("CourseId2", parts[1]);
                    paramMap.put("CourseGrup", parts[2]);
                    paramMap.put("sum", "");
                    */
                    RequestParams courseTimeParams = new RequestParams();

                    //textView.append(parts[0]+","+parts[1]+","+parts[2]+"\n");
//                    String a = String.format("%FA%F9%F2%22%E5", Uri.encode())
/*                    courseTimeParams.put("year", "%FA%F9%F2%22%E5");
                    courseTimeParams.put("CourseId1", parts[0]);
                    courseTimeParams.put("CourseId2", parts[1]);
                    courseTimeParams.put("CourseGrup", parts[2]);
                    courseTimeParams.put("sum", ""); */
                    String url = "CourseInfo.jsp?year=%FA%F9%F2%22%E5";
                    url += "&CourseId1=" + parts[0];
                    url += "&CourseId2=" + parts[1];
                    url += "&CourseGrup=" + parts[2];
                    url += "&sum=";
                    ServerRequests.post(url, /*courseTimeParams*/null, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess ( int statusCode, Header[] headers,byte[] responseBody){
                        String res = new String(responseBody);

                        /*
                        int semeterIndex = res.indexOf("<td align=center>") + 18;
                        int dayIndex = res.indexOf("<td align=center>", semeterIndex + 1) + 18;
                        int hoursIndex = res.indexOf("<td align=center>", dayIndex + 1) + 18;
                        int buildingIndex = res.indexOf("<td align=center>", hoursIndex + 1) + 18;
                        int builidngEndIndex = res.indexOf("</td>", buildingIndex);
                        int classroomIndex = res.indexOf("<td align=center>", buildingIndex + 1) + 18;
                        int classroomEndIndex = res.indexOf("</td>", classroomIndex);
                        */
                        String response = res.replaceAll("\\s","");
                        int len = "<tdalign=center>".length();
                        int semeterIndex = response.indexOf("<tdalign=center>")+len;
                        int dayIndex = response.indexOf("<tdalign=center>", semeterIndex+1)+len;
                        int hoursIndex = response.indexOf("<tdalign=center>", dayIndex+1)+len;
                        int buildingIndex = response.indexOf("<tdalign=center>", hoursIndex+1)+len;
                        int builidngEndIndex = response.indexOf("<", buildingIndex);
                        int classroomIndex = response.indexOf("<tdalign=center>", builidngEndIndex+1)+len;
                        int classroomEndIndex = response.indexOf("<", classroomIndex);
                        int len2 = "xx:xx-xx:xx".length();
                        String courseNum = parts[0]+"-"+parts[1]+"-"+parts[2];

                        course.setCourseBuilding(response.substring(buildingIndex, builidngEndIndex));
                        course.setCourseClass(response.substring(classroomIndex, classroomEndIndex));
                        course.setCourseDay(response.substring(dayIndex, dayIndex+1));
                        course.setCourseStartHour(response.substring(hoursIndex, hoursIndex + 5));
                        course.setCourseEndHour(response.substring(hoursIndex + 6, hoursIndex + 11));
                        course.setCourseSemester(response.substring(semeterIndex, semeterIndex+1) );

                        textView.append(course.getCourseNumber()+":\n");
                        textView.append("semester = " + course.getCourseSemester() + "\n");
                        textView.append("day = " + course.getCourseDay() + "\n");
                        textView.append("hours = " + course.getCourseStartHour() + " - "
                                + course.getCourseEndHour() + "\n");
                        textView.append("building = " + course.getCourseBuilding() + "\n");
                        textView.append("class = " + course.getCourseClass() + "\n");

                    }

                    @Override
                    public void onFailure ( int statusCode, Header[] headers,
                    byte[] responseBody, Throwable error){
                        textView.append("failed\n");
                    }
                });
            }

/*
                for (final Course course : MainActivity.courses) {
                    textViewSem.append(course.getCourseSemester());
                    textViewDay.append(course.getCourseDay());
                    textViewStart.append(course.getCourseStartHour());
                    textViewEnd.append(course.getCourseEndHour());
                    textViewBuild.append(course.getCourseBuilding());
                    textViewClass.append(course.getCourseClass());
                }
                */




                /*
                Document doc = Jsoup.parse(res);

                Elements tables = doc.getElementsByTag("table");
                Element semesterATable = tables.get(0);
                Elements semesterABody = semesterATable.getElementsByAttribute("tbody");

                Element semesterB = tables.get(1);
                */

        }

        @Override
        public void onFailure ( int statusCode, Header[] headers,byte[] responseBody, Throwable
        error){
            textView.append("not working");
            }
        });


    }

}
