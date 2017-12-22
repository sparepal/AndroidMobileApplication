package com.example.homework_3;

/*
Created by
        Sai Vikhyat Parepalli
        Geeta Priyanka janapareddy*/

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter implements View.OnClickListener {

    Context context;
    ArrayList<File> lines;
    ArrayList<String> input;
    private static LayoutInflater inflater = null;

    public CustomAdapter2(DisplayLines Activity, ArrayList<File> lines, ArrayList<String> input) {

        context = Activity;
        this.lines = lines;
        this.input = input;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.line, null);
        EditText dotstart = (EditText) rowView.findViewById(R.id.editTextstartdots);
        EditText dotend = (EditText) rowView.findViewById(R.id.editTextenddots);
        dotstart.setText("...");
        dotend.setText("...");


        holder.before = (EditText) rowView.findViewById(R.id.editTextbefore);
        holder.after = (EditText) rowView.findViewById(R.id.editTextafter);
        holder.key = (EditText) rowView.findViewById(R.id.editTextkey);
        holder.before.setText(lines.get(position).getLinebefore());
        holder.after.setText(lines.get(position).getLineafter());
        holder.key.setText(lines.get(position).getKey());
        holder.after.setFocusable(false);
        holder.before.setFocusable(false);
        holder.key.setFocusable(false);
        holder.key.setTag(position);
        holder.key.setOnClickListener(this);


        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equalsIgnoreCase(lines.get(position).getKey())) {

                if ((i + 3) % 3 == 0) {

                    holder.key.setTextColor(Color.BLUE);


                } else if ((i + 2) % 3 == 0) {
                    holder.key.setTextColor(Color.RED);

                } else if ((i + 1) % 3 == 0) {
                    holder.key.setTextColor(Color.GREEN);

                }

                break;


            }

        }


        return rowView;
    }

    @Override
    public int getCount() {
        return lines.size();
    }

    @Override
    public File getItem(int position) {
        return lines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Pressed" + ((EditText) v).getTag(), Toast.LENGTH_SHORT).show();


    }

    public class Holder {
        EditText before;
        EditText after;
        EditText key;
    }


}
