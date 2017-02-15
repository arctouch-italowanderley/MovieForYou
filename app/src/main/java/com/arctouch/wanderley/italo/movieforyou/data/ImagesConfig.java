package com.arctouch.wanderley.italo.movieforyou.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by italowanderley on 14/02/17.
 */

public class ImagesConfig extends BaseModel {

    @SerializedName("base_url")
    private String mBaseUrl;

    @SerializedName("secure_base_url")
    private String mSecureBaseUrl;

    @SerializedName("backdrop_sizes")
    private List<String> mBackdropSizes;

    @SerializedName("logo_sizes")
    private List<String> mLogoSizes;

    @SerializedName("poster_sizes")
    private List<String> mPosterSizes;

    @SerializedName("profile_sizes")
    private List<String> mProfileSizes;

    @SerializedName("still_sizes")
    private List<String> mStillSizes;

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    public ImagesConfig withBaseUrl(String baseUrl) {
        setBaseUrl(baseUrl);
        return this;
    }

    public String getSecureBaseUrl() {
        return mSecureBaseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.mSecureBaseUrl = secureBaseUrl;
    }

    public ImagesConfig withSecureBaseUrl(String secureBaseUrl) {
        setSecureBaseUrl(secureBaseUrl);
        return this;
    }

    public List<String> getBackdropSizes() {
        return mBackdropSizes;
    }

    public void setBackdropSizes(List<String> backdropSizes) {
        this.mBackdropSizes = backdropSizes;
    }

    public ImagesConfig withBackdropSizes(List<String> backdropSizes) {
        setBackdropSizes(backdropSizes);
        return this;
    }

    public List<String> getLogoSizes() {
        return mLogoSizes;
    }

    public void setLogoSizes(List<String> logoSizes) {
        this.mLogoSizes = logoSizes;
    }

    public ImagesConfig withLogoSizes(List<String> logoSizes) {
        setLogoSizes(logoSizes);
        return this;
    }

    public List<String> getPosterSizes() {
        return mPosterSizes;
    }

    public void setPosterSizes(List<String> posterSizes) {
        this.mPosterSizes = posterSizes;
    }

    public ImagesConfig withPosterSizes(List<String> posterSizes) {
        setPosterSizes(posterSizes);
        return this;
    }

    public List<String> getProfileSizes() {
        return mProfileSizes;
    }

    public void setProfileSizes(List<String> profileSizes) {
        this.mProfileSizes = profileSizes;
    }

    public ImagesConfig withProfileSizes(List<String> profileSizes) {
        setProfileSizes(profileSizes);
        return this;
    }

    public List<String> getStillSizes() {
        return mStillSizes;
    }

    public void setStillSizes(List<String> stillSizes) {
        this.mStillSizes = stillSizes;
    }

    public ImagesConfig withStillSizes(List<String> stillSizes) {
        setStillSizes(stillSizes);
        return this;
    }
}
