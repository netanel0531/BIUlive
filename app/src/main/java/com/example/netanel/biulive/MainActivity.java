package com.example.netanel.biulive;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.1 Map Button
        final Button map_button = (Button) findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(this, MapActivity.class);
                startActivity(intent);
            }
        });

        //1.2 Personal-Info Button
        final Button personal_button = (Button) findViewById(R.id.personal_button);
        personal_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
            }
        });

        //1.3 Grades Button
        final Button grades_button = (Button) findViewById(R.id.grades_button);
        grades_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(this, GradesActivity.class);
                startActivity(intent);
            }
        });

        //2.1 Tests Button
        final Button tests_button = (Button) findViewById(R.id.grades_button);
        tests_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(this, TestsActivity.class);
                startActivity(intent);
            }
        });

        //2.2 Schedule Button
        final Button schedule_button = (Button) findViewById(R.id.schedule_button);
        schedule_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //2.3 Library Button
        final Button library_button = (Button) findViewById(R.id.library_button);
        library_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(this, LibraryActivity.class);
                startActivity(intent);
            }
        });

        //3.1 Contacts Button
        final Button contacts_button = (Button) findViewById(R.id.contacts_button);
        contacts_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        //3.2 Messages Button
        final Button messages_button = (Button) findViewById(R.id.messages_button);
        messages_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(this, MessagesActivity.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
