package ru.tinkoff.newslist.data.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Vitaly on 08.12.2017.
 */
@Dao
public abstract class NewsItemDao {

    @Query("SELECT * FROM news_list")
    abstract Single<List<NewsItem>> getAllNews();

    @Query("DELETE FROM news_list")
    abstract void clearTable();

    @Insert
    abstract void insert(NewsItem itemDb);

    @Transaction
    void insertList(List<NewsItem> listItemDbList) {
        clearTable();
        for (NewsItem itemDb: listItemDbList) {
            insert(itemDb);
        }
    }
}
