package com.arctouch.wanderley.italo.movieforyou.core.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

/**
 * Created by italo on 09/02/17.
 */

public class FontCache {
    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String fontName, Context context) {
        Typeface typeface = fontCache.get(fontName);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                printExceptionMessage(e, "FontCache");
                return null;
            }

            fontCache.put(fontName, typeface);
        }

        return typeface;
    }
}
