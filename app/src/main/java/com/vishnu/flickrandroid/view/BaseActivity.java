package com.vishnu.flickrandroid.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vishnu.flickrandroid.R;

/**
 * Created by vishnu on 25/1/16.
 */
public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected Toolbar initToolBar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }
        return toolbar;
    }
}
