package ru.tinkoff.api;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import ru.tinkoff.newslist.domain.model.NewsListResponse;

/**
 * Created by Vitaly on 07.12.2017.
 */

public interface TinkoffService {

    String ENDPOINT = "https://api.tinkoff.ru/";

    @GET("v1/news/")
    Single<Response<NewsListResponse>> news();
}
