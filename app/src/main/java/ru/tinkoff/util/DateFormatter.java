package ru.tinkoff.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vitaly on 07.12.2017.
 */

public class DateFormatter {

    @SuppressLint("SimpleDateFormat")
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");

    public static String formatTimeStamp(long timestamp) {
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }
}
