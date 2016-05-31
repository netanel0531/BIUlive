package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.EncodingUtils;

public class TestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    public void retrieveExams() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("year", Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - 1940));
        RequestParams params = new RequestParams(paramMap);
        ServerRequests.post("FinalGradeInfo.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = EncodingUtils.getString(responseBody, "iso-8859-8");
                res = res.replaceAll("\\s","");

                String courseNum;
                String courseName;
                String moedADate;
                String moedATime;
                String moedBDate;
                String moedBTime;
                String semester = "";

                String tableStartSign = "height=\"2\"";
                int tableStart = 0;
                while (res.indexOf(tableStartSign, tableStart+1) > -1) {
                    tableStart = res.indexOf(tableStartSign, tableStart+1);
                }
                String rawStart = "<tr>";
                String cellStart = "<tdalign=\"center\">";
                String cellEnd = "</td>";
                String tableEnd = "<strong>";


                int tableEndIndex = res.indexOf(tableEnd, tableStart + 1);
                int nextRaw = res.indexOf(rawStart, tableStart + 1);
                int nextCell = res.indexOf(cellStart, nextRaw);

                int curInfoStart = 0;
                int curInfoEnd = 0;
                System.out.println(nextRaw);

                while (tableEndIndex > nextRaw) {
                    System.out.println();

                    //first cell
                    curInfoStart = res.indexOf(cellStart, nextRaw) + cellStart.length();
                    //check if first cell as a semester info
                    if (res.charAt(curInfoStart) != '&') {
                        semester = "" + res.charAt(curInfoStart);
                    }
                    System.out.println("Semester: " + semester);

                    //course number cell
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, nextCell) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, nextCell);
                    courseNum = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("course num: " + res.substring(curInfoStart, curInfoEnd));

                    //course name cell
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, nextCell) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, nextCell);
                    courseName = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("course name: " + res.substring(curInfoStart, curInfoEnd));

                    //course lecturer
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, nextCell) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, nextCell);
                    System.out.println("lacturer: " + res.substring(curInfoStart, curInfoEnd));

                    //first test date
                    /*
                     * date of course. may contain the string:<fontcolor="D18700"> if the date passed.
                     * so TODO add a check for this addition.
                     */
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, nextCell) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, nextCell);
                    moedADate = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("moed A date: " + res.substring(curInfoStart, curInfoEnd));

                    //first test time
                    //TODO as above
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, curInfoEnd) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, curInfoStart);
                    moedATime = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("moed A hour: " + res.substring(curInfoStart, curInfoEnd));


                    //second test date
                    //TODO as above
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, curInfoEnd) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, curInfoStart);
                    moedBDate = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("moed B date: " + res.substring(curInfoStart, curInfoEnd));

                    //second test time
                    //TODO as above
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, curInfoEnd) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, curInfoStart);
                    moedBTime = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("moed B hour: " + res.substring(curInfoStart, curInfoEnd));


                    if (res.indexOf(cellStart, curInfoStart) > -1) {
                        nextRaw = res.indexOf(rawStart, nextCell);
                        nextRaw = res.indexOf(rawStart, nextRaw);
                    } else {
                        nextRaw = tableEndIndex + 1;
                    }
                    for (Course course: MainActivity.courses) {
                        if (course.getCourseNumber() == courseNum) {
                            course.setCourseName(courseName);
                            course.setMoedA(moedADate, moedATime, "א");
                            course.setMoedA(moedBDate, moedBTime, "ב");
                            break;
                        }
                    }
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
