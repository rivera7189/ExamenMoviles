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
public class FragmentElectronics extends Fragment {


    RecyclerView recyclerView;
    private static final String ARG_SECTION_NUMBER = "section_number";
    public AdapterProduct adapterProduct;
    ArrayList<ItemProduct> products ;

    public FragmentElectronics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());


        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_recycler_view);

        ItemProductControl itemProductControl = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(this.getContext());
        products = itemProductControl.getProductsByCategory(0,dh);
        AdapterProduct adapterProduct = new AdapterProduct(Constant.FRAGMENT_ELECTRONICS, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
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
