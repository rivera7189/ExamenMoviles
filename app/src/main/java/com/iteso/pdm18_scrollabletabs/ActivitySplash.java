package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.beans.StoreProduct;
import com.iteso.pdm18_scrollabletabs.beans.User;
import com.iteso.pdm18_scrollabletabs.database.CategoryControl;
import com.iteso.pdm18_scrollabletabs.database.CityControl;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.database.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.database.StoreControl;
import com.iteso.pdm18_scrollabletabs.database.StoreProductControl;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    private static final int THUMBNAIL = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*
        Al	 momento	 de	 crear	 la	 actividad	 ActivitySplashScreen	 cargar	 las    preferencias	y	validar	la	existencia	de	un	usuario	en	memoria.
        iii. En	 caso	 de	 estar	 el	 atributo	 logged = true	 llamar	 a	 la	 actividad	 principal
        ActivityMain,	en	caso	contrario	a	ActivityLogin
        iv. Borrar	del	stack ActivitySplashScreen
        */

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Intent mainIntent;

                    mainIntent = new Intent().setClass(ActivitySplash.this, ActivityLogin.class);

                DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplash.this);
                StoreControl storeControl = new StoreControl();
                CityControl cityController = new CityControl();
                ItemProductControl itemProductControl = new ItemProductControl();
                CategoryControl categoryControl = new CategoryControl();
                StoreProductControl storeProductControl = new StoreProductControl();
                ArrayList<Store> stores = storeControl.getStore(dh);
                if (stores.size() == 0) {
                    //add 3
                    City guadalajara = new City(1, "Guadalajara");
                    City zapopan = new City(2, "Zapopan");


                    Store Walmart = new Store(1, "Walmart", "3333333333", 1, 3.1416, 2.17, guadalajara);
                    Store BestBuy = new Store(2, "Best buy", "3333333333", 1, 3.1416, 2.17, zapopan);
                    Store Sams = new Store(3, "Sams", "3333333333", 2, 3.1416, 2.17, guadalajara);
                    storeControl.addStore(Walmart, dh);
                    storeControl.addStore(BestBuy, dh);
                    storeControl.addStore(Sams, dh);
                    Category electronic = new Category(0, "Electronics");
                    Category home = new Category(1, "Home");
                    Category tecnology = new Category(2, "Tecnology");
                    categoryControl.addCategory(electronic, dh);
                    categoryControl.addCategory(home, dh);
                    categoryControl.addCategory(tecnology, dh);
                    ItemProduct mac = new ItemProduct(1, "Mac", "Mac 2018", 2, Walmart, tecnology);
                    ItemProduct alienware = new ItemProduct(2, "AlienWare", "Alienware 2018", 1, BestBuy, tecnology);
                    itemProductControl.addProduct(mac, dh);
                    itemProductControl.addProduct(alienware, dh);

                    StoreProduct sp = new StoreProduct(1, mac.getCode(), Walmart.getId());
                    StoreProduct sp2 = new StoreProduct(2, alienware.getCode(), BestBuy.getId());
                    storeProductControl.addStoreProduct(sp, dh);
                    storeProductControl.addStoreProduct(sp2, dh);


                }
                startActivity(mainIntent);

                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }


    }
