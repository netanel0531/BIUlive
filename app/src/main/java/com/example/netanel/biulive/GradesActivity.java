package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class GradesActivity extends AppCompatActivity {

    TextView textView;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.avgText);
        ServerRequests.get("memutsaBeynaim.jsp",null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                int i = res.indexOf("<b>ממוצע לתואר: </b>");
                String s;
                textView.append(res,i,i+40);
                //textView.conc(res.substring(i,i+40));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                textView.append("not working");
            }
        });
    }
}
