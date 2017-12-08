package ru.tinkoff.newsdetail.router;


import android.app.Activity;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Created by Vitaly on 08.12.2017.
 */

public class NewsDetailRouterImpl implements NewsDetailRouter {

    private WeakReference<Fragment> mWeakFragmentRef;

    public NewsDetailRouterImpl(Fragment fragment) {
        mWeakFragmentRef = new WeakReference<>(fragment);
    }
    @Override
    public void close() {
        Fragment fragment = mWeakFragmentRef.get();
        if (fragment == null) {
            return;
        }
        Activity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        activity.finish();
    }
}
