package com.arctouch.wanderley.italo.movieforyou.ui.fragments;

import android.widget.ImageView;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.core.BaseApplication;
import com.arctouch.wanderley.italo.movieforyou.data.ImagesConfig;
import com.arctouch.wanderley.italo.movieforyou.ui.activities.MovieDetailActivity;
import com.arctouch.wanderley.italo.movieforyou.ui.viewmodels.MovieViewModel;
import com.arctouch.wanderley.italo.movieforyou.ui.views.CustomTextView;
import com.squareup.picasso.Picasso;

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

    @ViewById
    protected ImageView mMovieImage;

    private MovieViewModel mMovieViewModel;

    @AfterViews
    protected void movieDetailFragmentAfterViews() {
        mMovieViewModel = ((MovieDetailActivity) mActivity).getMovieViewModel();

        setupMovieImage();
        setupMovieTexts();
    }

    private void setupMovieImage() {
        ImagesConfig imagesConfig = BaseApplication.imagesConfig;
        if (imagesConfig != null) {
            Picasso.with(mActivity).load(imagesConfig.getBaseUrl() + "w500" + mMovieViewModel.getBackdropPath()).into(mMovieImage);
        }
    }

    private void setupMovieTexts() {
        setText(mMovieTitle, mMovieViewModel.getOriginalTitle());
        setText(mMovieGenres, mMovieViewModel.getGenres());
        setText(mMovieVoteAverage, mMovieViewModel.getVoteAverage());
        setText(mMovieOverview, mMovieViewModel.getOverview());
    }
}
