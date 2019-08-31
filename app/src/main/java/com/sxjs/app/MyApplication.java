package com.sxjs.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.sxjs.app_common.AppCommonModule;
import com.sxjs.common.CommonModule;

/**
 * @author admin
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AppCommonModule.init(this);
        CommonModule.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
