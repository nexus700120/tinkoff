package ru.tinkoff.newslist.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vitaly on 06.12.2017.
 */

public class NewsListResponse {

    public @SerializedName("payload") List<NewsListItem> payload;

    public static class NewsListItem {
        public @SerializedName("text") String text;
        public @SerializedName("publicationDate") NewsListItemDate date;

        public static class NewsListItemDate {
            public  @SerializedName("milliseconds") long timestamp;
        }
    }
}
