package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
    Spinner categories,stores, images;
    ArrayList<Category> categoriesArray;
    ArrayList<Store> storesArray;
    ArrayList<String> imagesArray;
    DataBaseHandler dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        title = findViewById(R.id.title_activity);
        categories = findViewById(R.id.category_activity);
        stores = findViewById(R.id.stores_activity);
        images = findViewById(R.id.image_activity);


        CategoryControl categoryControll = new CategoryControl();
        StoreControl storeControl = new StoreControl();

        dh = DataBaseHandler.getInstance(ActivityItem.this);

        categoriesArray = categoryControll.getCategory(dh);
        storesArray = storeControl.getStore(dh);
        imagesArray = new ArrayList<String>();
        imagesArray.add("Mac");
        imagesArray.add("AlienWare");

        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, categoriesArray);
        ArrayAdapter<Store>  storeAdapter = new ArrayAdapter<Store>(this,android.R.layout.simple_list_item_1,storesArray);
        ArrayAdapter<String> imagesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,imagesArray);

        categories.setAdapter(categoryAdapter);
        stores.setAdapter(storeAdapter);
        images.setAdapter(imagesAdapter);

    }
    public void Save(View view){
        Category category = (Category) categories.getSelectedItem();
        Store store = (Store) stores.getSelectedItem();
        ItemProductControl itemProductControll = new ItemProductControl();
        ArrayList<ItemProduct> products = itemProductControll.getProducts(dh);

        int imageIndex = images.getSelectedItemPosition();
        ItemProduct product = new ItemProduct(products.size()+1,title.getText().toString(),"",imageIndex,store,category);

        itemProductControll.addProduct(product,dh);
        Intent intent = new Intent();
        intent.putExtra("category",categories.getSelectedItemPosition());
        intent.putExtra("product",product);
        setResult(RESULT_OK, intent);
        finish();
    }
}
