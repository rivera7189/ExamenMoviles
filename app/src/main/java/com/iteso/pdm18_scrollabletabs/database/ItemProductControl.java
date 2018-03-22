package com.iteso.pdm18_scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.beans.StoreProduct;

import java.util.ArrayList;

/**
 * Created by Carlos Rivera on 16/03/2018.
 */

public class ItemProductControl {

    public void addProduct(ItemProduct product, DataBaseHandler dh){
        StoreProductControl storeProductControll = new StoreProductControl();

        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idproduct", product.getCode());
        values.put("title", product.getTitle());
        values.put("image",product.getImage());
        values.put("idcategory",product.getCategory().getId());
        values.put("description",product.getDescription());
        db.insert("Product", null, values);
        int size = storeProductControll.getSize(dh);

        StoreProduct storeProduct = new StoreProduct();
        storeProduct.setId(size+1);
        storeProduct.setIdproduct(product.getCode());
        storeProduct.setIdstore(product.getStore().getId());
        storeProductControll.addStoreProduct(storeProduct,dh);


        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;

    }


    public ArrayList<ItemProduct> getProductsByCategory(int idCategory,DataBaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<>();
        String select = "SELECT p.idproduct, p.title, p.image, p.description, c.id, c.name, sp.idstore "+
                "FROM Product p, Category c, StoreProduct sp " +
                "WHERE sp.idproduct = p.idproduct AND p.idcategory = c.id AND c.id = "+idCategory;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        StoreControl storeControl = new StoreControl();
        ItemProduct itemProduct = new ItemProduct();
        while(cursor.moveToNext()) {

            itemProduct.setCode(cursor.getInt(0 ));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));
            itemProduct.setDescription(cursor.getString(3));

            Category category = new Category();
            category.setId(cursor.getInt(4));
            category.setName(cursor.getString(5));
            itemProduct.setCategory(category);

            Store store = storeControl.getStoreById(cursor.getInt(6),dh);
            itemProduct.setStore(store);
            products.add(itemProduct);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e) {
        }

        cursor = null;
        db = null;
        return products;
    }

    public ArrayList<ItemProduct> getProducts(DataBaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<>();
        String select = "SELECT p.idproduct, p.title, p.image, p.description, c.id, c.name, sp.idstore "+
                "FROM Product p, Category c, StoreProduct sp " +
                "WHERE sp.idproduct = p.idproduct AND p.idcategory = c.id " ;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        StoreControl storeControl = new StoreControl();
        ItemProduct itemProduct = new ItemProduct();
        while(cursor.moveToNext()) {

            itemProduct.setCode(cursor.getInt(0 ));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));
            itemProduct.setDescription(cursor.getString(3));

            Category category = new Category();
            category.setId(cursor.getInt(4));
            category.setName(cursor.getString(5));
            itemProduct.setCategory(category);

            Store store = storeControl.getStoreById(cursor.getInt(6),dh);
            itemProduct.setStore(store);
            products.add(itemProduct);
        }
        try {
            cursor.close();
            db.close();
        }catch (Exception e) {
        }

        cursor = null;
        db = null;
        return products;
    }

}
