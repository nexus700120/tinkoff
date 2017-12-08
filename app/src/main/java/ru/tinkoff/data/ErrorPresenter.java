package ru.tinkoff.data;

/**
 * Created by Vitaly on 07.12.2017.
 */

public interface ErrorPresenter {

    String present(Throwable t);
}
