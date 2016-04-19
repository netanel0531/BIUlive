package com.example.netanel.biulive;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 17/04/2016.
 */
public class CourseAdapter extends BaseAdapter {
    private FragmentActivity activity;
    private LayoutInflater inflater;
    private List<Course> items;

    public CourseAdapter(FragmentActivity activity, List<Course> items) {
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
            convertView = inflater.inflate(R.layout.list_item_course, null);

     //   ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtGrade = (TextView) convertView.findViewById(R.id.grade);
        TextView txtNumber = (TextView) convertView.findViewById(R.id.courseNum);
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.course_item);

        Course item = items.get(position);
        //set view with the current item

        txtGrade.setText(item.getCourseGrade());
        txtTitle.setText(item.getCourseName());
        txtNumber.setText(item.getCourseNumber());
        layout.setOnClickListener(item.getListener());

        return convertView;
    }

}
