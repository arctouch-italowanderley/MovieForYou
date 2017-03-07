package com.arctouch.wanderley.italo.movieforyou.ui.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.arctouch.wanderley.italo.movieforyou.ui.views.CustomEditText;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

/**
 * Created by italo on 09/02/17.
 */

public class CustomViewActions {
    public static ViewAction changeEditTextInputType(final int... inputTypes) {
        if (inputTypes == null || inputTypes.length == 0) {
            throw new IllegalArgumentException("inputTypes has no size");
        }

        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(CustomEditText.class);
            }

            @Override
            public String getDescription() {
                return "---";
            }

            @Override
            public void perform(UiController uiController, View view) {
                CustomEditText customEditText = (CustomEditText) view;
                int i = 0;
                int inputTypeResult = inputTypes[i++];
                while (i < inputTypes.length) {
                    inputTypeResult |= inputTypes[i++];
                }
                customEditText.setInputType(inputTypeResult);
            }
        };
    }
}
