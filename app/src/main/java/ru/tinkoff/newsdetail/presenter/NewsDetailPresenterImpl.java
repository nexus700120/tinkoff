package ru.tinkoff.newsdetail.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.tinkoff.data.ErrorPresenter;
import ru.tinkoff.newsdetail.domain.interactor.NewsDetailInteractor;
import ru.tinkoff.newsdetail.domain.model.NewsDetailResponse;
import ru.tinkoff.newsdetail.router.NewsDetailRouter;
import ru.tinkoff.newsdetail.view.NewsDetailView;

/**
 * Created by Vitaly on 08.12.2017.
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter {

    private NewsDetailView mView;
    private final NewsDetailInteractor mInteractor;
    private final NewsDetailRouter mRouter;
    private int mNewsId;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ErrorPresenter mErrorPresenter;

    public NewsDetailPresenterImpl(NewsDetailInteractor interactor,
                                   NewsDetailRouter router,
                                   ErrorPresenter errorPresenter) {
        mInteractor = interactor;
        mRouter = router;
        mErrorPresenter = errorPresenter;
    }

    @Override
    public void attachView(NewsDetailView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mCompositeDisposable.dispose();
        mView = null;
    }

    @Override
    public void onViewStateRestored(int newsId) {
        if (newsId == -1) {
            mRouter.close();
            return;
        }
        mNewsId = newsId;
        loadDetailNews();
    }

    @Override
    public void retry() {
        loadDetailNews();
    }

    private void loadDetailNews() {
        mView.showProgress();
        mCompositeDisposable.add(mInteractor
                .getDetailNews(mNewsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(response -> {
                    mView.hideProgress();
                    mView.bind(response);
                }, throwable -> mView.showError(mErrorPresenter.present(throwable))));
    }
}
