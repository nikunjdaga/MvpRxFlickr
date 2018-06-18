package com.nikunj.flickr.utils;

/**
 * Created by nikunj on 6/6/18.
 */
public class Utils {

    public static int getInfoPosition(int position) {

        if(position%2==0) {
            return position+2;
        } else {
            return position+1;
        }
    }
}
