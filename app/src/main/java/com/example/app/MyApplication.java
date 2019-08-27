package com.example.app;

import android.app.Application;
import android.graphics.Typeface;

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
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
