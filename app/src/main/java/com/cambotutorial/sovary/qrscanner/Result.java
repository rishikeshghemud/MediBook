package com.cambotutorial.sovary.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView tvResID, tvResName, tvResSpecies, tvResInfo, tvResImage;
    EditText etBinaryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        String id = i.getStringExtra("ID");
        String name = i.getStringExtra("Name");
        String species = i.getStringExtra("Species");
        String info = i.getStringExtra("Info");
        String image_link = i.getStringExtra("Image_link");

        tvResName = findViewById(R.id.tvName);
        tvResSpecies = findViewById(R.id.tvSpecies);
        tvResInfo = findViewById(R.id.tvInfo);
        tvResImage = findViewById(R.id.tvImageLink);
        
        tvResName.setText(name);
        tvResSpecies.setText(species);
        tvResInfo.setText(info);
        tvResImage.setText(image_link);
    }
}