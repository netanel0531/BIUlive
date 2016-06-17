/******************************
 * 8921104 308212612 Raaya Eyov
 *****************************/
package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


public class BaseActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBar();
    }

    private void setActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        try {
            mActionBar.hide();
        } catch (NullPointerException e) {

        }

    }
}
