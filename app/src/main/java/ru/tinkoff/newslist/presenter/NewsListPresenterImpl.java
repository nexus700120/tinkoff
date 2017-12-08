package ru.tinkoff.newslist.presenter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.tinkoff.data.ErrorPresenter;
import ru.tinkoff.newslist.domain.interactor.NewsListInteractor;
import ru.tinkoff.newslist.domain.model.NewsListResponse;
import ru.tinkoff.newslist.domain.repository.NewsListRepository;
import ru.tinkoff.newslist.view.NewsListView;

/**
 * Created by Vitaly on 06.12.2017.
 */

public class NewsListPresenterImpl implements NewsListPresenter {

    private final NewsListInteractor mInteractor;
    private final ErrorPresenter mErrorPresenter;
    private NewsListView mView;
    private NewsListRepository mRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public NewsListPresenterImpl(NewsListInteractor interactor,
                                 NewsListRepository repository,
                                 ErrorPresenter errorPresenter) {
        mInteractor = interactor;
        mErrorPresenter = errorPresenter;
        mRepository = repository;
    }

    @Override
    public void attachView(NewsListView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mCompositeDisposable.dispose();
        mView = null;
    }

    @Override
    public void onViewStateRestored() {
        loadNews();
    }

    private void loadNews() {
        mView.hideRefresh();
        mView.setRefreshEnabled(false);
        mView.showProgress();
        mCompositeDisposable.add(mInteractor.getFreshNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsListResponse -> {
                    mView.setRefreshEnabled(true);
                    mView.hideProgress();
                    onFreshNewsLoaded(newsListResponse.payload);
                }, this::tryGetCachedData));
    }

    private void tryGetCachedData(Throwable t) {
        mCompositeDisposable.add(mRepository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemList -> {
                    if (itemList.isEmpty()) {
                        mView.showError(mErrorPresenter.present(t));
                        return;
                    }
                    Collections.sort(itemList, (t0, t1) ->
                            Long.compare(t0.date.timestamp, t1.date.timestamp));
                    Collections.reverse(itemList);
                    mView.bind(itemList);
                    mView.setRefreshEnabled(true);
                    mView.hideProgress();
                }, throwable -> mView.showError(mErrorPresenter.present(t))));
    }

    @Override
    public void onRetry() {
        loadNews();
    }

    @Override
    public void refresh() {
        mCompositeDisposable.add(mInteractor.getFreshNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsListResponse -> {
                    mView.setRefreshEnabled(true);
                    mView.hideRefresh();
                    onFreshNewsLoaded(newsListResponse.payload);
                }, throwable -> {
                    mView.toast(mErrorPresenter.present(throwable));
                    mView.hideRefresh();
                }));
    }

    private void onFreshNewsLoaded(List<NewsListResponse.NewsListItem> itemList) {
        Collections.sort(itemList, (t0, t1) -> Long.compare(t0.date.timestamp, t1.date.timestamp));
        Collections.reverse(itemList);
        mRepository.addNews(itemList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        mView.bind(itemList);
    }
}
