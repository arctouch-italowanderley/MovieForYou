package com.arctouch.wanderley.italo.movieforyou.ui;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.arctouch.wanderley.italo.movieforyou.BaseTest;
import com.arctouch.wanderley.italo.movieforyou.data.Genre;
import com.arctouch.wanderley.italo.movieforyou.data.Movie;
import com.arctouch.wanderley.italo.movieforyou.robots.MovieDetailAnalyserRobot;
import com.arctouch.wanderley.italo.movieforyou.ui.activities.MovieDetailActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by italowanderley on 13/02/17.
 */
@RunWith(AndroidJUnit4.class)
public class MovieDetailFragmentTest extends BaseTest {

    @Rule
    public ActivityTestRule<MovieDetailActivity> mActivityRule = new ActivityTestRule(MovieDetailActivity.class, false, false);

    private static Movie mMovie;

    private MovieDetailActivity mActivity;

    @BeforeClass
    public static void movieDetailFragmentClassSetUpConfiguration() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre().withId(1).withName("genre 1"));
        genres.add(new Genre().withId(2).withName("genre 2"));

        mMovie = new Movie().withOriginalTitle("Filme Bacana")
                .withGenres(genres)
                .withVoteAverage(7)
                .withOverview("Esse filme eh muito bacana");
    }

    @Before
    public void movieDetailFragmentSetUpConfiguration() {
        mActivityRule.launchActivity(new Intent());

        mActivity = mActivityRule.getActivity();
    }

    @Test
    public void verifyIntegrityOfView() throws Exception {
        MovieDetailAnalyserRobot robot = new MovieDetailAnalyserRobot(mMovie);
        robot.analyseTexts();
    }
}
