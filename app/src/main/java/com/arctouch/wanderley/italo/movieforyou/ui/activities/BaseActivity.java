package com.arctouch.wanderley.italo.movieforyou.ui.activities;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.arctouch.wanderley.italo.movieforyou.R;

import org.androidannotations.annotations.EActivity;

/**
 * Created by italowanderley on 13/02/17.
 */
@EActivity
public class BaseActivity extends AppCompatActivity {

    protected FrameLayout mContent;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mContent = (FrameLayout) findViewById(R.id.content);

        final View view = getLayoutInflater().inflate(layoutResID, mContent, false);
        mContent.addView(view);
    }
}
