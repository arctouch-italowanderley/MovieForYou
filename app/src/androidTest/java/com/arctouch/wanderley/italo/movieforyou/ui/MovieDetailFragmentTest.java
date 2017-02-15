package com.arctouch.wanderley.italo.movieforyou.ui;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.arctouch.wanderley.italo.movieforyou.BaseTest;
import com.arctouch.wanderley.italo.movieforyou.data.Movie;
import com.arctouch.wanderley.italo.movieforyou.robots.MovieDetailAnalyserRobot;
import com.arctouch.wanderley.italo.movieforyou.ui.activities.MovieDetailActivity;
import com.arctouch.wanderley.italo.movieforyou.ui.activities.MovieDetailActivity_;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by italowanderley on 13/02/17.
 */
@RunWith(AndroidJUnit4.class)
public class MovieDetailFragmentTest extends BaseTest {

    @Rule
    public ActivityTestRule<MovieDetailActivity_> mActivityRule = new ActivityTestRule(MovieDetailActivity_.class, false, false);

    private static Movie mMovie;

    private MovieDetailActivity mActivity;

    @BeforeClass
    public static void movieDetailFragmentClassSetUpConfiguration() {
        mMovie = new Gson().fromJson(readMockedJson("movie_mock"), Movie.class);
    }

    @Before
    public void movieDetailFragmentSetUpConfiguration() {
        mActivityRule.launchActivity(new Intent().putExtra("mMovie", mMovie));
        mActivity = mActivityRule.getActivity();
    }

    @Test
    public void verifyIntegrityOfView() throws Exception {
        // TODO: add here a more appropriated verification for the portrait layout as landscape layout
        MovieDetailAnalyserRobot robot = new MovieDetailAnalyserRobot(mMovie);
        robot.analyseTexts();

        rotateToLandscape(mActivity);

        robot.analyseTexts();
    }
}
