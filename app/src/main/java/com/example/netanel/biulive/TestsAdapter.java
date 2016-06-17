package com.example.netanel.biulive;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 16/05/2016.
 */
public class TestsAdapter extends BaseAdapter{
    private FragmentActivity activity;
    private LayoutInflater inflater;
    private Map<Test,Course> items;
    private List<Test> tests = new ArrayList<Test>() ;

    public TestsAdapter(FragmentActivity activity,Map<Test,Course> items,List<Test> tests) {
        this.activity = activity;
        this.items = items;
        this.tests = tests;
    }
    @Override
    public int getCount() {
        return tests.size();
    }

    @Override
    public Object getItem(int location) {
        return tests.get(location);
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
            convertView = inflater.inflate(R.layout.list_item_tests, null);

        //   ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView examCourseName = (TextView) convertView.findViewById(R.id.examCourseName);
        TextView examCourseNumber = (TextView) convertView.findViewById(R.id.examCourseNumber);
        TextView examMoed = (TextView) convertView.findViewById(R.id.examMoed);
        TextView examDay = (TextView) convertView.findViewById(R.id.examDay);
        TextView examHour = (TextView) convertView.findViewById(R.id.examHour);

        Test test = tests.get(position);

        Course course = items.get(test);

        //set view with the current item

        examCourseName.setText(course.getCourseName());
        examCourseNumber.setText(course.getCourseNumber());
        examMoed.setText(test.getMoed());
        examDay.setText(test.getDay());
        examHour.setText(test.getHour());

        return convertView;
    }

}
