package com.arctouch.wanderley.italo.movieforyou.robots;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.data.Movie;

/**
 * Created by italowanderley on 13/02/17.
 */

public class MovieDetailAnalyserRobot extends BaseViewAnalyserRobot {
    private Movie mMovie;

    private MovieDetailAnalyserRobot() {
    }

    public MovieDetailAnalyserRobot(Movie movie) {
        mMovie = movie;
    }

    @Override
    public BaseViewAnalyserRobot analyseTexts() {
        verifyIfThisTextViewExists(R.id.m_movie_title, mMovie.getOriginalTitle());
        verifyIfThisTextViewExists(R.id.m_movie_genres, mMovie.getFormattedGenres());
        verifyIfThisTextViewExists(R.id.m_movie_vote_average, mMovie.getFormattedVoteAverage());
        verifyIfThisTextViewExists(R.id.m_movie_overview, mMovie.getOverview());
        return this;
    }

    @Override
    public BaseViewAnalyserRobot analyseFields() {
        return this;
    }

    @Override
    public BaseViewAnalyserRobot analyseButtons() {
        return this;
    }

    @Override
    public BaseViewAnalyserRobot analyseImages() {
        return this;
    }
}
