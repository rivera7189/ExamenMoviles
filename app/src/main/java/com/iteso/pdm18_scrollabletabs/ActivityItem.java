package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.database.CategoryControl;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.database.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {
    EditText title;
    Button save;
    Spinner categoriesSpinner,storesSpinner, images;
    ArrayList<Category> categoriesArray;
    ArrayList<Store> storesArray;
    ArrayList<String> imagesArray, categories, stores;
    DataBaseHandler dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        title = findViewById(R.id.title_activity);
        categoriesSpinner = findViewById(R.id.category_activity);
        storesSpinner = findViewById(R.id.stores_activity);
        images = findViewById(R.id.image_activity);
        save = findViewById(R.id.btn_save_activity_item);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save(view);
            }
        });

        CategoryControl categoryControll = new CategoryControl();
        StoreControl storeControl = new StoreControl();

        dh = DataBaseHandler.getInstance(ActivityItem.this);

        categoriesArray = categoryControll.getCategory(dh);
        categories = new ArrayList<>();

        for(int i = 0; i < categoriesArray.size(); i++) {
            categories.add(categoriesArray.get(i).getName());
        }

        storesArray = storeControl.getStore(dh);
        stores = new ArrayList<>();

        for(int i = 0; i < storesArray.size(); i++) {
            stores.add(storesArray.get(i).getName());
        }

        imagesArray = new ArrayList<String>();
        imagesArray.add("Mac");
        imagesArray.add("AlienWare");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        ArrayAdapter<String>  storeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, stores);
        ArrayAdapter<String> imagesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,imagesArray);

        categoriesSpinner.setAdapter(categoryAdapter);
        storesSpinner.setAdapter(storeAdapter);
        images.setAdapter(imagesAdapter);

    }
    public void Save(View view){
        Category category = categoriesArray.get(categoriesSpinner.getSelectedItemPosition());
        Store store = storesArray.get(storesSpinner.getSelectedItemPosition());
        ItemProductControl itemProductControll = new ItemProductControl();
        ArrayList<ItemProduct> products = itemProductControll.getProducts(dh);

        int imageIndex = images.getSelectedItemPosition();
        ItemProduct product = new ItemProduct(products.size()+1,title.getText().toString(),"",imageIndex,store,category);

        itemProductControll.addProduct(product,dh);
        Intent intent = new Intent();
        intent.putExtra("category",category);
        intent.putExtra("product",product);
        setResult(RESULT_OK, intent);
        finish();
    }
}
