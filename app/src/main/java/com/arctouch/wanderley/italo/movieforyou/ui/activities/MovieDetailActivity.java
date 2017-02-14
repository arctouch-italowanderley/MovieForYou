package com.arctouch.wanderley.italo.movieforyou.ui.activities;

import android.support.v4.app.FragmentManager;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.data.Movie;
import com.arctouch.wanderley.italo.movieforyou.ui.fragments.MovieDetailFragment;
import com.arctouch.wanderley.italo.movieforyou.ui.fragments.MovieDetailFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;

/**
 * Created by italowanderley on 09/02/17.
 */
@EActivity(R.layout.activity_movie_detail)
public class MovieDetailActivity extends BaseActivity {

    @Extra
    @InstanceState
    protected Movie mMovie;

    @InstanceState
    protected String mFragmentTag;

    protected MovieDetailFragment mFragment;

    @AfterViews
    protected void movieDetailActivityAfterViews() {
        verifyIfFragmentAlreadyExists();
        showFragmentContent();
    }

    private void verifyIfFragmentAlreadyExists() {
        FragmentManager fm = getSupportFragmentManager();
        mFragment = (MovieDetailFragment) fm.findFragmentByTag(mFragmentTag);
        if (mFragment == null) {
            mFragment = MovieDetailFragment_.builder().build();
        }

        mFragmentTag = mFragment.getClass().getSimpleName();
    }

    private void showFragmentContent() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mFragment, mFragmentTag)
                .commitNow();
    }

    public Movie getMovie() {
        return mMovie;
    }
}
