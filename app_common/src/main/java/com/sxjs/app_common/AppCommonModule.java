package com.sxjs.app_common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.leakcanary.LeakCanary;
import com.sxjs.common.GlobalAppComponent;

/**
 * @author liuxiaodong
 * @date 2018/12/1
 * @description
 */
public class AppCommonModule {
	public static void init(Application application) {

		if (LeakCanary.isInAnalyzerProcess(application)) {
			return;
		}
		if (BuildConfig.DEBUG) {
			LeakCanary.install(application);
			ARouter.openLog();     // 打印日志
			ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
		}

		ARouter.init(application);
		//CrashReport.initCrashReport(getApplicationContext(), "93f0e37549", CommonConfig.DEBUG);

		DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(application)
//                .setBaseDirectoryPath(new File(AppSettings.AppFilePathDir + "/caches"))
				.setBaseDirectoryName("rsSystemPicCache").setMaxCacheSize(200 * ByteConstants.MB)
				.setMaxCacheSizeOnLowDiskSpace(100 * ByteConstants.MB)
				.setMaxCacheSizeOnVeryLowDiskSpace(50 * ByteConstants.MB)
				.setMaxCacheSize(80 * ByteConstants.MB).build();


		ImagePipelineConfig config = ImagePipelineConfig.newBuilder(application)
				.setMainDiskCacheConfig(diskCacheConfig)
				.setResizeAndRotateEnabledForNetwork(false)//对网络图也能进行resiz处理以减少内存开销
				.setDownsampleEnabled(true)
				.build();


		Fresco.initialize(application, config);
		GlobalAppComponent.init(application);

		//平行module初始化
		ARouter.getInstance().build("/MainModule/MainModule").navigation();
		ARouter.getInstance().build("/TestModule/TestModule").navigation();
	}

	/*

	 */
	public  static  void destroy(){
		ARouter.getInstance().destroy();
	}


}
