/*
 * Copyright (c) 2017 ArcTouch LLC.
 * All rights reserved.
 *
 * This file, its contents, concepts, methods, behavior, and operation
 * (collectively the "Software") are protected by trade secret, patent,
 * and copyright laws. The use of the Software is governed by a license
 * agreement. Disclosure of the Software to third parties, in any form,
 * in whole or in part, is expressly prohibited except as authorized by
 * the license agreement.
 */

package com.arctouch.wanderley.italo.movieforyou.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by italowanderley on 21/02/17.
 */

public class MovieListWrapper extends BaseModel {

    @SerializedName("page")
    private Integer mPage;

    @SerializedName("results")
    private List<Movie> mResults;

    @SerializedName("total_results")
    private Integer mTotalResults;

    @SerializedName("total_pages")
    private Integer mTotalPages;

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        mPage = page;
    }

    public MovieListWrapper withPage(Integer page) {
        setPage(page);
        return this;
    }

    public List<Movie> getResults() {
        return mResults;
    }

    public void setResults(List<Movie> results) {
        mResults = results;
    }

    public MovieListWrapper withResults(List<Movie> results) {
        setResults(results);
        return this;
    }

    public Integer getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Integer totalResults) {
        mTotalResults = totalResults;
    }

    public MovieListWrapper withTotalResults(Integer totalResults) {
        setTotalResults(totalResults);
        return this;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        mTotalPages = totalPages;
    }

    public MovieListWrapper withTotalPages(Integer totalPages) {
        setTotalPages(totalPages);
        return this;
    }
}
