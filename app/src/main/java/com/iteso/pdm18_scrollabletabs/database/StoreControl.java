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
        values.put("latitude", store.getLatitude());
        values.put("longitude", store.getLongitude());

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
        String select = "SELECT s.id, s.name, phone, c.id, c.name, thumbnail, latitude, longitude FROM Store s, City c WHERE s.idcity = c.id";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Store store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            City city = new City(cursor.getInt(3),cursor.getString(4));
            store.setCity(city);
            store.setThumbnail(cursor.getInt(5));
            store.setLatitude(cursor.getDouble(6));
            store.setLongitude(cursor.getDouble(7));
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
        Store store = new Store();
        String select = "SELECT s.id, s.name, phone, c.id, c.name, thumbnail, latitude, longitude FROM Store s, City c WHERE s.idcity = c.id and s.id = "+idStore;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){

            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            City city = new City(cursor.getInt(3),cursor.getString(4));
            store.setCity(city);
            store.setThumbnail(cursor.getInt(5));
            store.setLatitude(cursor.getDouble(6));
            store.setLongitude(cursor.getDouble(7));

        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return store;
    }


}
