package com.arctouch.wanderley.italo.movieforyou.core.constants;

import android.content.Context;
import android.os.Build;

import com.arctouch.wanderley.italo.movieforyou.R;
import com.arctouch.wanderley.italo.movieforyou.ui.views.CustomButton;

/**
 * Created by italowanderley on 09/02/17.
 */

public enum ButtonStyles {
    DEFAULT_INACTIVE {
        public void defineStyle(final Context context, final CustomButton button) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                button.setTextAppearance(R.style.AppTheme_Button_Default);
                button.setBackground(context.getDrawable(R.drawable.button_default_inactive));
            } else {
                button.setTextAppearance(context, R.style.AppTheme_Button_Default);
                button.setBackground(context.getResources().getDrawable(R.drawable.button_default_inactive));
            }
            button.setEnabled(false);
        }
    },
    DEFAULT {
        public void defineStyle(final Context context, final CustomButton button) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                button.setTextAppearance(R.style.AppTheme_Button_Default);
                button.setBackground(context.getDrawable(R.drawable.button_defaut_ripple));
            } else {
                button.setTextAppearance(context, R.style.AppTheme_Button_Default);
                button.setBackground(context.getResources().getDrawable(R.drawable.button_defaut_ripple));
            }
            button.setEnabled(true);
        }
    },
    BORDER {
        public void defineStyle(final Context context, final CustomButton button) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                button.setTextAppearance(R.style.AppTheme_Button_Default_Border);
                button.setBackground(context.getResources().getDrawable(R.drawable.button_border_ripple, context.getTheme()));
            } else {
                button.setTextAppearance(context, R.style.AppTheme_Button_Default_Border);
                button.setBackground(context.getResources().getDrawable(R.drawable.button_border_ripple));
            }
            button.setEnabled(true);
        }
    };

    public abstract void defineStyle(final Context context, final CustomButton button);
}
