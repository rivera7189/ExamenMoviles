package com.iteso.pdm18_scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;

import java.util.ArrayList;

/**
 * Created by Carlos Rivera on 16/03/2018.
 */

public class CategoryControl {


    public void addCategory(Category category, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("name", category.getName());
        db.insert("Category", null, values);
        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;

    }

    public ArrayList<Category> getCategory(DataBaseHandler dh){
        ArrayList<Category> categories = new ArrayList<>();
        String select = "SELECT id, name FROM Category";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return categories;
    }



}
