package com.arctouch.wanderley.italo.movieforyou.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.arctouch.wanderley.italo.movieforyou.ui.activities.BaseActivity;
import com.arctouch.wanderley.italo.movieforyou.ui.viewmodels.MovieViewModel;
import com.arctouch.wanderley.italo.movieforyou.ui.views.MovieItemView;
import com.arctouch.wanderley.italo.movieforyou.ui.views.MovieItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by italowanderley on 15/02/17.
 */
@EBean
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    @RootContext
    protected BaseActivity mActivity;

    private List<MovieViewModel> mMovies = new ArrayList<>();

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(MovieItemView_.build(mActivity));
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        MovieViewModel movieViewModel = mMovies.get(position);
        ((MovieItemView) holder.itemView).bind(movieViewModel, position % 2 == 0);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        public MovieHolder(View itemView) {
            super(itemView);
        }
    }

    public void addAll(List<MovieViewModel> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }
}
