package ru.tinkoff.newslist.data;

import io.reactivex.Single;
import ru.tinkoff.data.NetworkInteractor;
import ru.tinkoff.newslist.domain.interactor.NewsListInteractor;
import ru.tinkoff.newslist.domain.model.NewsListResponse;

/**
 * Created by Vitaly on 07.12.2017.
 */

public class NewsListInteractorImpl extends NetworkInteractor implements NewsListInteractor {

    @Override
    public Single<NewsListResponse> getFreshNews() {
        return wrap(service().news());
    }
}
