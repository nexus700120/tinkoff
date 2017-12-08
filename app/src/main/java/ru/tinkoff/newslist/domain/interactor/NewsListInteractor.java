package ru.tinkoff.newslist.domain.interactor;

import io.reactivex.Single;
import ru.tinkoff.newslist.domain.model.NewsListResponse;

/**
 * Created by Vitaly on 07.12.2017.
 */

public interface NewsListInteractor {
    Single<NewsListResponse> getFreshNews();
}
