package com.example.netanel.biulive;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {
    Button bLogin;
    TextView registerLink;
    EditText etUsername, etPassword;
    TextView t;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = (Button) findViewById(R.id.login_button);
        etUsername = (EditText) findViewById(R.id.userId);
        etPassword = (EditText) findViewById(R.id.password);
        t = (TextView) findViewById(R.id.loginResp);
        userLocalStore = new UserLocalStore(this);
        bLogin.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
      /*  switch (view.getId()) {
            case R.id.login_button:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                ServerRequests.setAut("30512016","8452");

                PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
// clear cookie to make the fresh cookie, to ensure the newest cookie is being send
                myCookieStore.clear();
// set the new cookie
                ServerRequests.client.setCookieStore(myCookieStore);
                authenticate();
                //User user = new User(username, password);

                //authenticate(user);

                break;
        }*/
        ServerRequests.setAut("30512016","8452");

        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
// clear cookie to make the fresh cookie, to ensure the newest cookie is being send
        myCookieStore.clear();
// set the new cookie
        ServerRequests.client.setCookieStore(myCookieStore);
        authenticate();
    }
    private void authenticate() {
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("Id", "30512016");
        paramMap.put("idType", "1");
        paramMap.put("pass", "8452");
        paramMap.put("Page", "1");
        paramMap.put("idNum", "30512016");
        paramMap.put("passportNum","");
        RequestParams params = new RequestParams(paramMap);
        ServerRequests.client.setEnableRedirects(true,true,true);
        ServerRequests.post("StudentLoginPerformance.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                if (res.contains("FinalGradeInput.jsp"))
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                else
                    Toast.makeText(LoginActivity.this,res.concat("HI"),Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (responseBody == null) {
                    Toast.makeText(LoginActivity.this, String.valueOf(statusCode).concat("ERROr"), Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(LoginActivity.this, String.valueOf(statusCode).concat(getRequestHeaders().toString()), Toast.LENGTH_LONG).show();
                }
//                Toast.makeText(LoginActivity.this, responseBody.toString().concat("   CODE=").concat(String.valueOf(statusCode)), Toast.LENGTH_SHORT).show();

                /*if (responseBody.toString().contains("FinalGradeInput.jsp"))
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                else
                    Toast.makeText(LoginActivity.this,responseBody.toString(),Toast.LENGTH_SHORT).show();
                    */
            }
        });

    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {

        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, MenuActivity.class));
    }
}
