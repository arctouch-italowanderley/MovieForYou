package com.arctouch.wanderley.italo.movieforyou.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by italowanderley on 13/02/17.
 */

public class Movie {
    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("genres")
    private List<Genre> mGenres = new ArrayList<>();

    @SerializedName("vote_average")
    private Integer mVoteAverage;

    @SerializedName("overview")
    private String mOverview;

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.mOriginalTitle = originalTitle;
    }

    public Movie withOriginalTitle(String originalTitle) {
        setOriginalTitle(originalTitle);
        return this;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public String getFormattedGenres() {
        StringBuilder formattedGenres = new StringBuilder("");

        for (int i = 0; i < mGenres.size(); i++) {
            formattedGenres.append(mGenres.get(i).getName());
            if (i != mGenres.size() - 1) formattedGenres.append(", ");
        }
        return formattedGenres.toString();
    }

    public void setGenres(List<Genre> genres) {
        this.mGenres = genres;
    }

    public Movie withGenres(List<Genre> genres) {
        setGenres(genres);
        return this;
    }

    public Integer getVoteAverage() {
        return mVoteAverage;
    }

    public String getFormattedVoteAverage() {
        // TODO: verify if this limit (10) is right and what is the best way to update this info
        return mVoteAverage + " / 10";
    }

    public void setVoteAverage(Integer voteAverage) {
        this.mVoteAverage = voteAverage;
    }

    public Movie withVoteAverage(Integer voteAverage) {
        setVoteAverage(voteAverage);
        return this;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    public Movie withOverview(String overview) {
        setOverview(overview);
        return this;
    }
}
