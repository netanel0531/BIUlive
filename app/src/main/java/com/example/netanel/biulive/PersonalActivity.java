package com.example.netanel.biulive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.EncodingUtils;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView view = new TextView(this);


        getPersonalInfo();
    }

    public void getPersonalInfo() {
        ServerRequests.get("mazavCheshbonGet.jsp", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = EncodingUtils.getString(responseBody, "iso-8859-8");
                res = res.replaceAll("\\s","");

                final String CELL_START = "<td>";
                final String CELL_END = "</td>";

                int balanceIndex = res.indexOf("יתרתחובהמשוערכת");
                balanceIndex = res.indexOf(CELL_START, balanceIndex) + CELL_START.length();
                String balance = res.substring(balanceIndex, res.indexOf(CELL_END, balanceIndex));
                System.out.println("balance: " + balance);

                int chargeIndex = res.indexOf("סה\"כנומינלי");
                chargeIndex = res.indexOf(CELL_START, chargeIndex) + CELL_START.length();
                String charge = res.substring(chargeIndex, res.indexOf(CELL_END, chargeIndex));
                System.out.println("charge: " + charge);

                int creditsIndex = res.indexOf("סה\"כנומינלי", chargeIndex);
                creditsIndex = res.indexOf(CELL_START, creditsIndex) + CELL_START.length();
                String credits = res.substring(creditsIndex, res.indexOf(CELL_END, creditsIndex));
                System.out.println("credits: " + credits);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
