package com.vishnu.flickrandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishnu.flickrandroid.model.Picture;
import com.vishnu.flickrandroid.R;
import com.vishnu.flickrandroid.view.ViewHolder;

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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (null != pictureList ? pictureList.size() : 0);
    }
}
