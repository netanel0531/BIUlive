package com.example.netanel.biulive;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class GradeComponentsFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GradeComponentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GradeComponentsFragment newInstance(String param1, String param2) {
        GradeComponentsFragment fragment = new GradeComponentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Dialog dialog = getDialog();
        dialog.setTitle(R.string.gradeComp);
        dialog.setContentView(R.layout.fragment_grade_components);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grade_components, container, false);
        ListView lstGradeComp = (ListView) dialog.findViewById(R.id.lstGradeComp);
        List<GradeComponent> gradeComponentsItem = new ArrayList<GradeComponent>() ;
        for (final Course course: MainActivity.courses) {
            if (course.getCourseNumber().equals(mParam1)) {
                gradeComponentsItem.addAll(course.getGradeComponents());
            }
        }

        GradeComponentsAdapter gradeComponentsAdapter = new GradeComponentsAdapter((FragmentActivity) getActivity(), gradeComponentsItem);
        lstGradeComp.setAdapter(gradeComponentsAdapter);


        dialog.show();
/*
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Select Color Mode");

        ArrayAdapter<GradeComponent> modeAdapter = new ArrayAdapter<GradeComponent>(this.getContext(), R.layout.fragment_grade_components, R.id.lstGradeComp, gradeComponentsItem);
        lstGradeComp.setAdapter(modeAdapter);

        builder.setView(lstGradeComp);
        Dialog dialog = builder.create();

        dialog.show();
        */


        return view;

    }

}

