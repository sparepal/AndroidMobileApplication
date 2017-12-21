package com.example.vikhy.homework02;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by vikhy on 9/15/2017.
 */

public class DisplayContacts extends AppCompatActivity {

    ArrayList<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_contacts_list);
        contacts=new ArrayList<Contact>();

        if(getIntent().getExtras()!=null){


            contacts=(ArrayList<Contact>)getIntent().getExtras().get("contactList");




        final ListView listView=(ListView)findViewById(R.id.dynamicListView);
        Adapter adapter=new CustomAdapter(this,contacts);
        listView.setAdapter((ListAdapter) adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Contact contact=(Contact)listView.getItemAtPosition(position);
                    switch(ContactsHome.ACTION) {

                        case "DISPLAY":
                            Intent intent = new Intent(DisplayContacts.this, ContactDetails.class);
                            intent.putExtra("displayContact", contact);
                            startActivity(intent);

                        break;
                        case "DELETE":
                            AlertDialog.Builder builder = new AlertDialog.Builder(DisplayContacts.this);
                            builder.setTitle("Delete")
                                    .setMessage("Are you sure?")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent=new Intent(DisplayContacts.this, ContactsHome.class);
                                            intent.putExtra("contact", contact);
                                            setResult(RESULT_OK,intent);
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            final AlertDialog alert = builder.create();
                            alert.show();
                            break;


                    }
                }
            });



        }

    }
}