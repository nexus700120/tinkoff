package ru.tinkoff.newslist.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import ru.tinkoff.App;
import ru.tinkoff.R;
import ru.tinkoff.data.ErrorPresenterImpl;
import ru.tinkoff.newslist.data.NewsListInteractorImpl;
import ru.tinkoff.newslist.data.OpenDetailPageEvent;
import ru.tinkoff.newslist.data.repository.NewsListRepositoryImpl;
import ru.tinkoff.newslist.domain.model.NewsListResponse;
import ru.tinkoff.newslist.presenter.NewsListPresenter;
import ru.tinkoff.newslist.presenter.NewsListPresenterImpl;
import ru.tinkoff.newslist.router.NewsListRouterImpl;
import ru.tinkoff.widget.ProgressView;

/**
 * Created by Vitaly on 06.12.2017.
 */

public class NewsListFragmnet extends Fragment implements NewsListView {

    private final NewsListPresenter mPresenter = new NewsListPresenterImpl(
            new NewsListInteractorImpl(),
            new NewsListRepositoryImpl(),
            new NewsListRouterImpl(this),
            new ErrorPresenterImpl(App.getAppContext()));

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressView mProgressView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view;
        mSwipeRefreshLayout.setOnRefreshListener(mPresenter::refresh);
        mProgressView = view.findViewById(R.id.progress_view);
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(new NewsListAdapter());
        mPresenter.attachView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onItemClicked(OpenDetailPageEvent event) {
        mPresenter.onItemClicked(event.getItem().id);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mPresenter.onViewStateRestored();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
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
        mProgressView.error(message, mPresenter::onRetry);
    }

    @Override
    public void bind(List<NewsListResponse.NewsListItem> itemList) {
        NewsListAdapter adapter = (NewsListAdapter) mRecyclerView.getAdapter();
        adapter.setItems(itemList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setRefreshEnabled(boolean enabled) {
        mSwipeRefreshLayout.setEnabled(enabled);
    }

    @Override
    public void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
