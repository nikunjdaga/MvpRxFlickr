package com.nikunj.flickr.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikunj on 6/6/18.
 */
public class Photos {

    private Integer page;
    private Integer pages;
    private Integer perpage;
    private Integer total;
    @SerializedName("photo")
    private List<Photo> photos = null;

    public Integer getPage() {
        return page;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}