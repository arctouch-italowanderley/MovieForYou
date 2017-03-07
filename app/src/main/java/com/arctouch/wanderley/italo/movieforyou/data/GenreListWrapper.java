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
 * Created by italowanderley on 01/03/17.
 */

public class GenreListWrapper extends BaseModel {

    @SerializedName("genres")
    private List<Genre> mGenreList;

    public List<Genre> getResults() {
        return mGenreList;
    }

    public void setResults(List<Genre> genreList) {
        mGenreList = genreList;
    }

    public GenreListWrapper withResults(List<Genre> genreList) {
        setResults(genreList);
        return this;
    }
}
