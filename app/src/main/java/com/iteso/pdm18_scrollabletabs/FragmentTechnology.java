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
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTechnology extends Fragment {

    RecyclerView recyclerView;
    private static final String ARG_SECTION_NUMBER = "section_number";
    ArrayList<ItemProduct> products = new ArrayList<>();
    public AdapterProduct adapterProduct;

    public FragmentTechnology() {
        // Required empty public constructor
    }


    public static FragmentTechnology newInstance(int sectionNumber) {
        FragmentTechnology fragment = new FragmentTechnology();
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

        ItemProductControl itemProductControll = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(this.getContext());
        products = itemProductControll.getProductsByCategory(2,dh);

        adapterProduct = new AdapterProduct(products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterProduct);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        products = new ArrayList<>();
      //  products.add(new ItemProduct(1, "Mac", "BestBuy", "Zapopan", "3312345678", "Lorem Ipsum ....", Constant.TYPE_MAC));
      //  products.add(new ItemProduct(2, "Alienware", "DELL", "Zapopan", "3312345678", "Lorem Ipsum ....", Constant.TYPE_ALIENWARE));
      //  products.add(new ItemProduct(3, "Lanix", "Saint Jhonny", "Zapopan", "3312345678", "Lorem Ipsum ....", Constant.TYPE_ALIENWARE));


        adapterProduct = new AdapterProduct(Constant.FRAGMENT_TECHNOLOGY, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ItemProduct itemProduct = data.getParcelableExtra(Constant.EXTRA_PRODUCT);
        Iterator<ItemProduct> iterator = products.iterator();
        int position = 0;
        while(iterator.hasNext()){
            ItemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()){
                products.set(position, itemProduct);
                break;
            }
            position++;
        }
        adapterProduct.notifyDataSetChanged();

    }

    public void onChange(ItemProduct product){
        products.add(product);
        adapterProduct.notifyDataSetChanged();
    }
}
