package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Priyabrat on 21-08-2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle b = new Bundle();
        if (position == 5)
        {
            fragment = new DayFragment();
            b.putString("day","א");
            fragment.setArguments(b);
        }
        else if (position == 4)
        {
            fragment = new DayFragment();
            b.putString("day","ב");
            fragment.setArguments(b);
        }
        else if (position == 3)
        {
            fragment = new DayFragment();
            b.putString("day","ג");
            fragment.setArguments(b);
        }
        else if (position == 2)
        {
            fragment = new DayFragment();
            b.putString("day","ד");
            fragment.setArguments(b);
        }
        else if (position == 1)
        {
            fragment = new DayFragment();
            b.putString("day","ה");
            fragment.setArguments(b);
        }
        else if (position == 0)
        {
            fragment = new DayFragment();
            b.putString("day","ו");
            fragment.setArguments(b);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "ו";
        }
        else if (position == 1)
        {
            title = "ה";
        }
        else if (position == 2)
        {
            title = "ד";
        }
        else if (position == 3)
        {
            title = "ג";
        }
        else if (position == 4)
        {
            title = "ב";
        }
        else if (position == 5)
        {
            title = "א";
        }
        return title;
    }
}