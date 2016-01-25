package com.vishnu.flickrandroid.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.vishnu.flickrandroid.model.Picture;
import com.vishnu.flickrandroid.R;
import com.vishnu.flickrandroid.adapter.FlickrRecyclerViewAdapter;
import com.vishnu.flickrandroid.network.FlickrJsonDataParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    final String flickrAPIEndPoint =
            "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1";
    private final String TAG = getClass().getSimpleName();
    RecyclerView mRecyclerView;
    FlickrRecyclerViewAdapter flickrRecyclerViewAdapter;
    private List<Picture> pictureList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //FlickrData flickrData = new FlickrData(flickrAPIEndPoint);
        //flickrData.ConnectAndDownloadData();

        FlickrJsonDataParser flickrJsonDataParser = new FlickrJsonDataParser("android,lollipop", true);
        flickrJsonDataParser.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class picturesProcessor extends FlickrJsonDataParser {

        public picturesProcessor(String searchCriteria, boolean matchAll) {
            super(searchCriteria, matchAll);
        }

        @Override
        public void execute() {
            super.execute();
            new ProcessData().execute();
        }

        private class ProcessData extends DownloadJsonData {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                flickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(getPictureList(), MainActivity.this);
                mRecyclerView.setAdapter(flickrRecyclerViewAdapter);
            }
        }
    }


}
