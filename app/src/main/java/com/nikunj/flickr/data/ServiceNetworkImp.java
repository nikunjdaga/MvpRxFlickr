package com.nikunj.flickr.data;

import com.nikunj.flickr.data.model.ResponseRecentPhotos;
import com.nikunj.flickr.utils.Constant;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by nikunj on 6/6/18.
 */
public class ServiceNetworkImp implements ServiceNetwork {

    private ApiMethods apiMethods;

    @Inject
    public ServiceNetworkImp(ApiMethods apiMethods) {
        this.apiMethods = apiMethods;
    }

    @Override
    public Single<ResponseRecentPhotos> getPhotos(int pageNumber) {
        return apiMethods.getPictures(
                Constant.GET_RECENT_PICTURES,
                Constant.API_KEY,
                Constant.ELEMENTS_PER_PAGE_NUMBER,
                pageNumber,
                Constant.FORMAT,
                Constant.FLICKR_CALLBACK_TYPE);
    }
}
