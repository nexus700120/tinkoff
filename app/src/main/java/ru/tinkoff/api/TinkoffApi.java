package ru.tinkoff.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vitaly on 07.12.2017.
 */

public class TinkoffApi {

    private static TinkoffApi sInstance;

    private final OkHttpClient mClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();

    private final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(TinkoffService.ENDPOINT)
            .client(mClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private final TinkoffService mService = mRetrofit.create(TinkoffService.class);

    private TinkoffApi() {}

    public static TinkoffApi getInstance() {
        if (sInstance == null) {
            sInstance = new TinkoffApi();
        }
        return sInstance;
    }

    public TinkoffService getService() {
        return mService;
    }
}
