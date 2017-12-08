package ru.tinkoff.newsdetail.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vitaly on 08.12.2017.
 */

public class NewsDetailResponse {

    public @SerializedName("payload") NewsDetailPayload payload;

    public static class NewsDetailPayload {
        public @SerializedName("content") String content;
        public @SerializedName("title") NewsDetailTitle title;

        public static class NewsDetailTitle {
            public @SerializedName("text") String text;
            public @SerializedName("publicationDate") NewsDetailPublicationDate publicationDate;

            public static class NewsDetailPublicationDate {
                public @SerializedName("publicationDate") long milliseconds;
            }
        }
    }
}
