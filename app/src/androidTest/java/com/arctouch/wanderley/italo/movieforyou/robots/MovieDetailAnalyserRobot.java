package com.arctouch.wanderley.italo.movieforyou.robots;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.ui.viewmodels.MovieViewModel;

/**
 * Created by italowanderley on 13/02/17.
 */

public class MovieDetailAnalyserRobot extends BaseViewAnalyserRobot {
    private MovieViewModel mMovieViewModel;

    private MovieDetailAnalyserRobot() {
    }

    public MovieDetailAnalyserRobot(MovieViewModel movieViewModel) {
        mMovieViewModel = movieViewModel;
    }

    @Override
    public BaseViewAnalyserRobot analyseTexts() {
        verifyIfThisTextViewExists(R.id.m_movie_title, mMovieViewModel.getOriginalTitle());
        verifyIfThisTextViewExists(R.id.m_movie_genres, mMovieViewModel.getGenres());
        verifyIfThisTextViewExists(R.id.m_movie_vote_average, mMovieViewModel.getVoteAverage());
        verifyIfThisTextViewExists(R.id.m_movie_overview, mMovieViewModel.getOverview());
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
