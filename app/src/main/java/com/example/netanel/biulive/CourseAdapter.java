package com.example.netanel.biulive;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

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
        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.course_item);

        final Course item = items.get(position);
        //set view with the current item

        txtGrade.setText(item.getCourseGrade());
        txtTitle.setText(item.getCourseName());
        txtNumber.setText(item.getCourseNumber());

/*
        item.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = new GradeComponentsFragment().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.gradeCompFragment,
                        GradeComponentsFragment.newInstance(item.getCourseNumber(), ""));
                ft.addToBackStack("menu");
                ft.commit();
            }
        });
*/
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                FragmentManager fm = (new GradesFragment()).getChildFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.gradeCompFragment,
                        GradeComponentsFragment.newInstance(item.getCourseNumber(), ""));
                ft.addToBackStack("menu");
                //ft.commit();
                */

             //   activity.startActivity(new Intent(activity, MenuActivity.class));
                /*
                Intent intent = new Intent(new Intent(activity, GradeComponentsActivity.class));
                Bundle b = new Bundle();
                b.putString("courseNumber", item.getCourseNumber()); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                activity.startActivity(intent);
                activity.finish();
                */
                GradeComponentsFragment dFragment = new GradeComponentsFragment();
                // Show DialogFragment
                dFragment.show(activity.getSupportFragmentManager(), "Dialog Fragment");
            }
        });
        return convertView;
    }

}
