package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.EncodingUtils;

public class GradesActivity extends BaseActivity {

    TextView textView;
    Toolbar toolbar;
  //  ListView lstCourse;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
  //      setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.avgText);
        //lstCourse = (ListView) findViewById(R.id.listView);
    //     getAverage();
        textView.append(DataManager.average);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.gradesFragment, new GradesFragment());

        ft.commit();

    }

    /*
    get the newest final grade of a course,
    adding the grade to the course.
     */
    private void getCourseFinalGrades(final Course course) {

    }
    /*
    get the grade components of a course,
    (adding the grade to the course?)
     */
    private void getGradeComponents(final Course course) {
        String couresNum = course.getCourseNumber();
        HashMap<String, String> paramMap = new HashMap<>();
        String[] ids = couresNum.split(couresNum);
        paramMap.put("CourseId1", ids[0]);
        paramMap.put("CourseId2", ids[1]);
        paramMap.put("CourseGrup", ids[2]);
        RequestParams params = new RequestParams(paramMap);
        ServerRequests.get("ziunBchina.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = EncodingUtils.getString(responseBody,"iso-8859-8");
                res = res.replaceAll("\\s","");
                int head = res.indexOf("<thalign=\"right\"width=");
                int precTd = res.indexOf("<td", head);
                String[] tasks = res.split("<thvalign=bottomalign=centercolspan=8>");
                int i;
                String text;
                for (i = 2; i < tasks.length-1; i++) {
                    text = tasks[i];
                    //get Matala
                    int firstTd = text.indexOf("<td");
                    while (text.charAt(firstTd)!='>') {
                        firstTd++;
                    }
                    firstTd++;
                    String matala = "";
                    while (text.charAt(firstTd) != '<') {
                        matala += text.substring(firstTd, firstTd + 1);
                        firstTd++;
                    }
                    System.out.println("MATALA: " + matala);

                    //the percentage info
                    precTd = text.indexOf("<tddir", firstTd);
                    precTd += "<tddir=\"ltr\"align=\"right\">".length();
                    String prec = "";
                    while (text.charAt(precTd) != '<') {
                        prec += text.substring(precTd, precTd+1);
                        precTd++;
                    }
                    System.out.println("Prec: " + prec);
                    //the Moed info
                    firstTd = text.indexOf("<td>", precTd);
                    firstTd += "<td>".length();
                    String moed = "";
                    while (text.charAt(firstTd) != '<') {
                        moed += text.substring(firstTd, firstTd+1);
                        firstTd++;
                    }
                    System.out.println("Moed: " + moed);

                    //the Grade info
                    precTd = text.indexOf("<TDalign", firstTd);
                    precTd += "<TDalign=\"right\"dir=\"ltr\">".length();
                    String grade = "";
                    while (text.charAt(precTd) != '<') {
                        grade += text.substring(precTd, precTd+1);
                        precTd++;
                    }
                    System.out.println("Grade: " + grade);

                    //the Date info
                    precTd = text.indexOf("<TDalign", precTd);
                    precTd += "<TDalign=\"right\"dir=\"ltr\">".length();
                    String data = "";
                    while (text.charAt(precTd) != '<') {
                        data += text.substring(precTd, precTd+1);
                        precTd++;
                    }
                    System.out.println("Date: " + data);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}

