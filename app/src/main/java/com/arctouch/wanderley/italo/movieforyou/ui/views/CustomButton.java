package com.arctouch.wanderley.italo.movieforyou.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.core.constants.ButtonStyles;
import com.arctouch.wanderley.italo.movieforyou.core.utils.FontCache;


/**
 * Created by italo on 09/02/17.
 */

public class CustomButton extends Button {
    public CustomButton(Context context) {
        super(context);
        init(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        String buttonStyle = ButtonStyles.DEFAULT_INACTIVE.name();

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        if (typedArray.hasValue(R.styleable.CustomButton_style_button)) {
            buttonStyle = typedArray.getString(R.styleable.CustomButton_style_button);
        }
        ButtonStyles.valueOf(buttonStyle).defineStyle(context, this);

        String height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");
        if (height.equals(ViewGroup.LayoutParams.WRAP_CONTENT)) {
            final int dimension = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
            setHeight(dimension);
        }

        setAllCaps(false);
        final Typeface customFont = FontCache.getTypeface("fonts/WorkSans-SemiBold.ttf", context);
        setTypeface(customFont);
    }
}
