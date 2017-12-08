package ru.tinkoff.newsdetail.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.tinkoff.App;
import ru.tinkoff.R;
import ru.tinkoff.data.ErrorPresenterImpl;
import ru.tinkoff.newsdetail.data.NewsDetailInteractorImpl;
import ru.tinkoff.newsdetail.data.NewsIdExtras;
import ru.tinkoff.newsdetail.domain.model.NewsDetailResponse;
import ru.tinkoff.newsdetail.presenter.NewsDetailPresenter;
import ru.tinkoff.newsdetail.presenter.NewsDetailPresenterImpl;
import ru.tinkoff.newsdetail.router.NewsDetailRouterImpl;
import ru.tinkoff.util.DateFormatter;
import ru.tinkoff.util.HtmlHelper;
import ru.tinkoff.widget.ProgressView;

/**
 * Created by Vitaly on 08.12.2017.
 */
public class NewsDetailFragment extends Fragment implements NewsDetailView {

    private final NewsDetailPresenter mPresenter = new NewsDetailPresenterImpl(
            new NewsDetailInteractorImpl(),
            new NewsDetailRouterImpl(this),
            new ErrorPresenterImpl(App.getAppContext()));

    private TextView mDateTextView;
    private TextView mTextView;
    private ProgressView mProgressView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDateTextView = view.findViewById(R.id.date);
        mProgressView = (ProgressView) view;
        mTextView = view.findViewById(R.id.text);
        mPresenter.attachView(this);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mPresenter.onViewStateRestored(NewsIdExtras.fromBundle(getArguments()).getNewsId());
    }

    @Override
    public void showProgress() {
        mProgressView.showProgress();
    }

    @Override
    public void hideProgress() {
        mProgressView.hideProgress();
    }

    @Override
    public void showError(String message) {
        mProgressView.error(message, mPresenter::retry);
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void bind(NewsDetailResponse response) {
        if (response.payload == null) {
            return;
        }
        if (response.payload.content != null && !response.payload.content.isEmpty()) {
            HtmlHelper.setTextViewHTML(mTextView, response.payload.content);
        }

        if (response.payload.title != null) {
            if (response.payload.title.text != null &&
                    !response.payload.title.text.isEmpty()) {
                Activity activity = getActivity();
                if (activity != null) {
                    activity.setTitle(response.payload.title.text);
                }
            }

            if (response.payload.title.publicationDate != null &&
                    response.payload.title.publicationDate.milliseconds != 0) {
                mDateTextView.setText(DateFormatter
                        .formatTimeStamp(response.payload.title.publicationDate.milliseconds));
            }
        }
    }
}
