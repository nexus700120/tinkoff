package ru.tinkoff.newsdetail.presenter;

import ru.tinkoff.newsdetail.view.NewsDetailView;

/**
 * Created by Vitaly on 08.12.2017.
 */

public interface NewsDetailPresenter {
    void attachView(NewsDetailView view);
    void detachView();
    void onViewStateRestored(int newsId);
    void retry();
}
