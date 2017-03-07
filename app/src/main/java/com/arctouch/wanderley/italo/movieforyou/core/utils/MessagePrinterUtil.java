package com.arctouch.wanderley.italo.movieforyou.core.utils;

import android.util.Log;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * Created by italo on 09/02/17.
 */

public class MessagePrinterUtil {
    public static void printExceptionMessage(Throwable t, String tag) {
        if (t instanceof UndeclaredThrowableException) {
            Log.e(tag, t.getCause().getMessage(), t.getCause());
        } else {
            Log.e(tag, t.getMessage(), t);
        }
    }
}
