package com.nikunj.flickr.di;

import android.content.Context;

import com.nikunj.flickr.FlickrApplication;
import com.nikunj.flickr.data.RepositoryManager;
import com.nikunj.flickr.data.RepositoryManagerImpl;
import com.nikunj.flickr.ui.flickrpictures.FlickrPhotosActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by nikunj on 6/6/18.
 */
@Module
public abstract class ApplicationModule {

    @Singleton
    @Provides
    public static Context provideContext() {
        return FlickrApplication.getContext();
    }

    @Singleton
    @Provides
    public static RepositoryManager provideRepositoryManager(RepositoryManagerImpl repositoryManager) {
        return repositoryManager;
    }

    @ContributesAndroidInjector
    abstract FlickrPhotosActivity componentFlickrPicturesInjector();
}
