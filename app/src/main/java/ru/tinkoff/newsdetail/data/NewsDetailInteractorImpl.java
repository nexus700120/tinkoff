package ru.tinkoff.newsdetail.data;

import io.reactivex.Single;
import ru.tinkoff.data.NetworkInteractor;
import ru.tinkoff.newsdetail.domain.interactor.NewsDetailInteractor;
import ru.tinkoff.newsdetail.domain.model.NewsDetailResponse;

/**
 * Created by Vitaly on 08.12.2017.
 */

public class NewsDetailInteractorImpl extends NetworkInteractor implements NewsDetailInteractor {

    @Override
    public Single<NewsDetailResponse> getDetailNews(int newsId) {
        return wrap(service().newsDetails(newsId));
    }
}
