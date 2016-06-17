package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DayFragment extends Fragment {
    String day;
    public DayFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        ListView lstFriend = (ListView) view.findViewById(R.id.lstDay);
        ArrayList<Course> coursesOnDay = new ArrayList<>();

        /*set the list of the menu-title and image */
        List<Course> menuItem = new ArrayList<Course>() ;
   //     getSched();
       // menuItem.addAll(MainActivity.courses);
        Bundle b = getArguments();
        day = b.getString("day");
   //     day = EncodingUtils.getString(day.getBytes(),"iso-8859-8");
        for ( Course course : MainActivity.courses) {
            if (course.getCourseDay().equals(day)) {
                coursesOnDay.add(course);
            }
        }
        menuItem.addAll(coursesOnDay);
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter((FragmentActivity) getActivity(), menuItem);
        lstFriend.setAdapter(scheduleAdapter);

        return view;
    }

    private void getSched() {

        //     textView.append("\n\n\n\n");
    }

}
