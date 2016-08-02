package com.fanyafeng.okhttputil.util;

import android.os.Handler;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Author： fanyafeng
 * Data： 16/8/1 20:01
 * Email: fanyafeng@live.cn
 */
public class OkHttpUtil {
    private volatile static OkHttpClient okHttpClient;
    private volatile static Handler okHttpHandler;

    private OkHttpUtil() {

    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpUtil.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient().newBuilder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }


}
