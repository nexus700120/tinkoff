package ru.tinkoff.data;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import retrofit2.Response;
import ru.tinkoff.api.TinkoffApi;
import ru.tinkoff.api.TinkoffService;
import ru.tinkoff.data.exception.*;

/**
 * Created by Vitaly on 07.12.2017.
 */

public class NetworkInteractor {

    protected <T> Single<T> wrap(Single<Response<T>> single) {
        return single.flatMap(tResponse -> {
            T body = tResponse.body();
            if (body != null && tResponse.isSuccessful()) {
                return Single.just(body);
            }
            return Single.error(new UnknownException());
        }).onErrorResumeNext(throwable -> Single.error(clarifyError(throwable)));
    }

    private Throwable clarifyError(Throwable t) {
        if (t == null) {
            return new UnknownException();
        }
        if (t instanceof IOException) {
            return new InternetException();
        }
        return new UnknownException();
    }

    protected TinkoffService service() {
        return TinkoffApi.getInstance().getService();
    }
}
