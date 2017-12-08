package ru.tinkoff.newslist.domain.repository;

import java.util.List;

import io.reactivex.Single;
import ru.tinkoff.newslist.domain.model.NewsListResponse;

/**
 * Created by Vitaly on 07.12.2017.
 */

public interface NewsListRepository {
    Single<List<NewsListResponse.NewsListItem>> getNews();
    Single<Object> addNews(List<NewsListResponse.NewsListItem> itemList);
}
