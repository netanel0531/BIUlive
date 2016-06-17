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
 * Created by dell on 13/06/2016.
 */
public class GradeComponentsAdapter extends BaseAdapter{
    private FragmentActivity activity;
    private LayoutInflater inflater;
    private List<GradeComponent> items;

    public GradeComponentsAdapter(FragmentActivity activity, List<GradeComponent> items) {
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
            convertView = inflater.inflate(R.layout.list_item_grade_comp, null);

        //   ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtType = (TextView) convertView.findViewById(R.id.compType);
        TextView txtGrade = (TextView) convertView.findViewById(R.id.compGrade);
        TextView txtMoed = (TextView) convertView.findViewById(R.id.compMoed);
        TextView txtPrecents = (TextView) convertView.findViewById(R.id.compPrecent);
        TextView txtDate = (TextView) convertView.findViewById(R.id.compDate);


        GradeComponent item = items.get(position);
        //set view with the current item

        txtType.setText(item.getType());
        txtGrade.setText(item.getGrade());
        txtMoed.setText(item.getMoed());
        txtPrecents.setText(item.getPrecents());
        txtDate.setText(item.getDate());


        return convertView;
    }

}
