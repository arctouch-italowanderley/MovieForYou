package com.arctouch.wanderley.italo.movieforyou.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by italowanderley on 13/02/17.
 */

public class Genre extends BaseModel {
    @SerializedName("id")
    private Integer mId;

    @SerializedName("name")
    private String mName;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public Genre withId(Integer id) {
        setId(id);
        return this;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Genre withName(String name) {
        setName(name);
        return this;
    }
}
