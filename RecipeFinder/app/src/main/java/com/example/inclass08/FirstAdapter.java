package com.example.inclass08;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by smank on 10/30/2017.
 */

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> words = new ArrayList<String>();


    public FirstAdapter(Context context, ArrayList<String> words) {
        this.context = context;
        this.words = words;
        notifyDataSetChanged();
    }

    public void add(String word) {
        words.add(word);
        notifyDataSetChanged();

    }


    public ArrayList<String> allWords() {

        return words;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    int x;

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String w = words.get(position);
        holder.textView.setText(w);


        holder.textView.setEnabled(false);

        holder.imageView.setImageResource(R.drawable.remove);
        holder.imageView.setTag(R.drawable.remove);


        holder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                  words.remove(position);

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return words.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.editTextSingleEt);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewAdd);


        }
    }
}