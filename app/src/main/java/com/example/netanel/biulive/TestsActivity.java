package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

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
        int j = DatesManager.yearForInstituteAndTimestamp(10, -1L);
        int i = j;
        if (j == -1) {
            i = 2015;
        }

        i = i - 2013 + 73;

        HashMap localHashMap = new HashMap();
        localHashMap.put("year", Integer.toString(i));

        String dataUrl =  "https://dory.os.biu.ac.il/StudentSystem/ExamDates.jsp";
        String dataUrlParameters = "year="+ Integer.toString(i);
        URL url;
        HttpsURLConnection connection = null;
        User returnedUser = null;
        String responseStr = "null!";
        try {
// Create connection
            url = new URL(dataUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length","" + Integer.toString(dataUrlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "UTF-8");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
// Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(dataUrlParameters);
            wr.flush();
            wr.close();
// Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            responseStr = response.toString();
            if (responseStr.contains("course-time-content-info-box")) {

                returnedUser = new User(UserLocalStore.getLoggedInUser());
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

    }

}
