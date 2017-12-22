package com.example.inclass08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FirstFragment.onFragmentTextChange,SecondFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.container,new FirstFragment(),"first").commit();

    }

    @Override
    public void goToMainActivity() {
        getFragmentManager().beginTransaction().replace(R.id.container, new FirstFragment(), "first").addToBackStack(null).commit();

    }

    @Override
    public void onTextChanged(ArrayList<Ingredient> value) {


    }
}
