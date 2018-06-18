package com.nikunj.flickr.utils;

/**
 * Created by nikunj on 6/6/18.
 */
public class Constant {

    private Constant() {}


    public static final String PHOTO_ADDRESS_PATTERN = "https://farm%s.staticflickr.com/%s/%s_%s.jpg";
    public static final String BASE_URL = "https://api.flickr.com/services/";
    public static final String REST_PATH = "rest/";
    public static final String GET_RECENT_PICTURES = "flickr.photos.getRecent";

    public static final Integer ELEMENTS_PER_PAGE_NUMBER = 10;
    public static final Integer FLICKR_CALLBACK_TYPE = 1;

    public static final String FORMAT = "json";
    public static final String API_KEY = "dcbd1fc222381baadd0e02f4406a7166";
}
