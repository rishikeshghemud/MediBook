package com.cambotutorial.sovary.qrscanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context mContext;
    String dbPath, dbName;


    public DatabaseHelper(Context context) {
        super(context, "plants_info.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String stmt = "CREATE TABLE plants_data(plant_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, plant_name TEXT NOT NULL, plant_species TEXT NOT NULL, plant_info TEXT NOT NULL, plant_image_link TEXT NOT NULL)";
//        db.execSQL(stmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(PlantInfo p){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", p.name);

        long result = db.insert("PLANTS_INFO", null, cv);

        return result != -1;


    }

    public PlantInfo fetchOne(String id){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("plants_data", new String[]{"plant_name", "plant_species", "plant_info", "plant_image_link"}, "plant_id=?", new String[]{id}, null, null, null);


            PlantInfo plant = new PlantInfo();

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    plant.name = cursor.getString(0);
                    plant.species = cursor.getString(1);
                    plant.info = cursor.getString(2);
                    plant.image_link = cursor.getString(3);
                }
            } else {
                Log.d("cursor info", "Cursor Empty");
            }

            return plant;

    }

    public void checkDB(){
        SQLiteDatabase db = null;
        String filepath = dbPath;

        try{
            db = SQLiteDatabase.openDatabase(filepath, null, 0);

        }catch (Exception e){
            e.printStackTrace();
        }

        if(db != null){
            Toast.makeText(mContext, "DB already exists", Toast.LENGTH_SHORT);
        } else {
            copyDatabase();
        }


    }

    public void copyDatabase(){
        this.getReadableDatabase();
        try {
            InputStream ios = mContext.getAssets().open(dbName);
            OutputStream os = new FileOutputStream(dbPath+dbName);

            byte[] buffer = new byte[1024];
            int len;

            while((len=ios.read(buffer)) > 0){
                os.write(buffer, 0, len);

            }

            os.flush();
            ios.close();
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        Log.d("Copydb", "Database copied");
    }

    public void openDatabase(){
        String filePath = dbPath+dbName;
        SQLiteDatabase.openDatabase(filePath, null, 0);
    }
}
