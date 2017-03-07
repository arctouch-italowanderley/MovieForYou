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

package com.arctouch.wanderley.italo.movieforyou.domain;

import com.arctouch.wanderley.italo.movieforyou.core.BaseApplication;
import com.arctouch.wanderley.italo.movieforyou.data.Genre;
import com.arctouch.wanderley.italo.movieforyou.data.Movie;
import com.arctouch.wanderley.italo.movieforyou.data.MovieListWrapper;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by italowanderley on 26/02/17.
 */
@EBean
public class MovieListInteractor extends BaseInteractor {
    private final String TAG = MovieListInteractor.class.getSimpleName();

    private DisposableSingleObserver<List<Movie>> mMovieListObserver;

    public void listMovies(final DisposableSingleObserver<List<Movie>> movieListObserver) {
        mMovieListObserver = movieListObserver;

        mApi.getAnonymousClient()
                .getMovieList()
                .map(prepareMovieDataWithCategories())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(mMovieListObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposeObserver(mMovieListObserver);
    }

    private Function prepareMovieDataWithCategories() {
        return new Function<MovieListWrapper, List<Movie>>() {
            @Override
            public List<Movie> apply(@NonNull MovieListWrapper movieListWrapper) throws Exception {
                List<Movie> movieList = movieListWrapper.getResults();
                for (Movie movie : movieList) {
                    List<Genre> genreList = new ArrayList<>();
                    for (Integer genreId : movie.getGenreIds()) {
                        genreList.add(new Genre().withId(genreId).withName(BaseApplication.genreMap.get(genreId)));
                    }
                    movie.setGenres(genreList);
                    movie.cleanGenreIds();
                }
                return movieList;
            }
        };
    }
}
