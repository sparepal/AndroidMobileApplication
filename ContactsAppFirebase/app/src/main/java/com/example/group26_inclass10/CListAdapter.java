package com.example.group26_inclass10;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class CListAdapter extends ArrayAdapter<Contact> {
    ArrayList<Contact> contacts;
    Context mContext;
    int mResource;
    ImageView imageView;
    SharedPreferences sharedPreferences;

    public CListAdapter(@NonNull Context context, @LayoutRes int resource, List<Contact> objects) {
        super(context, resource, objects);
        contacts = (ArrayList<Contact>) objects;
        this.mContext = context;
        mResource = resource;

    }
    @Override
    public int getCount() {
        return contacts.size();
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull final ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }
        TextView textViewName=(TextView)convertView.findViewById(R.id.textViewName);
        TextView textViewPhone=(TextView)convertView.findViewById(R.id.textViewPhone);
        TextView textViewDept=(TextView)convertView.findViewById(R.id.textViewDept);
        TextView textViewEmail=(TextView)convertView.findViewById(R.id.textViewEmail);
        imageView=convertView.findViewById(R.id.imageViewAvatar);

        textViewName.setText(contacts.get(position).getName());
        textViewPhone.setText(contacts.get(position).getPhone());
        textViewDept.setText(contacts.get(position).getDepartment());
        textViewEmail.setText(contacts.get(position).getEmail());
        imageView.setImageResource(contacts.get(position).getiId());

        return convertView;
    }


}
