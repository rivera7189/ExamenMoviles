package com.iteso.pdm18_scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.StoreProduct;

/**
 * Created by Carlos Rivera on 21/03/2018.
 */

public class StoreProductControl {

    public void addStoreProduct(StoreProduct storeProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", storeProduct.getId());
        values.put("idproduct", storeProduct.getIdproduct());
        values.put("idstore", storeProduct.getIdstore());
        db.insert("StoreProduct", null, values);
        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;

    }

    public int getSize(DataBaseHandler dh){
        int count = 0;
        String select = "SELECT * FROM StoreProduct";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            count++;
        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return count;
    }

}
