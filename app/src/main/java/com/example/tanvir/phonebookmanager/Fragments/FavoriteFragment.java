package com.example.tanvir.phonebookmanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tanvir.phonebookmanager.R;

public class FavoriteFragment extends Fragment {
    ListView listView;

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
        return inflater.inflate(R.layout.fragment_favorite, container, false);

        /*Button button = view.findViewById(R.id.buttonId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), AddInformationsActivity.class);
                startActivity(intent);
            }
        });*/
        //return view;
    }
}
