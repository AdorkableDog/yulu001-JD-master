package com.sxjs.common.base.baseadapter.util;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.os.Vibrator;
import android.view.WindowManager;

import com.sxjs.common.CommonModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by User on 2018/11/5.
 */

public class AppUtils {
    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_MIUI = "sys_miui";
    public static final String SYS_FLYME = "sys_flyme";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

    /**
     * 獲取手機系統，小米/華為/魅族
     *
     * @return
     */
    public static String getSystem() {
        String SYS = "";
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            if (prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                SYS = SYS_MIUI;//小米
            } else if (prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                    || prop.getProperty(KEY_EMUI_VERSION, null) != null
                    || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null) {
                SYS = SYS_EMUI;//华为
            } else if (getMeizuFlymeOSFlag().toLowerCase().contains("flyme")) {
                SYS = SYS_FLYME;//魅族
            }
            ;
        } catch (IOException e) {
            e.printStackTrace();
            return SYS;
        }
        return SYS;
    }

    private static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    /**
     * 设备单个的字体
     *
     * @param type 字体类型，1-Medium 2-Regular 3-Bold
     */
    public static Typeface setSingleTypeface(int type) {
        Typeface typeface = null;
        switch (type) {
            case DeviceConstant.TYPE_MEDIUM:
                typeface = CommonModule.mMedTypeface;
                break;
            case DeviceConstant.TYPE_REGULAR:
                typeface = CommonModule.mRegTypeface;
                break;
            case DeviceConstant.TYPE_BOLD:
                typeface = CommonModule.mBoldTypeface;
                break;
            case DeviceConstant.TYPE_HEAVY:
                typeface = CommonModule.mHeaTypeface;
                break;
            default:
                typeface = CommonModule.mRegTypeface;
                break;
        }
        return typeface;
    }

    /**
     * 根据手机分辨率从DP转成PX
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     * @return
     */

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    //数组转 json
    /*public static String list2Json(List list){
        JsonArray array = new JsonArray();
        Gson gson = new Gson();
        for (int i = 0; i < list.size(); i++) {
            JsonElement obj = gson.toJsonTree(list.get(i));
            array.add(obj);
        }
        return array.toString();
    }*/

    //json 转 数组
    /*public static <T> List<T> json2List(String json, Class<T> cls){
        List<T> list = new ArrayList<>();
        if (!json.equals("") && json.length() > 0) {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        }
        return list;
    }*/

    //设置透明度
    public static void bgAlpha(Activity activity, float mAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = mAlpha;
        activity.getWindow().setAttributes(lp);
    }

 /*   *//**
     * 创建二维码图片
     *
     * @param codeStr
     * @param qrWidth
     * @param qrHeight
     * @return
     *//*
    public static Bitmap createQRImage(String codeStr, int qrWidth, int qrHeight) {
        Bitmap b = null;
        try {
            //判断URL合法性
            if (codeStr == null || "".equals(codeStr) || codeStr.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(codeStr, BarcodeFormat.QR_CODE, qrWidth, qrHeight, hints);
            int[] pixels = new int[qrWidth * qrHeight];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < qrHeight; y++) {
                for (int x = 0; x < qrWidth; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * qrWidth + x] = 0xff000000;
                    } else {
                        pixels[y * qrWidth + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(qrWidth, qrHeight, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, qrWidth, 0, 0, qrWidth, qrHeight);
            b = bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return b;
    }*/

   /* private static String token;
    public static String getToken(){

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.w("AppUtils", "getInstanceId failed", task.getException());
                    return;
                }
                // Get new Instance ID token
                token = task.getResult().getToken();
                Logger.i("AppUtils", "getInstanceId token :"+token);
            }
        });
        return token;
    }*/

/*    *//**
     * 获取goolge FCM推送的开发者 ID
     * @return
     *//*
    public static String getGoogleDevId(){
        String[] idArray = FirebaseApp.getInstance().getOptions().getApplicationId().split(":");
        return idArray[1];
    }

    public static boolean isSupportGoogleService(Context context){
        GoogleApiAvailability availability = GoogleApiAvailability.getInstance();
        int requestCode = availability.isGooglePlayServicesAvailable(context);
        if (requestCode == ConnectionResult.SUCCESS){
            return true;
        }else{
            return false;
        }
    }*/

    /**
     * 创建一次性振动
     *
     * @param milliseconds 震动时长（ms）
     * @param amplitude 振动强度。这必须是1到255之间的值，或者DEFAULT_AMPLITUDE
     */

    //手机震动
    public static void vibrate(/*final Activity activity,long[] pattern,*/ long milliseconds, int amplitude){


        Vibrator vib = (Vibrator) CommonModule.mInstance.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds );
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//           VibrationEffect.createOneShot(milliseconds,amplitude);
//
//        } else {
//            Vibrator vib = (Vibrator) App.mInstance.getSystemService(Service.VIBRATOR_SERVICE);
//            vib.vibrate(milliseconds );
//        }
    }
}
