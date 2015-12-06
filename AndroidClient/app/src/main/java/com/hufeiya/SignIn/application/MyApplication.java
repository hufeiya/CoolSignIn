package com.hufeiya.SignIn.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by hufeiya on 15-12-6.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}