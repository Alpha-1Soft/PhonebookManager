package com.example.tanvir.phonebookmanager.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvir.phonebookmanager.R;
import com.example.tanvir.phonebookmanager.models.ContactsInfo;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<ContactsInfo> implements Filterable{
    ArrayList<ContactsInfo> orginalList,tempList;
    Context context;
    customFilter cf;

    public ContactsAdapter(@NonNull Context context, ArrayList<ContactsInfo> orginalList) {
        super(context, R.layout.listview_shape,orginalList);
        this.context=context;
        this.orginalList=orginalList;
        this.tempList=orginalList;
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

        holder.name.setText(orginalList.get(position).getContactName());
        holder.image.setImageResource(orginalList.get(position).getRatingImage());

        return  v;
    }

    @Override
    public int getCount() {
       return orginalList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if(cf==null){
            cf = new customFilter();
        }
        return cf;
    }

    class viewHolder{
        TextView name;
        ImageView image;

        public viewHolder(View view) {
            name = (TextView)view.findViewById(R.id.textviewIdOnLv);
            image = view.findViewById(R.id.rating_imag);
        }
    }
    //custom filter for contacts fragment
    class customFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if(charSequence!=null && charSequence.length()>0) {
                charSequence = charSequence.toString().toUpperCase();

                ArrayList<ContactsInfo> filters = new ArrayList<>();

                for (int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i).getContactName().toUpperCase().contains(charSequence)) {
                        ContactsInfo contactsInfo = new ContactsInfo(tempList.get(i).getContactName(), tempList.get(i).getRatingImage());
                        filters.add(contactsInfo);
                    }
                }
                filterResults.count = filters.size();
                filterResults.values = filters;
            }
            else{
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            orginalList = (ArrayList<ContactsInfo>)filterResults.values;
            notifyDataSetChanged();
        }
    }
}

