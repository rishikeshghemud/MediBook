package com.cambotutorial.sovary.qrscanner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity
{
    Button btn_scan;
    DatabaseHelper db;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_scan =findViewById(R.id.btn_scan);

        btn_scan.setOnClickListener(v->
        {
            scanCode();
        });

        db = new DatabaseHelper(this);

        /*try{
            db.checkDB();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            db.openDatabase();
        } catch (Exception e){
            e.printStackTrace();
        }*/

        /*btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlantInfo p1;

                try {
//                    p1 = new PlantInfo(etInputName.getText().toString());

                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_LONG).show();
//                    p1 = new PlantInfo("Error");
                }

//                db = new DatabaseHelper(MainActivity.this);
//                boolean res = db.addOne(p1);

//                Toast.makeText(MainActivity.this, "Status: "+res, Toast.LENGTH_LONG).show();

            }
        });*/

    }

    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null)
        {
            String id = result.getContents();

//            int plantId = Integer.parseInt(id);
            try {
                PlantInfo plant = db.fetchOne(id);
                if(plant.name != null) {
                    Intent i = new Intent(getApplicationContext(), Result.class);
                    i.putExtra("ID", id);
                    i.putExtra("Name", plant.name);
                    i.putExtra("Species", plant.species);
                    i.putExtra("Info", plant.info);
                    i.putExtra("Image_link", plant.image_link);
                    startActivity(i);
                } else {
                    builder = new AlertDialog.Builder(this);
                    builder.setTitle("Sorry no plant registered for QR")
                            .setNegativeButton("OK", (dialogInterface, i) -> {
                               dialogInterface.cancel();
                            }).show();
                }

            } catch (Exception e){
                e.printStackTrace();
            }

        }
    });



}