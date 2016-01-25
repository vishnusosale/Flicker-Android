package com.vishnu.flicker_android;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by vishnu on 25/1/16.
 */
public class FlickrApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
    }
}
