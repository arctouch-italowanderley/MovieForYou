package com.arctouch.wanderley.italo.movieforyou.ui.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.text.MessageFormat;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

/**
 * Created by italo on 09/02/17.
 */

public class CustomViewMatchers {
    public static Matcher<View> withHintInTextInputLayout(final int expectedMsgId, final String... extraContent) {
        if (expectedMsgId <= 0) {
            throw new IllegalArgumentException("resourceId is equal to: " + expectedMsgId);
        }

        return new TypeSafeMatcher<View>() {
            private String hint;
            private String resourceName;

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) return false;

                CharSequence msg = ((TextInputLayout) view).getHint();
                if (msg == null) return false;

                hint = msg.toString();
                resourceName = view.getContext().getResources().getResourceEntryName(expectedMsgId);

                String expectedText;
                if (extraContent == null || extraContent.length == 0) {
                    expectedText = view.getContext().getString(expectedMsgId);
                } else {
                    expectedText = MessageFormat.format(view.getContext().getString(expectedMsgId), (Object[]) extraContent);
                }

                return hint.equals(expectedText);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with string from resource id: ");
                description.appendValue(expectedMsgId);
                if (resourceName != null) {
                    description.appendText("[");
                    description.appendText(resourceName);
                    description.appendText("]");
                }
                if (hint != null) {
                    description.appendText("value: ");
                    description.appendText(hint);
                }
            }
        };
    }

    public static Matcher<View> withDrawable(final int expectedDrawableId) {
        return new TypeSafeMatcher<View>() {
            private String resourceName;

            @Override
            protected boolean matchesSafely(View target) {
                if (!(target instanceof ImageView)) return false;

                ImageView imageView = (ImageView) target;
                if (expectedDrawableId < 0) return imageView.getDrawable() == null;

                Resources resources = target.getContext().getResources();
                Drawable expectedDrawable;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    expectedDrawable = resources.getDrawable(expectedDrawableId, target.getContext().getTheme());
                } else {
                    expectedDrawable = resources.getDrawable(expectedDrawableId);
                }
                if (expectedDrawable == null) return false;

                resourceName = resources.getResourceEntryName(expectedDrawableId);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                Bitmap otherBitmap = ((BitmapDrawable) expectedDrawable).getBitmap();
                return bitmap.sameAs(otherBitmap);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with drawable from resource id: ");
                description.appendValue(expectedDrawableId);
                if (resourceName != null) {
                    description.appendText("[");
                    description.appendText(resourceName);
                    description.appendText("]");
                }
            }
        };
    }

    public static Matcher<View> withTextAndParameters(final String resource, final String... extraContent) {
        return new TypeSafeMatcher<View>() {
            private String expectedText = null;

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView)) return false;

                try {
                    expectedText = prepareStringForComparison(resource, extraContent);
                } catch (Resources.NotFoundException e) {
                    printExceptionMessage(e, "CustomViewMatchers");
                }

                CharSequence actualText = ((TextView) view).getText();
                if (null != expectedText && null != actualText) {
                    return expectedText.equals(actualText.toString());
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with string from resource id: ");
                description.appendValue(resource);
                if (null != expectedText) {
                    description.appendText(" value: ");
                    description.appendText(expectedText);
                }
            }
        };
    }

    public static Matcher<View> withTextAndParameters(final int resourceId, final String... extraContent) {
        return new TypeSafeMatcher<View>() {
            private String resourceName = null;
            private String expectedText = null;

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView)) return false;

                try {
                    expectedText = prepareStringForComparison(view.getResources().getString(resourceId), extraContent);
                    resourceName = view.getResources().getResourceEntryName(resourceId);
                } catch (Resources.NotFoundException e) {
                    printExceptionMessage(e, "CustomViewMatchers");
                }

                CharSequence actualText = ((TextView) view).getText();
                if (null != expectedText && null != actualText) {
                    return expectedText.equals(actualText.toString());
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with string from resource id: ");
                description.appendValue(resourceId);
                if (null != resourceName) {
                    description.appendText("[");
                    description.appendText(resourceName);
                    description.appendText("]");
                }
                if (null != expectedText) {
                    description.appendText(" value: ");
                    description.appendText(expectedText);
                }
            }
        };
    }

    private static String prepareStringForComparison(String interestingStr, String... extraContent) {
        String expectedText = interestingStr.replaceAll("(?i)<br *?/>", "\n").replaceAll("<.*?>", "");
        return MessageFormat.format(expectedText, (Object[]) extraContent);
    }
}
