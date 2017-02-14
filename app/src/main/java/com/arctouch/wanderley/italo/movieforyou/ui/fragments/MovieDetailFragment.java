package com.arctouch.wanderley.italo.movieforyou.ui.fragments;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.data.Movie;
import com.arctouch.wanderley.italo.movieforyou.ui.activities.MovieDetailActivity;
import com.arctouch.wanderley.italo.movieforyou.ui.views.CustomTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by italowanderley on 09/02/17.
 */
@EFragment(R.layout.fragment_movie_detail)
public class MovieDetailFragment extends BaseFragment {

    @ViewById
    protected CustomTextView mMovieTitle, mMovieGenres, mMovieVoteAverage, mMovieOverview;

    private Movie mMovie;

    @AfterViews
    protected void movieDetailFragmentAfterViews() {
        mMovie = ((MovieDetailActivity) mActivity).getMovie();

        setText(mMovieTitle, mMovie.getOriginalTitle());
        setText(mMovieGenres, mMovie.getFormattedGenres());
        setText(mMovieVoteAverage, mMovie.getFormattedVoteAverage());
        setText(mMovieOverview, mMovie.getOverview());
    }
}
