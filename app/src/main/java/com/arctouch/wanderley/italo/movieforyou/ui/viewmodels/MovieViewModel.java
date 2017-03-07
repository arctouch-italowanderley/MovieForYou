package com.arctouch.wanderley.italo.movieforyou.ui.viewmodels;

import com.arctouch.wanderley.italo.movieforyou.data.Movie;

import java.io.Serializable;

/**
 * Created by italowanderley on 16/02/17.
 */

public class MovieViewModel implements Serializable {

    private Movie mMovie;

    public MovieViewModel(final Movie movie) {
        mMovie = movie;
    }

    public String getTitle() {
        return mMovie.getTitle();
    }

    public String getOriginalTitle() {
        return mMovie.getOriginalTitle();
    }

    public String getGenres() {
        StringBuilder formattedGenres = new StringBuilder("");
        for (int i = 0; i < mMovie.getGenres().size(); i++) {
            formattedGenres.append(mMovie.getGenres().get(i).getName());
            if (i != mMovie.getGenres().size() - 1) formattedGenres.append(", ");
        }
        return formattedGenres.toString();
    }


    public String getVoteAverage() {
        // TODO: verify if this limit (10) is right and what is the best way to update this info
        return mMovie.getVoteAverage() + " / 10";
    }

    public String getOverview() {
        return mMovie.getOverview();
    }

    public String getPosterPath() {
        return mMovie.getPosterPath();
    }

    public String getBackdropPath() {
        return mMovie.getBackdropPath();
    }
}
