package com.example.vikhy.homework02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        ArrayList<Contact> arrayOfContacts = new ArrayList<Contact>();
// Create the adapter to convert the array to views
        CustomAdapter adapter = new CustomAdapter( this, arrayOfContacts);
// Attach the adapter to a ListView
        final ListView listView = (ListView) findViewById(R.id.dynamicListView);
        listView.setAdapter(adapter);

    }

}
