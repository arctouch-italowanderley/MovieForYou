package com.arctouch.wanderley.italo.movieforyou;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.arctouch.wanderley.italo.movieforyou.core.utils.MessagePrinterUtil.printExceptionMessage;

/**
 * Created by italowanderley on 16/02/17.
 */

public class BaseUnitTest {
    public static String readMockedJson(final Object obj, final String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource("mock_json/" + fileName + ".json");

        try {
            return Files.toString(new File(resource.getPath()), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Could not open test assets folder mock_json/" + fileName + ".json");
        } catch (NullPointerException e) {
            throw new IllegalStateException("Something is null...");
        }
    }
}
