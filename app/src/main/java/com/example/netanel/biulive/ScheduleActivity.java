package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class ScheduleActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        textView = (TextView) findViewById(R.id.schText);
        textView.append("\n\n\n\n");

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
                }
                ArrayList<String> c = new ArrayList<String>();
                for (Course course: MainActivity.courses) {
                    c.add(course.getCourseNumber());
                }
                for (String s : listMatches) {
//                    String sub = s.split("-")[0] + s.split("-")[1];
                    if (!c.contains(s)) {
                        c.add(s);
                        MainActivity.courses.add(new Course(s));
                    }
                }

                for (final Course course : MainActivity.courses) {

                    final String[] parts = course.getCourseNumber().split("-");

                    RequestParams courseTimeParams = new RequestParams();

                    String url = "CourseInfo.jsp?year=%FA%F9%F2%22%E5";
                    url += "&CourseId1=" + parts[0];
                    url += "&CourseId2=" + parts[1];
                    url += "&CourseGrup=" + parts[2];
                    url += "&sum=";
                    ServerRequests.post(url, /*courseTimeParams*/null, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess ( int statusCode, Header[] headers,byte[] responseBody){
                        String res = new String(responseBody);

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
        }

        @Override
        public void onFailure ( int statusCode, Header[] headers,byte[] responseBody, Throwable
        error){
            textView.append("not working");
            }
        });


    }

}