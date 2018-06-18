package com.nikunj.flickr.ui.flickrpictures;

import com.nikunj.flickr.data.model.Photo;
import com.nikunj.flickr.ui.base.View;

import java.util.List;

/**
 * Created by nikunj on 6/6/18.
 */
public interface FlickrPhotosView extends View {

    void updatePhotos(List<Photo> photos);

    void showErrorMessage(int messageId);
}
