package com.example.netanel.biulive;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class GradesFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grades, container, false);

        ListView lstFriend = (ListView) view.findViewById(R.id.lstCourse);

        /*set the list of the menu-title and image */
        List<Course> menuItem = new ArrayList<Course>() ;
  //      DataManager.retrieveCourseFinalGrade();
        /*
        menuItem.add(new Course("חשבון","33-55",100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                ft.add(R.id.gradeCompFragment , new GradeComponentsFragment());
                ft.addToBackStack("menu");
                ft.commit();
            }
        }));
*/
        for (final Course course: MainActivity.courses) {
            course.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    ft.add(R.id.gradeCompFragment,
                            GradeComponentsFragment.newInstance(course.getCourseNumber(),""));
                    ft.addToBackStack("menu");
                    ft.commit();
                    */
                    //startActivity(new Intent(getActivity(), MenuActivity.class));
                }
            });
        }

        menuItem.addAll(MainActivity.courses);
        CourseAdapter menuAdapter = new CourseAdapter((FragmentActivity) getActivity(), menuItem);
        lstFriend.setAdapter(menuAdapter);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
