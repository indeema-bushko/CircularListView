package com.indeema.circularlistview.entity;

import android.graphics.Bitmap;

/**
 * Created by Kostiantyn Bushko on 7/12/17.
 */

public class MenuItem {

    private Bitmap mImage;

    private String mTitle;

    private String mUrl;

    public void setImage(Bitmap image) {
        mImage = image;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
