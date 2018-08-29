package com.example.tanvir.phonebookmanager.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanvir.phonebookmanager.Activity.MainActivity;
import com.example.tanvir.phonebookmanager.Activity.UserInformationActivity;
import com.example.tanvir.phonebookmanager.Adapters.ContactsAdapter;
import com.example.tanvir.phonebookmanager.Database.DatabaseManager;
import com.example.tanvir.phonebookmanager.R;
import com.example.tanvir.phonebookmanager.models.ContactsInfo;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    ListView favListView;
    DatabaseManager databaseManager;
    TextView favEmptyTv;
    SearchView favsearchView;
    int contactId = 0;
    ArrayList<ContactsInfo> favList = new ArrayList<>();
    ContactsAdapter contactsAdapter;

    public interface OnFragmentInteractionListener {

    }
    public FavoriteFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        databaseManager=new DatabaseManager(getActivity());


        favListView = view.findViewById(R.id.favListView);
        favEmptyTv = view.findViewById(R.id.favEmptyTextView);
        favsearchView = view.findViewById(R.id.favSearchView);

        final ArrayList<ContactsInfo> contactsInfos = databaseManager.getContactByFavorite();
        final ArrayList<ContactsInfo> contactsInfoss = databaseManager.getAllContacts();

        for (ContactsInfo contactsInfo:contactsInfos){
            favList.add(new ContactsInfo(contactsInfo.getId(),contactsInfo.getContactName(),R.drawable.blank));
        }
        favsearchView.setFocusable(false);
        favListView.setEmptyView(favEmptyTv);//this function will be call when list is empty
        contactsAdapter = new ContactsAdapter(getActivity(),favList);
        favListView.setAdapter(contactsAdapter);

        favsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {//when user search on searchview this method will be called
                contactsAdapter.getFilter().filter(s);//here, the search text will be filtered
                return false;
            }
        });


        favListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contactId = contactsInfos.get(i).getId();
                ContactsInfo contactsInfo = databaseManager.getContactsById(contactId);

                String id = String.valueOf(contactId);
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",contactsInfo.getContactName());
                intent.putExtra("phoneNum",contactsInfo.getContactNumber());
                intent.putExtra("email",contactsInfo.getContactEmail());
                intent.putExtra("description",contactsInfo.getContactDescription());
                startActivity(intent);
            }
        });

        return view;
    }
}
