package com.example.group26_inclass10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by smank on 11/13/2017.
 */

public class SignUpActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    //DatabaseReference mConditionRef = mRootRef.child("users");
    private FirebaseAuth mAuth;
    EditText firstname, lastname, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firstname = (EditText) findViewById(R.id.editTextFirstName);
        lastname = (EditText) findViewById(R.id.editTextLastName);
        email = (EditText) findViewById(R.id.editTextEmailSignUp);
        password = (EditText) findViewById(R.id.editTextPasswordSignUp);
        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //writeNewUser(firstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), password.getText().toString());
                addUser();
            }
        });
    }


    public void addUser() {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("tag", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(SignUpActivity.this, ContactListActivity.class);
                            startActivity(intent);

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(firstname.getText().toString() + "," + lastname.getText().toString())
                                    // .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                                    .build();


                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("demo", "User profile updated.");
                                                Intent intent = new Intent(SignUpActivity.this, ContactListActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });


                        } else
                            Log.d("demo", "not successful");
                    }
                });


    }


}
