package com.android.monews.utils;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/7/19 0019.
 *
 */
public class OKHTTP {
    public static OKHTTP okhttp;
    public static OkHttpClient okHttpClient;

    private OKHTTP() {
        okHttpClient = new OkHttpClient();
    }

    public static OKHTTP getOkHttp() {
        if (okhttp == null) {
            synchronized (OKHTTP.class) {
                if (okhttp == null) {
                    okhttp = new OKHTTP();
                }
            }
        }
        return okhttp;
    }

}
