package com.iteso.pdm18_scrollabletabs.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carlos Rivera on 21/03/2018.
 */

public class StoreProduct implements Parcelable {
    private int id,idproduct,idstore;
    public StoreProduct(){

    }
    public StoreProduct(int id, int idproduct, int idstore) {
        this.id = id;
        this.idproduct = idproduct;
        this.idstore = idstore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int getIdstore() {
        return idstore;
    }

    public void setIdstore(int idstore) {
        this.idstore = idstore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.idproduct);
        dest.writeInt(this.idstore);
    }

    protected StoreProduct(Parcel in) {
        this.id = in.readInt();
        this.idproduct = in.readInt();
        this.idstore = in.readInt();
    }

    public static final Parcelable.Creator<StoreProduct> CREATOR = new Parcelable.Creator<StoreProduct>() {
        @Override
        public StoreProduct createFromParcel(Parcel source) {
            return new StoreProduct(source);
        }

        @Override
        public StoreProduct[] newArray(int size) {
            return new StoreProduct[size];
        }
    };
}
