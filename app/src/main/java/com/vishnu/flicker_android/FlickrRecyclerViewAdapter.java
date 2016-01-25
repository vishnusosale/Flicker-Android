package com.vishnu.flicker_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by vishnu on 25/1/16.
 */
public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Picture> pictureList;
    private Context context;

    public FlickrRecyclerViewAdapter(List<Picture> pictureList, Context context) {
        this.pictureList = pictureList;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
