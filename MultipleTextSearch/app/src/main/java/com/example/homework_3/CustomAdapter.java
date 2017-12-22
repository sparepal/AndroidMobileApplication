package com.example.homework_3;


/*
Created by
        Sai Vikhyat Parepalli
        Geeta Priyanka janapareddy*/


import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    ArrayList<String> words = new ArrayList<String>();


    public CustomAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void add(String word) {
        words.add(word);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int position) {


        return words.get(position);
    }

    public ArrayList<String> allWords() {

        return words;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)

            convertView = View.inflate(context, R.layout.textview, null);


        EditText text = (EditText) convertView.findViewById(R.id.editTextSingleEt);
        text.setKeyListener(null);
        FloatingActionButton button = (FloatingActionButton) convertView.findViewById(R.id.floatadd);
        button.setOnClickListener(this);
        button.setTag(position);
        button.setImageResource(R.drawable.remove);
        text.setText(words.get(position));
        return convertView;
    }

    public void remove(int pos) {
        words.remove(pos);

    }

    @Override
    public void onClick(View v) {
        // words.remove(getItemId());
        // words.remove(v.getTag());
        //words.clear();
        //    notify();


        //notifyDataSetChanged();
        //  Toast.makeText(context,v.getTag()+"",Toast.LENGTH_SHORT).show();

        ((MainActivity) context).method12((Integer) v.getTag());


    }
}




