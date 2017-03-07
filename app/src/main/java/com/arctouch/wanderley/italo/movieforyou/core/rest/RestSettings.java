package com.arctouch.wanderley.italo.movieforyou.core.rest;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.core.rest.interfaces.AnonymousMovieDBApiService;
import com.arctouch.wanderley.italo.movieforyou.core.rest.interfaces.AuthenticatedMovieDBApiService;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by italowanderley on 14/02/17.
 */
@EBean
public class RestSettings {

    @StringRes(R.string.m_api_url)
    protected String mApiUrl;

    @StringRes(R.string.m_api_key)
    protected String mApiKey;

    private AnonymousMovieDBApiService mAnonymousMovieDBApiService;
    private RestClient<AnonymousMovieDBApiService> mAnonymousMovieDBClient;
    private AuthenticatedMovieDBApiService mAuthenticatedMovieDBService;
    private RestClient<AuthenticatedMovieDBApiService> mAuthenticatedMovieDBClient;
    private boolean mIsTestingOn = false;

    @AfterInject
    protected void restSettingsAfterInject() {
        mAnonymousMovieDBClient = RestClient.getInstance(mApiUrl, mApiKey);
        mAnonymousMovieDBApiService = mAnonymousMovieDBClient.getService(AnonymousMovieDBApiService.class);

        mAuthenticatedMovieDBClient = RestClient.getInstance(mApiUrl, mApiKey);
        mAuthenticatedMovieDBService = mAuthenticatedMovieDBClient.getService(AuthenticatedMovieDBApiService.class);
    }

    public AnonymousMovieDBApiService getAnonymousClient() {
        return mAnonymousMovieDBApiService;
    }

    public AuthenticatedMovieDBApiService getAuthenticatedClient() {
        return mAuthenticatedMovieDBService;
    }

    public boolean isTestingOn() {
        return mIsTestingOn;
    }

    public void setTestingOn(boolean isTesting) {
        mIsTestingOn = isTesting;
    }
}
