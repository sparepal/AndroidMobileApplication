package com.example.inclass08;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstFragment extends Fragment implements GetRecipeAsyncTask.IData {

    private onFragmentTextChange mListener;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> ingredients = new ArrayList<>();
    ArrayList<String> arrayList;

    EditText ingredient;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.first_recyclerView);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        arrayList = new ArrayList<>();

        mAdapter = new FirstAdapter(getActivity(), arrayList);


        mRecyclerView.setAdapter(mAdapter);
        final EditText dish = (EditText) getActivity().findViewById(R.id.editTextDish);

        ingredient = (EditText) getView().findViewById(R.id.editTextSingleEt2);
        getActivity().findViewById(R.id.imageViewAdd2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.size()<5)
                {
                if (ingredient.getText().toString().trim().length()>0) {
                    arrayList.add(ingredient.getText().toString());
                    ingredient.setText("");
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "Enter input", Toast.LENGTH_SHORT).show();
                    ingredient.setText("");
                }
                }
                else
                    Toast.makeText(getActivity(), "Max ingredients", Toast.LENGTH_SHORT).show();
            }
        });


        getActivity().findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dish.getText().toString().trim().length()>0&&arrayList!=null)
                {
                StringBuilder sb = new StringBuilder();
            ingredients=arrayList;
                // sb.append(" http://www.recipepuppy.com/api/? i=");// http://www.recipepuppy.com/api/?
                sb.append("http://www.recipepuppy.com/api/?i=");
                // i=<INGREDIENTS LIST, COMMA SEPARATED>&q=<DISH NAME>.
                for (int i = 0; i < ingredients.size(); i++)
                    sb.append(ingredients.get(i) + ",");
                sb.append("&q=");
                sb.append(dish.getText().toString());
                new GetRecipeAsyncTask(FirstFragment.this).execute(sb.toString());
                }
                else
                    Toast.makeText(getActivity(), "Enter input", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onFragmentTextChange) {
            mListener = (onFragmentTextChange) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupData(ArrayList<Ingredient> result) {
        if (result != null) {

            SecondFragment sc = new SecondFragment();
            sc.onTextChanged(result);
           // mListener.onTextChanged(result);


            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, sc, "Second")
                    .addToBackStack(null)
                    .commit();
        }
        else
        {
            Toast.makeText(getActivity(), "No results", Toast.LENGTH_SHORT).show();
            /*Intent intent=new Intent(getActivity(),MainActivity.class);
            startActivity(intent);*/
        }



    }


    public interface onFragmentTextChange {
        void onTextChanged(ArrayList<Ingredient> value);

    }


}