package com.vishnu.flickrandroid.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vishnu on 21/1/16.
 */
enum DownloadStatus {
    IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK
}

/*
    FlickrData connects to Flickr services api and downloads the response in JSON format.
 */
public class FlickrData {


    private final String TAG = getClass().getSimpleName();
    private String mUrl;
    private String jsonData;
    private DownloadStatus mDownloadStatus;

    public FlickrData(String url) {
        this.mUrl = url;
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    public void reset() {
        this.mDownloadStatus = DownloadStatus.IDLE;
        this.mUrl = null;
        this.jsonData = null;
    }

    public String getJsonData() {
        return jsonData;
    }

    public DownloadStatus getmDownloadStatus() {
        return mDownloadStatus;
    }

    public void ConnectAndDownloadData() {
        mDownloadStatus = DownloadStatus.PROCESSING;

        new DownloadFlickrData().execute(mUrl);
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    /*
        DownloadFlickrData will download data from Flickr Services api and returns the response
     */
    public class DownloadFlickrData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;

            if (params == null) {
                return null;
            }

            try {
                URL url = new URL(params[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();

                if (inputStream == null) {
                    return null;
                }

                StringBuffer stringBuffer = new StringBuffer();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String endOfFile;
                while ((endOfFile = bufferedReader.readLine()) != null) {
                    stringBuffer.append(endOfFile + "\n");
                }

                return stringBuffer.toString();

            } catch (IOException e) {
                Log.e(TAG, "downloadData", e);
                return null;
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "finally_block", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            jsonData = s;
            if (jsonData == null) {
                if (mUrl == null) {
                    mDownloadStatus = DownloadStatus.NOT_INITIALISED;
                } else {
                    mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
                }
            } else {
                // success
                mDownloadStatus = DownloadStatus.OK;
            }
        }
    }
}
