package com.sxjs.common;

import android.app.Application;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2019/6/26 0026.
 */

public class CommonModule {


	public static Application mInstance;
	public static Typeface mRegTypeface, mBoldTypeface, mHeaTypeface, mMedTypeface;

	public static void init(Application application) {
		mInstance = application;
		initTypeface();
	}

	public static void initTypeface() {
		mRegTypeface = Typeface.createFromAsset(mInstance.getAssets(), "fonts/Regular.ttf");
		mBoldTypeface = Typeface.createFromAsset(mInstance.getAssets(), "fonts/Bold.ttf");
		mHeaTypeface = Typeface.createFromAsset(mInstance.getAssets(), "fonts/Heavy.ttf");
		mMedTypeface = Typeface.createFromAsset(mInstance.getAssets(), "fonts/Medium.ttf");
		try {
			Field field = Typeface.class.getDeclaredField("SERIF");
			field.setAccessible(true);
			field.set(null, mRegTypeface);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}
