package com.example.inclass08;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by smank on 10/30/2017.
 */

public class RecipeAdd_adapter extends RecyclerView.Adapter<RecipeAdd_adapter.ViewHolder> {

    public static ArrayList<Ingredient> mData;
    public static Context mContext;
    public static int pos;


    //0
    public RecipeAdd_adapter(ArrayList<Ingredient> mData, Context context) {
        this.mData = mData;
        mContext = context;


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //2
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //3
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Ingredient ingredient = mData.get(position);


        if (ingredient.getImage() == null||ingredient.getImage().trim().length()==0)
            holder.image.setImageResource(R.drawable.remove);
        else

            Picasso.with(mContext)
                    .load(ingredient.getImage())
                    .into(holder.image);


        holder.textViewURL.setText(ingredient.getUrl());
        holder.textViewTitle.setText(ingredient.getTitle());
        holder.textViewIngrdients.setText(ingredient.getIngredients());
        holder.ingredient = ingredient;
    }

    //1
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewURL, textViewTitle, textViewIngrdients;
        ImageView image;

        Ingredient ingredient;

        public ViewHolder(final View itemView) {
            super(itemView);
            textViewURL = (TextView) itemView.findViewById(R.id.textViewURL);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewIngrdients = (TextView) itemView.findViewById(R.id.ingredients);
            image = (ImageView) itemView.findViewById(R.id.imageView);

            pos = getAdapterPosition();


        }


    }


}