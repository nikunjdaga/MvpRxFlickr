package com.nikunj.flickr.data;

import com.nikunj.flickr.data.model.ResponseRecentPhotos;

import io.reactivex.Single;

/**
 * Created by nikunj on 6/6/18.
 */
public interface ServiceNetwork {

    Single<ResponseRecentPhotos> getPhotos(int pageNumber);
}
