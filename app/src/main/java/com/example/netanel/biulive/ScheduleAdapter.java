package com.example.netanel.biulive;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 20/04/2016.
 */
public class ScheduleAdapter extends BaseAdapter{
    private FragmentActivity activity;
    private LayoutInflater inflater;
    private List<Course> items;

    public ScheduleAdapter(FragmentActivity activity, List<Course> items) {
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item_schedule, null);

        //   ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView name = (TextView) convertView.findViewById(R.id.courseName);
        TextView number = (TextView) convertView.findViewById(R.id.courseNumber);
        TextView day = (TextView) convertView.findViewById(R.id.courseDay);
        TextView hours = (TextView) convertView.findViewById(R.id.courseHours);


        Course item = items.get(position);
        //set view with the current item

        day.setText(item.getCourseDay());
        name.setText(item.getCourseName());
        number.setText(item.getCourseNumber());
        hours.setText(item.getCourseStartHour().concat("-").concat(item.getCourseEndHour()));


        return convertView;
    }

}
