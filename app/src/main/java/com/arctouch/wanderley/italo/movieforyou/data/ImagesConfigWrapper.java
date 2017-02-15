package com.arctouch.wanderley.italo.movieforyou.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by italowanderley on 14/02/17.
 */

public class ImagesConfigWrapper extends BaseModel {

    @SerializedName("images")
    private ImagesConfig mImagesConfig;

    @SerializedName("change_keys")
    private List<String> mChangeKeys;

    public ImagesConfig getImagesConfig() {
        return mImagesConfig;
    }

    public void setImagesConfig(ImagesConfig imagesConfig) {
        this.mImagesConfig = imagesConfig;
    }

    public ImagesConfigWrapper withImagesConfig(ImagesConfig imagesConfig) {
        setImagesConfig(imagesConfig);
        return this;
    }

    public List<String> getChangeKeys() {
        return mChangeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.mChangeKeys = changeKeys;
    }

    public ImagesConfigWrapper withChangeKeys(List<String> changeKeys) {
        setChangeKeys(changeKeys);
        return this;
    }
}
