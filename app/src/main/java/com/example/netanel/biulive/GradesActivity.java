package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class GradesActivity extends AppCompatActivity {

    TextView textView;

  //  ListView lstCourse;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.avgText);
        //lstCourse = (ListView) findViewById(R.id.listView);
         getAverage();
      //  getCourseANDGrades();
     //   textView.append("\n\n\n\n");
       // textView.append("AS");
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

                /*
                for (Element elm : elms) {
                    textView.append(elm.text());
                }*/
                textView.append(elms.get(1).text());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                textView.append("not working");
            }
        });
    }

    private void getCourseANDGrades() {

        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("degree", "99");
        paramMap.put("tYear", "76");
        paramMap.put("fYear", "76");
        RequestParams params = new RequestParams(paramMap);
        ServerRequests.post("CourseANDGradesInfo.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                Document doc = Jsoup.parse(res);
                Elements elements = doc.select("tr");
                for (Element elem : elements) {
//                    elem.getElementsByTag()
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
