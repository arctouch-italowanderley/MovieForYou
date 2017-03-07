package com.arctouch.wanderley.italo.movieforyou.ui.viewmodels;

import com.arctouch.wanderley.italo.movieforyou.BaseUnitTest;
import com.arctouch.wanderley.italo.movieforyou.data.Genre;
import com.arctouch.wanderley.italo.movieforyou.data.Movie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by italowanderley on 16/02/17.
 */

public class MovieViewModelTest extends BaseUnitTest {

    @Test
    public void verifyTextsForView() throws Exception {
        String title = "title";
        String overview = "overview";
        String genre1 = "genre1";
        String genre2 = "genre2";
        float voteAverage = 3.7f;

        Movie movie = new Movie()
                .withOriginalTitle(title)
                .withGenres(new ArrayList<>(Arrays.asList(new Genre().withName(genre1), new Genre().withName(genre2))))
                .withVoteAverage(voteAverage)
                .withOverview(overview);

        MovieViewModel movieViewModel = new MovieViewModel(movie);

        assertEquals(title, movieViewModel.getOriginalTitle());
        assertEquals(genre1 + ", " + genre2, movieViewModel.getGenres());
        assertEquals(voteAverage + " / 10", movieViewModel.getVoteAverage());
        assertEquals(overview, movieViewModel.getOverview());
    }
}
