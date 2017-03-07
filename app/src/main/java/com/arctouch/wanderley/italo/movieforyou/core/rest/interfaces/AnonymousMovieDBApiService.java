package com.arctouch.wanderley.italo.movieforyou.core.rest.interfaces;

import com.arctouch.wanderley.italo.movieforyou.data.GenreListWrapper;
import com.arctouch.wanderley.italo.movieforyou.data.ImagesConfigWrapper;
import com.arctouch.wanderley.italo.movieforyou.data.MovieListWrapper;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by italowanderley on 14/02/17.
 */

public interface AnonymousMovieDBApiService {

    @GET("configuration?api_key=ab1d8287bf920723e20399d553e561b3")
    Single<ImagesConfigWrapper> getCurrentImageUrlConfig();

    @GET("discover/movie?api_key=ab1d8287bf920723e20399d553e561b3&language=pt-BR&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&release_date.gte=2016-11-16&release_date.lte=2016-12-02&with_release_type=2|3")
    Single<MovieListWrapper> getMovieList();

    @GET("genre/movie/list?api_key=ab1d8287bf920723e20399d553e561b3&language=pt-BR")
    Single<GenreListWrapper> getGenreList();
}
