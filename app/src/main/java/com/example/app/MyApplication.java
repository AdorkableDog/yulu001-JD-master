package com.example.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;

import com.example.app_common.AppCommonModule;
import com.sxjs.common.CommonModule;

import java.lang.reflect.Field;

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
}
