package ru.tinkoff.newslist.data.repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.tinkoff.App;
import ru.tinkoff.newslist.domain.model.NewsListResponse;
import ru.tinkoff.newslist.domain.repository.NewsListRepository;

/**
 * Created by Vitaly on 07.12.2017.
 */

public class NewsListRepositoryImpl implements NewsListRepository {

    @Override
    public Single<List<NewsListResponse.NewsListItem>> getNews() {
        return App.getDataBase().newsListDao().getAllNews().map(listItemDbs -> {
            ArrayList<NewsListResponse.NewsListItem> result = new ArrayList<>();
            for (NewsItem itemDb : listItemDbs) {
                NewsListResponse.NewsListItem item = new NewsListResponse.NewsListItem();
                item.text = itemDb.text;
                NewsListResponse.NewsListItem.NewsListItemDate dateObj =
                        new NewsListResponse.NewsListItem.NewsListItemDate();
                dateObj.timestamp = itemDb.date;
                item.date = dateObj;
                result.add(item);
            }
            return result;
        });
    }

    @Override
    public Single<Object> addNews(List<NewsListResponse.NewsListItem> itemList) {
        List<NewsItem> dbItemList = new ArrayList<>();
        for (NewsListResponse.NewsListItem item : itemList) {
            NewsItem dbItem = new NewsItem();
            dbItem.date = item.date.timestamp;
            dbItem.text = item.text;
            dbItemList.add(dbItem);
        }
        return Single.defer(() -> {
            App.getDataBase().newsListDao().insertList(dbItemList);
            return Single.just(new Object());
        });
    }
}
