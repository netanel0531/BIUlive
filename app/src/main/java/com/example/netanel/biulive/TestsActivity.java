package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.CompoundButton;
import android.widget.Switch;

public class TestsActivity extends BaseActivity {
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        switch1 = (Switch) findViewById(R.id.switch1);

        //set the switch to ON
        switch1.setChecked(true);
        //attach a listener to check for changes in state
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                if(isChecked){
                    ft.add(R.id.testFragment, new TestsListFragment());
                    ft.commit();
                }else{
                    ft.add(R.id.testFragment, new GradesFragment());
                    ft.commit();
                }

            }
        });
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //check the current state before we display the screen
        if(switch1.isChecked()){
            ft.add(R.id.testFragment, new TestsListFragment());
            ft.commit();
        }
        else {
            ft.add(R.id.testFragment, new GradesFragment());
            ft.commit();
        }
    }

}
