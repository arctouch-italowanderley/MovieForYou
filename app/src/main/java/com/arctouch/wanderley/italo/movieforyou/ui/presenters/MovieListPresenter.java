package com.arctouch.wanderley.italo.movieforyou.ui.presenters;

import com.arctouch.wanderley.italo.movieforyou.data.Movie;
import com.arctouch.wanderley.italo.movieforyou.domain.MovieListInteractor;
import com.arctouch.wanderley.italo.movieforyou.ui.viewmodels.MovieViewModel;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

/**
 * Created by italowanderley on 16/02/17.
 */
@EBean
public class MovieListPresenter extends BasePresenter {
    private final String TAG = MovieListPresenter.class.getSimpleName();

    @Bean
    protected MovieListInteractor mInteractor;

    private DisposableSingleObserver<List<MovieViewModel>> mMovieViewModelListObserver;

    public void afterViews(final DisposableSingleObserver<List<MovieViewModel>> movieViewModelListObserver) {
        mMovieViewModelListObserver = movieViewModelListObserver;

        mInteractor.init();
        mInteractor.listMovies(prepareMovieListObserver());
    }

    public void onDestroy() {
        mInteractor.onDestroy();
        if (mMovieViewModelListObserver != null && !mMovieViewModelListObserver.isDisposed()) {
            mMovieViewModelListObserver.dispose();
        }
    }

    private void prepareAndSendViewModelToView(List<Movie> movieList) {
        Single.just(movieList)
                .map(prepareViewModelData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMovieViewModelListObserver);
    }

    private Function prepareViewModelData() {
        return new Function<List<Movie>, List<MovieViewModel>>() {
            @Override
            public List<MovieViewModel> apply(@NonNull List<Movie> movieList) throws Exception {
                List<MovieViewModel> movieViewModelList = new ArrayList<>();
                for (Movie movie : movieList) {
                    movieViewModelList.add(new MovieViewModel(movie));
                }
                return movieViewModelList;
            }
        };
    }

    private DisposableSingleObserver<List<Movie>> prepareMovieListObserver() {
        return new DisposableSingleObserver<List<Movie>>() {

            @Override
            public void onSuccess(List<Movie> movieList) {
                prepareAndSendViewModelToView(movieList);
            }

            @Override
            public void onError(Throwable t) {
                printExceptionMessage(t, TAG);
            }
        };
    }
}
