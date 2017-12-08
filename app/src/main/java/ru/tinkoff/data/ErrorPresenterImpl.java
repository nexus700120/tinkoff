package ru.tinkoff.data;

import android.content.Context;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import ru.tinkoff.R;
import ru.tinkoff.data.exception.InternetException;
import ru.tinkoff.data.exception.UnknownException;

/**
 * Created by Vitaly on 07.12.2017.
 */

public class ErrorPresenterImpl implements ErrorPresenter {

    private WeakReference<Context> mRefContext;

    public ErrorPresenterImpl(Context context) {
        mRefContext = new WeakReference<>(context);
    }
    @Override
    public String present(@Nullable Throwable t) {
        Context context = mRefContext.get();
        if (context == null) {
            return "";
        }
        if (t == null || t.getClass() == UnknownException.class) {
            return context.getString(R.string.common_error_unknown);
        }
        if (t.getClass() == InternetException.class) {
            return context.getString(R.string.common_error_internet);
        }
        return context.getString(R.string.common_error_unknown);
    }
}
