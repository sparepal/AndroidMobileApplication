package com.example.group26_inclass10;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ArrayList<Contact> contacts;
    DatabaseReference databaseReference;
    DatabaseReference childref;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        listView=findViewById(R.id.listView);


        contacts=new ArrayList<>();
        final CListAdapter cListAdapter=new CListAdapter(ContactListActivity.this,R.layout.listitem,contacts);


        listView.setAdapter(cListAdapter);

        FirebaseUser currentUser = mAuth.getCurrentUser();


        String id = currentUser.getUid();

        childref = databaseReference.child("contacts").child(id);


        findViewById(R.id.buttonLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(ContactListActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        childref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    Log.d("DEMO",ds.getValue().toString());

                    Contact contact = ds.getValue(Contact.class);
                    contacts.add(contact);
                    cListAdapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        findViewById(R.id.buttonCreateNewContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactListActivity.this,ContactActivity.class);
                startActivity(intent);
            }
        });









    }


}
