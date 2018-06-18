package com.nikunj.flickr.di;

import com.google.gson.Gson;
import com.nikunj.flickr.data.ApiMethods;
import com.nikunj.flickr.data.ServiceNetwork;
import com.nikunj.flickr.data.ServiceNetworkImp;
import com.nikunj.flickr.utils.Constant;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    ServiceNetwork provideServiceNetwork(ServiceNetworkImp serviceNetwork) {
        return serviceNetwork;
    }

    @Provides
    @Singleton
    ApiMethods provideApiMethodsService(Retrofit retrofit) {
        return retrofit.create(ApiMethods.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(HttpUrl baseUrl,
                             Converter.Factory converterFactory,
                             CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    HttpUrl provideHttpUrl() {
        return HttpUrl.parse(Constant.BASE_URL);
    }
}
