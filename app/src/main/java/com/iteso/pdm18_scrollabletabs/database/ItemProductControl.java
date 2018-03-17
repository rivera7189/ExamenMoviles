package com.iteso.pdm18_scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;

import java.util.ArrayList;

/**
 * Created by Carlos Rivera on 16/03/2018.
 */

public class ItemProductControl {

    public void addItemProduct(ItemProduct item, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idproduct", item.getCode());
        values.put("title", item.getTitle());
        values.put("image", item.getImage());
        values.put("idcategory",item.getCategory().getId());

        ContentValues valuesf = new ContentValues();

        valuesf.put("idproduct",item.getCode());
        valuesf.put("idstore",item.getStore().getId());

        db.insert("Product", null, values);
        db.insert("StoreProduct",null, valuesf);

        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;
        valuesf = null;

    }

    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<>();

        String select = "SELECT" +
                "idproduct, " +
                "title, " +
                "image, " +
                "idcategory " +
                "Category.id " +
                "Category.name " +
                "Store.id," +
                "Store.name, " +
                "Store.phone, " +
                "Store.idcity, " +
                "Store.thumbnail, " +
                "Store.latitude, " +
                "Store.longitude, " +
                "City.id, " +
                "City.name, " +
                "FROM Product " +
                "INNER JOIN Category ON Product.idcategory = Category.id " +
                "INNER JOIN StoreProduct ON Product.id = StoreProduct.idproduct" +
                "INNER JOIN Store ON Store.idstore = Store.id" +
                "INNER JOIN City ON Store.idcity = City.id" +
                "WHERE Product.idcategory = " + idCategory;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));
            Category category = new Category(cursor.getInt(3),
                                             cursor.getString(4));
            itemProduct.setCategory(category);

            Store store = new Store();
            products.add(itemProduct);
        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return products;
    }



}
