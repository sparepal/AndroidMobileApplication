package com.example.group26_inclass10;

import android.view.View;/*InClass 03
    *//*    Inclass03
        Sai Vikhyat Parepalli
        Geeta Priyanka Janapareddy
*/



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ContactActivity extends AppCompatActivity {
    DatabaseReference databaseReference;

    ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        Button submit = (Button) findViewById(R.id.button);
        final EditText e1 = (EditText) findViewById(R.id.editText4);
        final EditText e2 = (EditText) findViewById(R.id.editText5);
        final EditText editTextPhone=(EditText)findViewById(R.id.editTextPhone);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        i = (ImageView) findViewById(R.id.imageView9);

        final String[] name = new String[1];
        final String[] email = new String[1];
        ImageView avatar;
        final String department = "";

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactActivity.this,AvatarActivity.class);
                startActivityForResult(intent,9999);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (e1.getText().toString().trim().length()>0)
                    name[0] = e1.getText().toString();
                else
                    Toast.makeText(getApplicationContext(), "Enter a Name", Toast.LENGTH_LONG).show();

                if (e2.getText().toString().trim().length()>0)

                    email[0] = e2.getText().toString();

                else
                    Toast.makeText(getApplicationContext(), "Enter a valid Email", Toast.LENGTH_LONG).show();

                if(editTextPhone.getText().toString().trim().length()==0)
                    Toast.makeText(ContactActivity.this, "Enter phone", Toast.LENGTH_SHORT).show();

                else{

                    RadioButton radioButton=(RadioButton)findViewById(rg.getCheckedRadioButtonId());
                Contact contact=new Contact();
                contact.setEmail(email[0]);
                contact.setName(name[0]);
                contact.setPhone(editTextPhone.getText().toString().trim());
                contact.setDepartment(radioButton.getText().toString());
                contact.setiId(x);



                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String ky = databaseReference.child("contacts").child(id).push().getKey();
                    databaseReference.child("contacts").child(id).child(ky).setValue(contact);

                    Intent intent=new Intent(ContactActivity.this,ContactListActivity.class);
                    startActivity(intent);


                }

            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 9999) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                x=(Integer)data.getExtras().get("asd");
                i.setImageResource(Integer.parseInt(String.valueOf(x),16));
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
        }
    }
    int x;
}


