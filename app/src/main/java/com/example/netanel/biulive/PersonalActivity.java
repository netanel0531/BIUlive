package com.example.netanel.biulive;

import android.os.Bundle;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.EncodingUtils;

public class PersonalActivity extends BaseActivity {
    Integer i = 0;
    TextView charge;
    TextView credit;
    TextView balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        charge = (TextView) findViewById(R.id.charge);
        credit = (TextView) findViewById(R.id.credit);
        balance = (TextView) findViewById(R.id.balance);

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
                balance.append(res.substring(balanceIndex, res.indexOf(CELL_END, balanceIndex)));


                int chargeIndex = res.indexOf("סה\"כנומינלי");
                chargeIndex = res.indexOf(CELL_START, chargeIndex) + CELL_START.length();
                charge.append(res.substring(chargeIndex, res.indexOf(CELL_END, chargeIndex)));


                int creditsIndex = res.indexOf("סה\"כנומינלי", chargeIndex);
                creditsIndex = res.indexOf(CELL_START, creditsIndex) + CELL_START.length();
                credit.append(res.substring(creditsIndex, res.indexOf(CELL_END, creditsIndex)));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}
