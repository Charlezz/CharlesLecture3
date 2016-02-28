package com.tistory.charlezz.chathead;

import android.app.Application;
import android.content.Context;

/**
 * Created by Charles on 16. 2. 28..
 */
public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
