package com.arctouch.wanderley.italo.movieforyou.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.core.utils.FontCache;


/**
 * Created by italo on 09/02/17.
 */

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        applyCustomFont(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        Typeface customFont = FontCache.getTypeface("fonts/WorkSans-Regular.ttf", context);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
            if (ta != null) {
                String pathFont = ta.getString(R.styleable.CustomTextView_font);
                if (pathFont != null && !pathFont.isEmpty()) {
                    customFont = FontCache.getTypeface("fonts/".concat(pathFont), context);
                }
            }
        }
        setTypeface(customFont);

        String color = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textColor");
        if (color == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setTextColor(getResources().getColor(R.color.text_color, context.getTheme()));
            } else {
                setTextColor(getResources().getColor(R.color.text_color));
            }
        }

        String textSize = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textSize");
        if (textSize == null) {
            setTextSize(getResources().getDimension(R.dimen.text_normal_size));
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            super.setText(Html.fromHtml(text.toString(), Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH), type);
        } else {
            super.setText(Html.fromHtml(text.toString()), type);
        }
    }
}
