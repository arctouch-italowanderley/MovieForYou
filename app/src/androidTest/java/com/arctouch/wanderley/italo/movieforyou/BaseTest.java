package com.arctouch.wanderley.italo.movieforyou;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.io.CharStreams;
import android.support.v7.app.AppCompatActivity;

import com.arctouch.wanderley.italo.movieforyou.core.BaseApplication;

import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

/**
 * Created by italowanderley on 13/02/17.
 */

public class BaseTest {
    protected static Context mAppContext;
    protected static Context mTestContext;
    protected static BaseApplication mApp;

    @BeforeClass
    public static void baseTestSetUpConfiguration() {
        mTestContext = InstrumentationRegistry.getContext();
        mAppContext = InstrumentationRegistry.getTargetContext();
        mApp = (BaseApplication) mAppContext.getApplicationContext();
    }

    public static String readMockedJson(final String fileName) {
        try {
            final InputStream is = mTestContext.getResources().getAssets().open("mock_json/" + fileName + ".json");
            return CharStreams.toString(new InputStreamReader(is));
        } catch (IOException e) {
            printExceptionMessage(e, "Could not open test assets folder mock_json/" + fileName + ".json");
            throw new RuntimeException("Could not open test assets folder mock_json/" + fileName + ".json");
        } catch (NullPointerException e) {
            printExceptionMessage(e, "You have to see the state of the tests. Maybe mAppContext is null...");
            throw new IllegalStateException("You have to see the state of the tests. Maybe mAppContext is null...");
        }
    }

    public void rotateToLandscape(AppCompatActivity activity) {
        rotateToLandscape(activity, false);
    }

    public void rotateToLandscape(AppCompatActivity activity, boolean isReverse) {
        if (isReverse) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public void rotateToPortrait(AppCompatActivity activity) {
        rotateToPortrait(activity, false);
    }

    public void rotateToPortrait(AppCompatActivity activity, boolean isReverse) {
        if (isReverse) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
