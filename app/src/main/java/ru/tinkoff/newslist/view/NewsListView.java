package ru.tinkoff.newslist.view;

import java.util.List;

import ru.tinkoff.newslist.domain.model.NewsListResponse;

/**
 * Created by Vitaly on 07.12.2017.
 */

public interface NewsListView {
    void showProgress();
    void hideProgress();
    void showError(String message);
    void bind(List<NewsListResponse.NewsListItem> itemList);
    void hideRefresh();
    void setRefreshEnabled(boolean enabled);
    void toast(String message);
}
