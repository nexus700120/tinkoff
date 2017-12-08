package ru.tinkoff.newslist.data.repository;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Vitaly on 08.12.2017.
 */

@Entity(tableName = "news_list")
public class NewsItem {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "date")
    public long date;
}
