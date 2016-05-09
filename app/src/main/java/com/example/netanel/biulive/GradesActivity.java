package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.mime.content.StringBody;

public class GradesActivity extends AppCompatActivity {

    TextView textView;

  //  ListView lstCourse;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.avgText);
        //lstCourse = (ListView) findViewById(R.id.listView);
        Toast.makeText(GradesActivity. this, Integer.toString(MainActivity.courses.size())  ,Toast.LENGTH_LONG).show();
        getAverage();

        for (Course course: MainActivity.courses) {
            getCourseFinalGrades(course);
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.gradesFragment, new GradesFragment());
        ft.commit();
    }
    private void getAverage() {
        ServerRequests.get("memutsaBeynaim.jsp",null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                Document doc = Jsoup.parse(res);
                Elements elms = doc.getElementsByTag("span");

                textView.append(elms.get(1).text());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                textView.append("not working");
            }
        });
    }
    /*
    get the newest final grade of a course,
    adding the grade to the course.
     */
    private void getCourseFinalGrades(final Course course) {
        String couresNum = course.getCourseNumber();
        final int[] gradeInt = new int[1];
        HashMap<String, String> paramMap = new HashMap<>();
        String[] ids = couresNum.split("-");
        paramMap.put("CourseId1", ids[0]);
        paramMap.put("CourseId2", ids[1]);
        paramMap.put("CourseGrup", ids[2]);
        RequestParams params = new RequestParams(paramMap);
        ServerRequests.post("FinalGradeInfo.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                res = res.replaceAll("\\s","");
                /*
                if (res.contains("<pdir=rtlalign=right>")) {
                    return;
                }
                */
                Pattern pattern = Pattern.compile("\\d\\d\\d\\d\\/\\d\\d\\/\\d\\d");
                Matcher matcher = pattern.matcher(res);
                List<String> listMatches = new ArrayList<String>();
                while (matcher.find()) {
                    listMatches.add(matcher.group());
                }

                int listSize = listMatches.size();
                if (listSize < 1) {
                    return;
                }
                int lastGradeIndex = res.indexOf(listMatches.get(listSize-1));
                int len = "<tdalign=center>".length();
                int len3 = "xxxx/xx/xx".length();
                int i = 0;
                int index = lastGradeIndex+len+len3;
                String grade = "";
                while (res.charAt(index+i) != '<') {
                    grade += res.substring(index + i, index + i + 1);
                    i++;
                }
                if (grade.length() > 1) {
                    course.setCourseGrade(Integer.parseInt(grade));
                }
                //textView.append(grade+"\n");

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
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
                String res = new String(responseBody);
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
