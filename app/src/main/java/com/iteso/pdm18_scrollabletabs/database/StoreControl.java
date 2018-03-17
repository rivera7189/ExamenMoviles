package com.iteso.pdm18_scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.Store;

import java.util.ArrayList;

/**
 * Created by Carlos Rivera on 16/03/2018.
 */

public class StoreControl {

    public void addStore(Store store, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", store.getId());
        values.put("name", store.getName());
        values.put("phone", store.getPhone());
        values.put("idcity",store.getCity().getId());
        values.put("latitud", store.getLatitude());
        values.put("longitud", store.getLongitude());

        db.insert("Store", null, values);
        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;

    }

    public ArrayList<Store> getStore(DataBaseHandler dh){
        ArrayList<Store> stores = new ArrayList<>();
        String select = "SELECT id, name, phone, idcity, latitud, longitud , City.id, City.name FROM Category INNER JOIN City ON Category.idcity = City.id";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Store store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            City city = new City(cursor.getInt(6),
                                 cursor.getString(7));
            store.setCity(city);
            store.setLatitude(cursor.getInt(4));
            store.setLongitude(cursor.getInt(5));
            stores.add(store);
        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return stores;
    }

    public Store getStoreById(Integer idStore, DataBaseHandler dh){
        String select = "SELECT id, name, phone, idcity, latitud, longitud , City.id, City.name FROM Category WHERE id = " + idStore + " INNER JOIN City ON Category.idcity = City.id";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Store store= null;

        if(cursor != null ){
            cursor.moveToNext();
            store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            City city = new City(cursor.getInt(6),
                    cursor.getString(7));
            store.setCity(city);
            store.setLatitude(cursor.getInt(4));
            store.setLongitude(cursor.getInt(5));
        }

        try{
            cursor.close();
            db.close();
        }catch(Exception e){
        }

        db = null;
        cursor = null;
        return store;
    }


}
