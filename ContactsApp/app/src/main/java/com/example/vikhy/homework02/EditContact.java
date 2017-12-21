package com.example.vikhy.homework02;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditContact extends AppCompatActivity {
    public static String ACT="";
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);

        if(getIntent().getExtras()!=null) {

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            EditText first = (EditText) findViewById(R.id.editTextFirst);
            EditText last = (EditText) findViewById(R.id.editTextLast);
            EditText email = (EditText) findViewById(R.id.editTextEmail);
            EditText phone= (EditText) findViewById(R.id.editTextPhone);
            EditText company = (EditText) findViewById(R.id.editTextCompany);
            EditText address = (EditText) findViewById(R.id.editText15);
            EditText bday = (EditText) findViewById(R.id.editTextBirthday);
            EditText URL = (EditText) findViewById(R.id.editTextURL);
            EditText nickname = (EditText) findViewById(R.id.editTextNickname);
            EditText fbURL = (EditText) findViewById(R.id.editTextFacebookProfileURL);
            EditText twitter = (EditText) findViewById(R.id.editTextTwitterProfileURL);
            EditText skype = (EditText) findViewById(R.id.textViewSkype);
            EditText youtube = (EditText) findViewById(R.id.editTextYoutubeChannel);

            Contact contact = getIntent().getExtras().getParcelable("displayContact");

            Bitmap bitmap = contact.photo;
            imageView.setImageBitmap(bitmap);
            first.setText(contact.first);
            last.setText(contact.last);
            phone.setText(contact.phone);
            email.setText(contact.email);
            company.setText(contact.company);
            address.setText(contact.address);
            bday.setText(contact.birthday);
            URL.setText(contact.URL);
            nickname.setText(contact.nickname);
            fbURL.setText(contact.facebook_url);
            twitter.setText(contact.twitter_url);
            skype.setText(contact.skype);
            youtube.setText(contact.youtube);
        }


        ImageView img = (ImageView) findViewById(R.id.imageView);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        final Calendar myCalendar = Calendar.getInstance();

        final EditText edittext = (EditText) findViewById(R.id.editTextBirthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                String birthday = sdf.format(myCalendar.getTime());
                int year = Integer.parseInt(birthday.substring(birthday.length() - 4));
                if (year >= 1850) {
                    edittext.setText(sdf.format(myCalendar.getTime()));
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid selection", Toast.LENGTH_LONG).show();
                }
            }
        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditContact.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACT= "EDIT";
                EditText first = (EditText) findViewById(R.id.editTextFirst);
                EditText last = (EditText) findViewById(R.id.editTextLast);
                EditText phone = (EditText) findViewById(R.id.editTextPhone);
                EditText company = (EditText) findViewById(R.id.editTextCompany);
                EditText email = (EditText) findViewById(R.id.editTextEmail);
                EditText address = (EditText) findViewById(R.id.editText15);
                EditText URL = (EditText) findViewById(R.id.editTextURL);
                EditText fb_url = (EditText) findViewById(R.id.editTextFacebookProfileURL);
                EditText twitter_url = (EditText) findViewById(R.id.editTextTwitterProfileURL);
                EditText bday = (EditText) findViewById(R.id.editTextBirthday);
                EditText nickname = (EditText) findViewById(R.id.editTextNickname);
                EditText skype = (EditText) findViewById(R.id.textViewSkype);
                EditText youtube = (EditText) findViewById(R.id.editTextYoutubeChannel);
                ImageView photo = (ImageView) findViewById(R.id.imageView);

                //photo.buildDrawingCache();
                //Bitmap bmap = photo.getDrawingCache();

                Bitmap bmap = ((BitmapDrawable) photo.getDrawable()).getBitmap();

                Date birthday = null;

                String firstname = first.getText().toString();
                String lastname = last.getText().toString();
                String phone_num = phone.getText().toString();
                String date = bday.getText().toString();
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


                    birthday = (Date) formatter.parse(date);
                } catch (Exception e) {
                    Log.d("demo", "Invalid format");
                }

                //birthday = new Date("12/29/2017");

                Date date_object = null;

                if (firstname.isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Please provide first name", Toast.LENGTH_LONG).show();

                } else if (lastname.isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "Please provide last name", Toast.LENGTH_LONG).show();

                } else if (phone_num.isEmpty() == true || !(android.util.Patterns.PHONE.matcher(phone_num).matches())) {
                    Toast.makeText(getApplicationContext(), "Please provide valid phone number", Toast.LENGTH_LONG).show();

                } else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())) {
                    Toast.makeText(getApplicationContext(), "Please provide valid email ID", Toast.LENGTH_LONG).show();
                } else {
                    Contact contact = new Contact(bmap, first.getText().toString(), last.getText().toString(), company.getText().toString(), email.getText().toString(), phone.getText().toString(), URL.getText().toString(), address.getText().toString(), nickname.getText().toString(), fb_url.getText().toString(), twitter_url.getText().toString(), skype.getText().toString(), youtube.getText().toString(), date);

                    Intent intent = new Intent();
                    intent.putExtra("contactClass", contact);
                    setResult(400, intent);
                    finish();
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView img=(ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(photo);
        }
    }

}
