package com.arctouch.wanderley.italo.movieforyou.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.arctouch.wanderley.italo.movieforyou.ui.activities.BaseActivity;

import org.androidannotations.annotations.EFragment;

/**
 * Created by italowanderley on 13/02/17.
 */
@EFragment
public class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    protected void setText(TextView view, String content) {
        if (content == null) return;
        view.setText(content);
    }
}
