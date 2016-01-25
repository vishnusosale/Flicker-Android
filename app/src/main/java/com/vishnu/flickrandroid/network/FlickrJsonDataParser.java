package com.vishnu.flickrandroid.network;

import android.net.Uri;
import android.util.Log;

import com.vishnu.flickrandroid.model.Picture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishnu on 22/1/16.
 */
public class FlickrJsonDataParser extends FlickrData {

    private final String TAG = getClass().getSimpleName();
    private List<Picture> pictureList;
    /*
        Uniform Resource Identifier is used create a valid URL
     */
    private Uri destinationUri;

    public FlickrJsonDataParser(String searchCriteria, boolean matchAll) {
        super(null);
        pictureList = new ArrayList<>();
        createAndUpdateUri(searchCriteria, matchAll);
    }

    public boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {

        final String FLICKR_BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne";
        final String TAGS = "tags";
        final String TAG_MODE = "tagmode";
        final String FORMAT = "format";
        final String JSON_CALLBACK = "nojsoncallback";

        destinationUri = Uri.parse(FLICKR_BASE_URL).buildUpon()
                .appendQueryParameter(TAGS, searchCriteria)
                .appendQueryParameter(TAG_MODE, matchAll ? "ALL" : "ANY")
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(JSON_CALLBACK, "1")
                .build();

        return destinationUri != null;
    }

    public void execute() {
        setmUrl(destinationUri.toString());
        new DownloadJsonData().execute(destinationUri.toString());
    }

    public void processResults() {
        if (getmDownloadStatus() != DownloadStatus.OK) {
            Log.e(TAG, "Did not download data");
            return;
        }

        final String FLICKR_ITEMS = "items";
        final String FLICKR_TITLE = "title";
        final String FLICKR_MEDIA = "media";
        final String FLICKR_PHOTO_URL = "m";
        final String FLICKR_AUTHOR = "author";
        final String FLICKR_AUTHOR_ID = "author_id";
        final String FLICKR_TAGS = "tags";
        final String FLICKR_LINK = "link";


        try {
            JSONObject jsonObject = new JSONObject(getJsonData());
            JSONArray jsonArray = jsonObject.getJSONArray(FLICKR_ITEMS);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonPicture = jsonArray.getJSONObject(i);
                String title = jsonPicture.getString(FLICKR_TITLE);
                String author = jsonPicture.getString(FLICKR_AUTHOR);
                String authorId = jsonPicture.getString(FLICKR_AUTHOR_ID);
                String tags = jsonPicture.getString(FLICKR_TAGS);
                String link = jsonPicture.getString(FLICKR_LINK);

                JSONObject jsonMedia = jsonPicture.getJSONObject(FLICKR_MEDIA);
                String photoUrl = jsonMedia.getString(FLICKR_PHOTO_URL);

                Picture picture = new Picture(title, author, authorId, link, tags, photoUrl);
                this.pictureList.add(picture);


            }
        } catch (JSONException e) {
            Log.e(TAG, "parseJson", e);
        }
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public class DownloadJsonData extends DownloadFlickrData {
        @Override
        protected String doInBackground(String... params) {
            return super.doInBackground(destinationUri.toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            processResults();
        }
    }
}

