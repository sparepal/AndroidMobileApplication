package com.example.vikhy.homework02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import static android.R.attr.data;

public class ContactsHome extends AppCompatActivity {

    ArrayList<Contact> contactArrayList = new ArrayList<>();

public static String ACTION="";
    Contact c;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (data.getExtras() != null) {
            if (requestCode == 200&&resultCode==400)
                c = (Contact) data.getExtras().get("contactClass");
            contactArrayList.add(c);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_home);


        findViewById(R.id.buttonCreateNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsHome.this, CreateNewContact.class);
                startActivityForResult(intent, 200);
            }
        });
        findViewById(R.id.buttonDisplayContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACTION="DISPLAY";
                Intent intent = new Intent(ContactsHome.this, DisplayContacts.class);
                intent.putExtra("contactList", contactArrayList);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonDeleteContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACTION="DELETE";
                Intent intent = new Intent(ContactsHome.this, DisplayContacts.class);
                intent.putExtra("contactList", contactArrayList);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonEditContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACTION="EDIT";
                Intent intent = new Intent(ContactsHome.this, DisplayContacts.class);
                intent.putExtra("contactList", contactArrayList);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}
