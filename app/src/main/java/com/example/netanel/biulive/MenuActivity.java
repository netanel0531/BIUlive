package com.example.netanel.biulive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //1.1 Map Button
        final Button map_button = (Button) findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MenuActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        //1.2 Personal-Info Button
        final Button personal_button = (Button) findViewById(R.id.personal_button);
        personal_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MenuActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });

        //1.3 Grades Button
        final Button grades_button = (Button) findViewById(R.id.grades_button);
        grades_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MenuActivity.this, GradesActivity.class);
                startActivity(intent);
            }
        });

        //2.1 Tests Button
        final Button tests_button = (Button) findViewById(R.id.tests_button);
        tests_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MenuActivity.this, TestsActivity.class);
                startActivity(intent);
            }
        });

        //2.2 Schedule Button
        final Button schedule_button = (Button) findViewById(R.id.schedule_button);
        schedule_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MenuActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //2.3 Library Button
        final Button library_button = (Button) findViewById(R.id.library_button);
        library_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MenuActivity.this, LibraryActivity.class);
                startActivity(intent);
            }
        });

        //3.1 Contacts Button
        final Button contacts_button = (Button) findViewById(R.id.contacts_button);
        contacts_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MenuActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        //3.2 Messages Button
        final Button messages_button = (Button) findViewById(R.id.messages_button);
        messages_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MenuActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        });

        //3.3 Facebook Button
        final Button facebook_button = (Button) findViewById(R.id.facebook_button);
        facebook_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // or we open the browser with the facebook adress
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
                startActivity(browserIntent);
            }
        });
    }

}
