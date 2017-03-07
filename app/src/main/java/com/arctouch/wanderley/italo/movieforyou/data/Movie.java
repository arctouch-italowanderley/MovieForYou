package com.arctouch.wanderley.italo.movieforyou.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by italowanderley on 13/02/17.
 */

public class Movie extends BaseModel {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("genres")
    private List<Genre> mGenres = new ArrayList<>();

    @SerializedName("genre_ids")
    private List<Integer> mGenreIds = new ArrayList<>();

    @SerializedName("vote_average")
    private Float mVoteAverage;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Movie withTitle(String title) {
        setTitle(title);
        return this;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public Movie withOriginalTitle(String originalTitle) {
        setOriginalTitle(originalTitle);
        return this;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public Movie withGenres(List<Genre> genres) {
        setGenres(genres);
        return this;
    }

    public void cleanGenre() {
        mGenres.clear();
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }

    public Movie withGenreIds(List<Integer> genreIds) {
        setGenreIds(genreIds);
        return this;
    }

    public void cleanGenreIds() {
        mGenreIds.clear();
    }

    public Float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Movie withVoteAverage(Float voteAverage) {
        setVoteAverage(voteAverage);
        return this;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Movie withOverview(String overview) {
        setOverview(overview);
        return this;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public Movie withPosterPath(String posterPath) {
        setPosterPath(posterPath);
        return this;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public Movie withBackdropPath(String backdropPath) {
        setBackdropPath(backdropPath);
        return this;
    }
}
