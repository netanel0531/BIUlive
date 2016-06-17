package com.example.netanel.biulive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestsListFragment extends Fragment {


    public TestsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tests_list, container, false);
        ListView lstTests = (ListView) view.findViewById(R.id.lstTests);
        Map<Test,Course> testsMap = new HashMap<>();
        List<Course> menuItem = new ArrayList<Course>() ;
        List<Test> tests = new ArrayList<Test>();

        Course c1 = new Course("חשבון","33-55","13/06/12", "16:00","א");
        c1.setMoedB("02/03/12", "18:00","ב" );
        menuItem.add(c1);

        Course c2 = new Course("אנגלית","33-56","21/03/12", "17:30","א") ;
        c2.setMoedB("01/03/12", "17:00","ב" );
        menuItem.add(c2) ;

        for (Course course:MainActivity.courses) {
        //for (Course course:menuItem) {
            if (null != course.getMoedA()) {
                testsMap.put(course.getMoedA(), course);
                testsMap.put(course.getMoedB(), course);
                tests.add(course.getMoedA());
                tests.add(course.getMoedB());
            }
        }
        menuItem.addAll(MainActivity.courses);
        TestsAdapter menuAdapter = new TestsAdapter((FragmentActivity) getActivity(), testsMap, tests);
        lstTests.setAdapter(menuAdapter);

        return view;


    }


}
