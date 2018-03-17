package com.iteso.pdm18_scrollabletabs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;

/**
 * Created by Carlos Rivera on 16/03/2018.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    private  static final  String DATABASE_NAME = "ItesoStore.db";
    private  static final  int    DATABASE_VERSION = 1;


    private static  DataBaseHandler dataBaseHandler;

    private DataBaseHandler (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance (Context context){
        if (dataBaseHandler == null){
            dataBaseHandler = new DataBaseHandler(context);
        }
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String city =
                "CREATE TABLE City (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT" +
                        ")";

        String category =
                "CREATE TABLE Category (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT" +
                        ")";

        String store =
                "CREATE TABLE Store (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT," +
                        "phone TEXT," +
                        "idcity INTEGER," +
                        "thumbnail INTEGER," +
                        "latitude DOUBLE," +
                        "longitude DOUBLE" +
                        ")";

        String product =
                "CREATE TABLE Product (" +
                        "idproduct INTEGER PRIMARY KEY, " +
                        "title TEXT," +
                        "image INTEGER," +
                        "idcategory INTEGER" +
                        ")";

        String storeProduct =
                "CREATE TABLE StoreProduct (" +
                        "id INTEGER PRIMARY KEY," +
                        "idproduct INTEGER," +
                        "idstore INTEGER" +
                        ")";
        sqLiteDatabase.execSQL(city);
        sqLiteDatabase.execSQL(category);
        sqLiteDatabase.execSQL(store);
        sqLiteDatabase.execSQL(product);
        sqLiteDatabase.execSQL(storeProduct);

        sqLiteDatabase.execSQL("INSERT INTO Category (id, name) VALUES (0, 'TECHNOLOGY')");
        sqLiteDatabase.execSQL("INSERT INTO Category (id, name) VALUES (1, 'HOME')");
        sqLiteDatabase.execSQL("INSERT INTO Category (id, name) VALUES (2, 'ELECTRONICS')");
        sqLiteDatabase.execSQL("INSERT INTO City     (id, name) VALUES (0, 'GUADALAJARA')");
        sqLiteDatabase.execSQL("INSERT INTO City     (id, name) VALUES (1, 'QUERETARO')");
        sqLiteDatabase.execSQL("INSERT INTO City     (id, name) VALUES (2, 'VERACRUZ')");
        sqLiteDatabase.execSQL("INSERT INTO City     (id, name) VALUES (3, 'MONTERREY')");
        sqLiteDatabase.execSQL("INSERT INTO City     (id, name) VALUES (4, 'MORELIA')");
        sqLiteDatabase.execSQL("INSERT INTO City     (id, name) VALUES (5, 'ZACATECAS')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
