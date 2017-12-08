package ru.tinkoff.newsdetail.domain.interactor;

import io.reactivex.Single;
import ru.tinkoff.newsdetail.domain.model.NewsDetailResponse;

/**
 * Created by Vitaly on 08.12.2017.
 */

public interface NewsDetailInteractor {

    Single<NewsDetailResponse> getDetailNews(int newsId);
}
