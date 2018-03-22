package com.iteso.pdm18_scrollabletabs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.tools.Constant;

import java.util.ArrayList;

/**
 * @author Oscar Vargas
 * @since 26/02/18.
 */

class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    ArrayList<ItemProduct> products;

    public AdapterProduct(ArrayList<ItemProduct> products) {
        this.products = products;
    }

    public AdapterProduct(int fragmentElectronics, FragmentActivity activity, ArrayList<ItemProduct> products) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mStore;
        public TextView mPhone;
        public ImageView mImage;
        public TextView mLocation;
        public String data;
        public ItemProduct product;


        public ViewHolder(final View v) {
            super(v);
            mTitle = v.findViewById(R.id.item_product_title);
            mStore = v.findViewById(R.id.item_product_store);
            mPhone = v.findViewById(R.id.item_product_phone);
            mImage = v.findViewById(R.id.item_product_image);
            mLocation = v.findViewById(R.id.item_product_location);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), data, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(),ActivityProduct.class);
                    intent.putExtra("Producto",product);
                    ActivityMain main = (ActivityMain) v.getContext();
                    main.startActivityForResult(intent,9999);

                }
            });


            mPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + mPhone.getText().toString()));
                    if(ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity) v.getContext(),new String[]{Manifest.permission.CALL_PHONE},1999);
                    }else{
                        v.getContext().startActivity(callIntent);                    }


                }
            });

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTitle.setText(products.get(position).getTitle());
        //products.get(position).getStore().getName(),products.get(position).getStore().getPhone()
        holder.mStore.setText(products.get(position).getStore().getName());
        holder.mPhone.setText(products.get(position).getStore().getPhone());
        holder.data = products.get(position).toString();
        double latitude = products.get(position).getStore().getLatitude();
        holder.mLocation.setText(String.valueOf(latitude));
        holder.product = products.get(position);

        switch (products.get(position).getImage()){

            case 0:
                holder.mImage.setImageResource(R.drawable.mac);
                break;
            case 1:
                holder.mImage.setImageResource(R.drawable.alienware);
                break;
            case 2:
                holder.mImage.setImageResource(R.drawable.mac);
                break;
            default:
                holder.mImage.setImageResource(R.drawable.bestbuy);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
