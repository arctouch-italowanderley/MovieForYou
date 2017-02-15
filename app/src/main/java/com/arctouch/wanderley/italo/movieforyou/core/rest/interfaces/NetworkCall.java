package com.arctouch.wanderley.italo.movieforyou.core.rest.interfaces;

import com.arctouch.wanderley.italo.movieforyou.core.constants.EventTypes;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by italowanderley on 14/02/17.
 */

public abstract class NetworkCall implements Serializable {
    public abstract void doWork() throws IOException;

    public void beforeWork() {
    }

    public void afterWork() {
    }

    public void doError() {
    }

    public abstract EventTypes requireEvent();
}
