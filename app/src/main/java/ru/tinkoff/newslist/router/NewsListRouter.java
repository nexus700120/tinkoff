package ru.tinkoff.newslist.router;

import ru.tinkoff.newslist.domain.model.NewsListResponse;

/**
 * Created by Vitaly on 07.12.2017.
 */

public interface NewsListRouter {
    void openDetailPage(NewsListResponse.NewsListItem item);
}
