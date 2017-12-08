package ru.tinkoff.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.tinkoff.newslist.data.repository.NewsItem;
import ru.tinkoff.newslist.data.repository.NewsItemDao;

/**
 * Created by Vitaly on 08.12.2017.
 */
@Database(entities = {NewsItem.class}, version = 1)
public abstract class TinkoffDataBase extends RoomDatabase {
    public abstract NewsItemDao newsListDao();
}
