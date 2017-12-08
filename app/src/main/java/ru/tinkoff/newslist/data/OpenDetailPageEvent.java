package ru.tinkoff.newslist.data;

import ru.tinkoff.newslist.domain.model.NewsListResponse;

/**
 * Created by Vitaly on 08.12.2017.
 */

public class OpenDetailPageEvent {

    private NewsListResponse.NewsListItem mItem;

    public OpenDetailPageEvent(NewsListResponse.NewsListItem item) {
        mItem = item;
    }

    public NewsListResponse.NewsListItem getItem() {
        return mItem;
    }
}
