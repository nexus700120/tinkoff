package ru.tinkoff.newsdetail.view;

import ru.tinkoff.newsdetail.domain.model.NewsDetailResponse;

/**
 * Created by Vitaly on 08.12.2017.
 */

public interface NewsDetailView {
    void showProgress();
    void hideProgress();
    void showError(String message);
    void bind(NewsDetailResponse response);
}
