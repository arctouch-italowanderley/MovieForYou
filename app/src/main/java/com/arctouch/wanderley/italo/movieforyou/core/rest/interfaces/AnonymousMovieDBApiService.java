package com.arctouch.wanderley.italo.movieforyou.core.rest.interfaces;

import com.arctouch.wanderley.italo.movieforyou.data.ImagesConfigWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by italowanderley on 14/02/17.
 */

public interface AnonymousMovieDBApiService {

    @GET("/configuration")
    Call<ImagesConfigWrapper> getCurrentImageUrlConfig();
}
