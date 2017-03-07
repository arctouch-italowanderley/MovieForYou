package com.arctouch.wanderley.italo.movieforyou.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by italowanderley on 16/02/17.
 */

public class Cinema extends BaseModel {

    @SerializedName("name")
    protected String mName;

    @SerializedName("address")
    protected String mAddress;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Cinema withName(String name) {
        setName(name);
        return this;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Cinema withAddress(String address) {
        setAddress(address);
        return this;
    }
}
