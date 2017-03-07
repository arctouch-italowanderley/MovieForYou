package com.arctouch.wanderley.italo.movieforyou.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.core.BaseApplication;
import com.arctouch.wanderley.italo.movieforyou.data.ImagesConfig;
import com.arctouch.wanderley.italo.movieforyou.ui.activities.MovieDetailActivity_;
import com.arctouch.wanderley.italo.movieforyou.ui.viewmodels.MovieViewModel;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by italowanderley on 15/02/17.
 */
@EViewGroup(R.layout.item_view_movie)
public class MovieItemView extends LinearLayout implements View.OnClickListener {

    @ViewById
    protected CustomTextView mMovieOriginalTitle, mMovieTitle, mMovieGenres, mMovieVoteAverage;

    @ViewById
    protected ImageView mMovieImage;

    private MovieViewModel mMovieViewModel;

    public MovieItemView(Context context) {
        super(context);
    }

    public MovieItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MovieItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void bind(MovieViewModel movie, boolean showSeparator) {
        mMovieViewModel = movie;

        setupBackgroundColor(showSeparator);
        setupMovieImage();
        setupMovieTexts();

        setOnClickListener(this);
    }

    private void setupBackgroundColor(boolean showSeparator) {
        if (!showSeparator) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setBackgroundColor(getResources().getColor(R.color.item_separator, getContext().getTheme()));
        } else {
            setBackgroundColor(getResources().getColor(R.color.item_separator));
        }
    }

    private void setupMovieImage() {
        ImagesConfig imagesConfig = BaseApplication.imagesConfig;
        if (imagesConfig != null) {
            Picasso.with(getContext()).load(imagesConfig.getBaseUrl() + "w500" + mMovieViewModel.getPosterPath()).into(mMovieImage);
        }
    }

    private void setupMovieTexts() {
        setText(mMovieTitle, mMovieViewModel.getTitle());
        setText(mMovieOriginalTitle, mMovieViewModel.getOriginalTitle());
        setText(mMovieGenres, mMovieViewModel.getGenres());
        setText(mMovieVoteAverage, mMovieViewModel.getVoteAverage());
    }

    @Override
    public void onClick(View view) {
        MovieDetailActivity_.intent(getContext())
                .extra("mMovieViewModel", mMovieViewModel)
                .start();
    }

    protected void setText(TextView view, String content) {
        if (content == null) return;
        view.setText(content);
    }
}
