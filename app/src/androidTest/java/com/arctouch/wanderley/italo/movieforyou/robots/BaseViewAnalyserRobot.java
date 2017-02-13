package com.arctouch.wanderley.italo.movieforyou.robots;

import android.support.test.espresso.ViewInteraction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.arctouch.wanderley.italo.movieforyou.ui.utils.CustomViewMatchers.withDrawable;
import static com.arctouch.wanderley.italo.movieforyou.ui.utils.CustomViewMatchers.withHintInTextInputLayout;
import static com.arctouch.wanderley.italo.movieforyou.ui.utils.CustomViewMatchers.withTextAndParameters;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by italowanderley on 13/02/17.
 */

public abstract class BaseViewAnalyserRobot {
    public abstract BaseViewAnalyserRobot analyseTexts();

    public abstract BaseViewAnalyserRobot analyseFields();

    public abstract BaseViewAnalyserRobot analyseButtons();

    public abstract BaseViewAnalyserRobot analyseImages();

    // DIALOG
    public void verifyIfThisDialogExists(final int titleStringId, final int msgStringId, final int buttonId, final String... extraContent) {
        onView(withTextAndParameters(titleStringId))
                .check(matches(isDisplayed()));

        onView(withTextAndParameters(msgStringId))
                .check(matches(isDisplayed()));

        onView(withTextAndParameters(buttonId)).perform(click());
    }

    // BUTTON
    public void verifyIfThisButtonExists(final int buttonId, final boolean isEnabled, final int msgStringId, final String... extraContent) {
        Matcher<View> enableMatcher = isEnabled ? isEnabled() : not(isEnabled());
        onView(withId(buttonId))
                .perform(closeSoftKeyboard(), scrollTo())
                .check(matches(allOf(isDisplayed(), enableMatcher, withTextAndParameters(msgStringId, extraContent))));
    }

    // IMAGE VIEW
    public void verifyIfThisImageViewExists(final int imageViewId, final int drawableId) {
        onView(withId(imageViewId))
                .perform(closeSoftKeyboard(), scrollTo())
                .check(matches(allOf(isDisplayed(), withDrawable(drawableId))));
    }

    // TEXT INPUT LAYOUT
    public void verifyIfThisTextInputLayoutExists(final int textInputLayoutId, final int hintStringId, final String... extraContent) {
        onView(withId(textInputLayoutId))
                .perform(closeSoftKeyboard(), scrollTo())
                .check(matches(allOf(isDisplayed(), withHintInTextInputLayout(hintStringId))));
    }

    // TEXT VIEW
    public void verifyIfThisTextViewExists(final int textViewId, final String msgString, final String... extraContent) {
        onView(withId(textViewId))
                .perform(closeSoftKeyboard(), scrollTo())
                .check(matches(allOf(isDisplayed(), withTextAndParameters(msgString, extraContent))));
    }

    public void verifyIfThisTextViewExists(final int textViewId, final int msgStringId, final String... extraContent) {
        onView(withId(textViewId))
                .perform(closeSoftKeyboard(), scrollTo())
                .check(matches(allOf(isDisplayed(), withTextAndParameters(msgStringId, extraContent))));
    }

    // NORMAL TEXT
    public void verifyIfThisTextExists(final int stringId, final String... extraContent) {
        onView(withTextAndParameters(stringId, extraContent))
                .perform(closeSoftKeyboard(), scrollTo())
                .check(matches(isDisplayed()));
    }

    public void verifyIfThisTextDoesntExists(final int stringId, final String... extraContent) {
        onView(withTextAndParameters(stringId, extraContent)).check(doesNotExist());
    }

    // GENERAL VIEW
    public void verifyIfThisViewExists(final int viewId, final boolean needToScroll) {
        ViewInteraction scrollInteraction = needToScroll ? onView(withId(viewId))
                .perform(closeSoftKeyboard(), scrollTo()) : onView(withId(viewId))
                .perform(closeSoftKeyboard());
        scrollInteraction.check(matches(isDisplayed()));
    }

    public void verifyIfThisViewDoesntExists(final int viewId) {
        onView(withId(viewId))
                .check(doesNotExist());
    }

    public void verifyIfThisViewIsInvisible(final int viewId) {
        onView(withId(viewId))
                .check(matches(not(isDisplayed())));
    }
}
