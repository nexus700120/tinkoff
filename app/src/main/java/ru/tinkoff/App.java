package ru.tinkoff;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import ru.tinkoff.db.TinkoffDataBase;

/**
 * Created by Vitaly on 07.12.2017.
 */

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;
    private static TinkoffDataBase sDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        sDataBase = Room.databaseBuilder(this, TinkoffDataBase.class, "db").build();
    }

    public static Context getAppContext() {
        return sContext;
    }

    public static TinkoffDataBase getDataBase() {
        return sDataBase;
    }
}
