package com.vishnu.flickrandroid.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnu.flickrandroid.R;

/**
 * Created by vishnu on 25/1/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    protected ImageView thumbnail;
    protected TextView title;


    public ViewHolder(View itemView) {
        super(itemView);

        this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        this.title = (TextView) itemView.findViewById(R.id.title);
    }
}
