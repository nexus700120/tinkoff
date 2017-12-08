package ru.tinkoff.newslist.router;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

import ru.tinkoff.NewsDetailActivity;
import ru.tinkoff.newsdetail.data.NewsIdExtras;

/**
 * Created by Vitaly on 07.12.2017.
 */

public class NewsListRouterImpl implements NewsListRouter {

    private WeakReference<Fragment> mFragmentWeakReference;

    public NewsListRouterImpl(Fragment fragment) {
        mFragmentWeakReference = new WeakReference<>(fragment);
    }

    @Override
    public void openDetailPage(int newsId) {
        Fragment fragment = mFragmentWeakReference.get();
        if (fragment == null) {
            return;
        }
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, NewsDetailActivity.class);
        intent.putExtras(new NewsIdExtras(newsId).toBundle());
        activity.startActivity(intent);

    }
}
