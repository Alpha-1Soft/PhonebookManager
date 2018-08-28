package com.example.tanvir.phonebookmanager.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvir.phonebookmanager.R;
import com.example.tanvir.phonebookmanager.models.ContactsInfo;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<ContactsInfo> {
    ArrayList<ContactsInfo> arrayList;
    Context context;
    public ContactsAdapter(@NonNull Context context, ArrayList<ContactsInfo> arrayList) {
        super(context, R.layout.listview_shape,arrayList);
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder holder=null;
        View v = convertView;
        if(v==null){
            v= LayoutInflater.from(context).inflate(R.layout.listview_shape,parent,false);
            holder = new viewHolder(v);
            v.setTag(holder);
        }
        else {
            holder = (viewHolder) v.getTag();
        }

        holder.name.setText(arrayList.get(position).getContactName());
        holder.image.setImageResource(arrayList.get(position).getRatingImage());

        return  v;
    }
}
class viewHolder{
    TextView name;
    ImageView image;

    public viewHolder(View view) {
        name = (TextView)view.findViewById(R.id.textviewIdOnLv);
        image = view.findViewById(R.id.rating_imag);
    }
}
