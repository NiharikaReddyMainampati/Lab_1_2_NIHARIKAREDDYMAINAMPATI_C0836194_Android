package com.example.lab_1_2_niharikareddymainampati_c0836194_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "PRODUCTS.db";
    private static final String TABLE_PRODUCTS = "PRODUCTS";
    private static final String PRODUCT_NAME = "NAME";
    private static final String PRODUCT_DESCRIPTION = "DESCRIPTION";
    private static final String PRODUCT_PRICE = "PRICE";
    private static final String PRODUCT_LATITUDE = "LATITUDE";
    private static final String PRODUCT_LONGITUDE = "LONGITUDE";
    private static final String PRODUCT_ID = "ID";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " +
                    TABLE_PRODUCTS + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PRODUCT_NAME + " TEXT," + PRODUCT_DESCRIPTION + " TEXT," + PRODUCT_PRICE +
                    " INTEGER," + PRODUCT_LATITUDE + " INTEGER, " + PRODUCT_LONGITUDE + " INTEGER)");}






    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_PRODUCTS);
        onCreate(db);


    }

    public Boolean insertProductData(Integer ID, String NAME, String DESCRIPTION, Integer PRICE, Integer LATITUDE, Integer LONGITUDE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_ID, ID);
        values.put(PRODUCT_NAME, NAME);
        values.put(PRODUCT_DESCRIPTION, DESCRIPTION);
        values.put(PRODUCT_PRICE, PRICE);
        values.put(PRODUCT_LATITUDE, LATITUDE);
        values.put(PRODUCT_LONGITUDE,LONGITUDE);
        long result = db.insert(TABLE_PRODUCTS, null, values);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getdata (){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM PRODUCTS", null);
        return cursor;
    }


}
