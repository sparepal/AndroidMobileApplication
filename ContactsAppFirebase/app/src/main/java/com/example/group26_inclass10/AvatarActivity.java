package com.example.group26_inclass10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/*
InClass 03
        Inclass03
        Sai Vikhyat Parepalli
        Geeta Priyanka Janapareddy*/
public class AvatarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView i1=(ImageView)findViewById(R.id.imageView3);
        ImageView i2=(ImageView)findViewById(R.id.imageView4);
        ImageView i3=(ImageView)findViewById(R.id.imageView5);
        ImageView i4=(ImageView)findViewById(R.id.imageView6);
        ImageView i5=(ImageView)findViewById(R.id.imageView7);
        ImageView i6=(ImageView)findViewById(R.id.imageView8);
    }
    public void imageClicked(View view)
    {

        Intent intent=new Intent();
        intent.putExtra("asd",view.getId());
        finish();


    }

}
