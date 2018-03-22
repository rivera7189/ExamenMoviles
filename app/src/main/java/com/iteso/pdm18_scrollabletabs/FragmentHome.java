package com.iteso.pdm18_scrollabletabs;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.database.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.tools.Constant;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {


    RecyclerView recyclerView;
    private static final String ARG_SECTION_NUMBER = "section_number";
    public AdapterProduct adapterProduct;
    ArrayList<ItemProduct> products ;
    public FragmentHome() {
        // Required empty public constructor
    }


    public static FragmentHome newInstance(int sectionNumber) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());


        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_recycler_view);




        ItemProductControl itemProductControl = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(this.getContext());
        products = itemProductControl.getProductsByCategory(1,dh);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(mLayoutManager);

        ArrayList<ItemProduct> products = new ArrayList<>();

        adapterProduct = new AdapterProduct(Constant.FRAGMENT_HOME, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onChange(ItemProduct product){
        products.add(product);
        adapterProduct.notifyDataSetChanged();
    }
}
