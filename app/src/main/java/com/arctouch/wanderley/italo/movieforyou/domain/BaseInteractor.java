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
import com.arctouch.wanderley.italo.movieforyou.core.rest.RestSettings;
import com.arctouch.wanderley.italo.movieforyou.data.Genre;
import com.arctouch.wanderley.italo.movieforyou.data.GenreListWrapper;
import com.arctouch.wanderley.italo.movieforyou.data.ImagesConfigWrapper;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

/**
 * Created by italowanderley on 26/02/17.
 */
@EBean
public class BaseInteractor {
    private final String TAG = BaseInteractor.class.getSimpleName();

    @Bean
    protected RestSettings mApi;

    private DisposableSingleObserver<ImagesConfigWrapper> mImagesConfigWrapperObserver;
    private DisposableSingleObserver<List<Genre>> mGenreListObserver;

    public void init() {
        mImagesConfigWrapperObserver = prepareImagesConfigObserver();
        mGenreListObserver = prepareGenreListObserver();

        mApi.getAnonymousClient()
                .getCurrentImageUrlConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(mImagesConfigWrapperObserver);

        mApi.getAnonymousClient()
                .getGenreList()
                .map(prepareGenreData())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(mGenreListObserver);
    }

    public void onDestroy() {
        disposeObserver(mImagesConfigWrapperObserver);
        disposeObserver(mGenreListObserver);
    }

    public void disposeObserver(Disposable observer) {
        if (observer != null && !observer.isDisposed()) {
            observer.dispose();
        }
    }

    private DisposableSingleObserver<ImagesConfigWrapper> prepareImagesConfigObserver() {
        return new DisposableSingleObserver<ImagesConfigWrapper>() {

            @Override
            public void onSuccess(ImagesConfigWrapper imagesConfigWrapper) {
                BaseApplication.imagesConfig = imagesConfigWrapper.getImagesConfig();
            }

            @Override
            public void onError(Throwable t) {
                printExceptionMessage(t, TAG);
            }
        };
    }

    private Function prepareGenreData() {
        return new Function<GenreListWrapper, List<Genre>>() {
            @Override
            public List<Genre> apply(@NonNull GenreListWrapper genreListWrapper) throws Exception {
                return genreListWrapper.getResults();
            }
        };
    }

    private DisposableSingleObserver<List<Genre>> prepareGenreListObserver() {
        return new DisposableSingleObserver<List<Genre>>() {

            @Override
            public void onSuccess(List<Genre> genreList) {
                Map<Integer, String> genreMap = new HashMap<>();
                for (Genre genre : genreList) {
                    genreMap.put(genre.getId(), genre.getName());
                }

                BaseApplication.genreMap = genreMap;
            }

            @Override
            public void onError(Throwable t) {
                printExceptionMessage(t, TAG);
            }
        };
    }
}
