package com.arctouch.wanderley.italo.movieforyou.core;

import android.app.Application;

import com.arctouch.wanderley.italo.movieforyou.data.ImagesConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by italowanderley on 13/02/17.
 */

public class BaseApplication extends Application {

    public static ImagesConfig imagesConfig;
    public static Map<Integer, String> genreMap = new HashMap<>();
}
