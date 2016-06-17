package com.example.netanel.biulive;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    Button bLogin;
    TextView registerLink;
    EditText etUsername, etPassword;
    ProgressBar pb;
    TextView t;

    Integer i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = (Button) findViewById(R.id.login_button);
        etUsername = (EditText) findViewById(R.id.userId);
        etPassword = (EditText) findViewById(R.id.password);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        t = (TextView) findViewById(R.id.loginResp);

        bLogin.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
      /*  switch (view.getId()) {
            case R.id.login_button:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                ServerRequests.setAut(username,password);

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
   //     startActivity(new Intent(LoginActivity.this, MenuActivity.class));

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
                if (res.contains("FinalGradeInput.jsp")) {
                    DataManager.loadAll();
                    final ProgressDialog progress;
                    progress=new ProgressDialog(LoginActivity.this);
                    progress.setMessage("מתחבר ...");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(true);

                    progress.setProgress(0);
                    progress.show();
                    Thread tLoader = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (i <= 100) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //set text to be - 5 4 3 2 1
                                            progress.setProgress(i);
                                        }
                                    });
                                    Thread.sleep(200); //sleep
                                    i++;
                                }
                                //move to the map activity
                                logUserIn(new User("305120164","8452"));
                            }
                            catch (Exception ex)
                            {

                            }
                        }
                    });
                    tLoader.start();
                }
                else
                    Toast.makeText(LoginActivity.this,res.concat("HI"),Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (responseBody == null) {
                    Toast.makeText(LoginActivity.this, String.valueOf(statusCode).concat("ERROR"), Toast.LENGTH_LONG).show();
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

        MainActivity.userLocalStore.storeUserAuth(returnedUser);
        MainActivity.userLocalStore.setUserLoggedIn(true);
        MainActivity.userLocalStore.storeUserData(MainActivity.courses);
        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
    }
}
