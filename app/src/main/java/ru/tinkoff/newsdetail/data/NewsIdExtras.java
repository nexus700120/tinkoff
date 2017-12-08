package ru.tinkoff.newsdetail.data;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Vitaly on 08.12.2017.
 */

public class NewsIdExtras {

    private static final String ID = "id";

    private int mNewsId;

    public NewsIdExtras(int id) {
        mNewsId = id;
    }

    public int getNewsId() {
        return mNewsId;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(ID, mNewsId);
        return bundle;
    }

    public static NewsIdExtras fromBundle(@Nullable Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return new NewsIdExtras(-1);
        }
        return new NewsIdExtras(bundle.getInt(ID));
    }
}
