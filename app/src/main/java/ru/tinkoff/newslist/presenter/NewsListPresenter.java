package ru.tinkoff.newslist.presenter;

import ru.tinkoff.newslist.view.NewsListView;

/**
 * Created by Vitaly on 07.12.2017.
 */

public interface NewsListPresenter {
    void attachView(NewsListView view);
    void detachView();
    void onViewStateRestored();
    void onRetry();
    void refresh();
}
