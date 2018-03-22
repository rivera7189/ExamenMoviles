package com.iteso.pdm18_scrollabletabs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.database.CategoryControl;
import com.iteso.pdm18_scrollabletabs.database.CityControl;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.database.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.database.StoreControl;
import com.iteso.pdm18_scrollabletabs.tools.Constant;

import java.util.ArrayList;

/**
 * @author Oscar Vargas
 * @since 02/03/18
 */
public class ActivityMain extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FragmentTechnology fragmentTecnology;
    private FragmentElectronics fragmentElectronics;
    private FragmentHome fragmentHome;
    CityControl cityController;
    StoreControl storeControl;
    CategoryControl categoryControl;
    ItemProductControl productControl;
    DataBaseHandler dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dh = DataBaseHandler.getInstance(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMain.this,ActivityItem.class);
                startActivityForResult(intent,12);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(ActivityMain.class.getSimpleName(),"result");
        switch (requestCode){
            case 9999:
                if(resultCode == Activity.RESULT_OK){
                    ItemProduct product = data.getParcelableExtra("Producto");
                    if(product != null){
                        Log.d(ActivityMain.class.getSimpleName(),product.getTitle());
                        fragmentTecnology.onChange(product);
                    }
                }
                break;
            case 12:
                if(resultCode == Activity.RESULT_OK){
                    ArrayList<ItemProduct> products ;
                    ItemProductControl itemProductControl = new ItemProductControl();
                    switch (data.getIntExtra("category",-1)){

                        case 0:
                            if(fragmentTecnology!= null) {
                                ItemProduct product = data.getParcelableExtra("product");
                                fragmentTecnology.onChange(product);
                            }
                            break;
                        case 1:
                            if(fragmentHome != null) {
                                ItemProduct product = data.getParcelableExtra("product");
                                fragmentHome.onChange(product);
                            }
                            break;
                        case 2:
                            if(fragmentElectronics != null) {
                                ItemProduct product = data.getParcelableExtra("product");
                                fragmentElectronics.onChange(product);
                            }
                            break;
                    }
                }
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }




    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:return (fragmentTecnology==null)?fragmentTecnology = new FragmentTechnology():fragmentTecnology;
                case 1: return (fragmentHome==null)?fragmentHome = new FragmentHome():fragmentHome;
                case 2: return (fragmentElectronics==null)?fragmentElectronics = new FragmentElectronics():fragmentElectronics;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0: return "Technology";
                case 1: return "Home";
                case 2: return "Electronics";
            }
            return null;
        }
    }
}
