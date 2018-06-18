package com.nikunj.flickr.data;

import com.nikunj.flickr.data.model.ResponseRecentPhotos;
import com.nikunj.flickr.utils.Constant;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nikunj on 6/6/18.
 */
public interface ApiMethods {

    @GET(Constant.REST_PATH)
    Single<ResponseRecentPhotos> getPictures(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("per_page") Integer perPage,
            @Query("page") Integer page,
            @Query("format") String format,
            @Query("nojsoncallback") Integer callbackType
    );
}
