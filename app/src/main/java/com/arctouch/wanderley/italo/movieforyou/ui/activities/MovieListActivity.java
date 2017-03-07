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

package com.arctouch.wanderley.italo.movieforyou.ui.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.ui.adapters.MovieAdapter;
import com.arctouch.wanderley.italo.movieforyou.ui.presenters.MovieListPresenter;
import com.arctouch.wanderley.italo.movieforyou.ui.viewmodels.MovieViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

@EActivity(R.layout.activity_movie_list)
public class MovieListActivity extends BaseActivity {
    private final String TAG = MovieListActivity.class.getSimpleName();

    @Bean
    protected MovieListPresenter mPresenter;

    @Bean
    protected MovieAdapter mAdapter;

    @ViewById
    protected RecyclerView mMovieList;

    @ViewById
    protected ProgressBar mProgress;

    @AfterViews
    protected void movieListActivityAfterViews() {
        prepareRecyclerView();
        mPresenter.afterViews(prepareObserver());
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    private void prepareRecyclerView() {
        mMovieList.setHasFixedSize(true);
        mMovieList.setLayoutManager(new LinearLayoutManager(this));
        mMovieList.setAdapter(mAdapter);
        mMovieList.setNestedScrollingEnabled(false);
    }

    private DisposableSingleObserver<List<MovieViewModel>> prepareObserver() {
        return new DisposableSingleObserver<List<MovieViewModel>>() {

            @Override
            public void onSuccess(List<MovieViewModel> movieViewModelList) {
                dismissProgress();
                mAdapter.addAll(movieViewModelList);
            }

            @Override
            public void onError(Throwable t) {
                printExceptionMessage(t, TAG);

                dismissProgress();
                Toast.makeText(MovieListActivity.this, R.string.error_connection, Toast.LENGTH_LONG).show();
            }
        };
    }

    private void dismissProgress() {
        mProgress.setEnabled(false);
        mProgress.setVisibility(View.GONE);
    }
}
