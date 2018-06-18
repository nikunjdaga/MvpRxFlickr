package com.nikunj.flickr.di;

import com.nikunj.flickr.FlickrApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by nikunj on 6/6/18.
 */
@Component(modules = {ApplicationModule.class, ApiModule.class, AndroidSupportInjectionModule.class})
@Singleton
public interface ApplicationComponent extends AndroidInjector<FlickrApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<FlickrApplication> {
    }
}
