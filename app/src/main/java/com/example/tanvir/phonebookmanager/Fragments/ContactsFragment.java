package com.example.tanvir.phonebookmanager.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.tanvir.phonebookmanager.Activity.AddDataActivity;
import com.example.tanvir.phonebookmanager.Activity.UserInformationActivity;
import com.example.tanvir.phonebookmanager.Database.DatabaseManager;
import com.example.tanvir.phonebookmanager.R;
import com.example.tanvir.phonebookmanager.models.ContactsInfo;

import java.util.ArrayList;
import java.util.Collections;

public class ContactsFragment extends Fragment {
    ListView listView;
    SearchView searchView;
    TextView emptyTv;
    DatabaseManager databaseManager;
    int contactId=0;
    public interface OnFragmentInteractionListener {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contacts, container, false);
        databaseManager=new DatabaseManager(getActivity());

        emptyTv = view.findViewById(R.id.emptyTextView);
        listView = view.findViewById(R.id.listView1);

        searchView = view.findViewById(R.id.searchView);
        searchView.setFocusable(false);//autofocus off on searchview

        final ArrayList<ContactsInfo> contactsInfos = databaseManager.getAllContacts();
        ArrayList<String> displayList = new ArrayList<>();

        for(ContactsInfo contactsInfo:contactsInfos){
            displayList.add(contactsInfo.getContactName());
        }

        listView.setEmptyView(emptyTv);//this function will be call when list is empty
        //adapter for connecting listview
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.listview_shape,R.id.textviewIdOnLv,displayList);
        listView.setAdapter(adapter);

        //search view thing
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {//when user search on searchview this method will be called
                adapter.getFilter().filter(s);//here, the search text will be filtered
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contactId = contactsInfos.get(i).getId();
                ContactsInfo contactsInfo = databaseManager.getContactsById(contactId);

                String id= String.valueOf(contactId);
                //passing user data via intent
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",contactsInfo.getContactName());
                intent.putExtra("phoneNum",contactsInfo.getContactNumber());
                intent.putExtra("email",contactsInfo.getContactEmail());
                intent.putExtra("description",contactsInfo.getContactDescription());
                startActivity(intent);
            }
        });
        //floating action bar function
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FD12F6")));//fab background color
        //fab.setBackgroundDrawable(R.drawable.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddDataActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
