package com.vishnu.flickrandroid;

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
