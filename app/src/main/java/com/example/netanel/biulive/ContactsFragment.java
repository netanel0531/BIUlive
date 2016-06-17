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



public class ContactsFragment extends Fragment {

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        ListView lstFriend = (ListView) view.findViewById(R.id.lstContacts);

        /*set the list of the menu-title and image */
        List<Contact> menuItem = new ArrayList<Contact>() ;
        ContactsAdapter menuAdapter = new ContactsAdapter((FragmentActivity) getActivity(), menuItem);
        lstFriend.setAdapter(menuAdapter);
        return view;
    }

}
