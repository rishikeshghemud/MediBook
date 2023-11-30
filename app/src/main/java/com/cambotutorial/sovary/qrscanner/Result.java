package com.cambotutorial.sovary.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Result extends AppCompatActivity {

    TextView tvResName, tvResSpecies, tvResInfo;
    ImageView imageView;
    String image_link, name, species, info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        String id = i.getStringExtra("ID");
        name = i.getStringExtra("Name");
        species = i.getStringExtra("Species");
        info = i.getStringExtra("Info");
        image_link = i.getStringExtra("Image_link");

        tvResName = findViewById(R.id.tvName);
        tvResSpecies = findViewById(R.id.tvSpecies);
        tvResInfo = findViewById(R.id.tvInfo);
        imageView = findViewById(R.id.imageView);
        
        tvResName.setText(name);
        tvResSpecies.setText(species);
        tvResInfo.setText(info);


        Glide.with(this)
                .load(image_link)
                .into(imageView);
    }


}