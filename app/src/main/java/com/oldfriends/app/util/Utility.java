package com.oldfriends.app.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oldfriends.app.R;
import com.oldfriends.app.config.SysConfig;
import com.oldfriends.app.view.CustomNoticeDialog;

import java.util.Iterator;

public class Utility {
    private static long lastClickTime;

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public static void finishActivityTranslate(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_left_out);
    }

    public static void startActivitytranslate(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
    }

    public static void startActivityNormal(Activity activity, Class intentClass) {
        Intent intent = new Intent(activity, intentClass);
        activity.startActivity(intent);
    }

    public static void showNoticeDialog(Context paramContext, String paramString)
    {
        CustomNoticeDialog localCustomNoticeDialog = new CustomNoticeDialog(paramContext, R.style.MyDialog);
        localCustomNoticeDialog.show();
        localCustomNoticeDialog.setCanceledOnTouchOutside(false);
        localCustomNoticeDialog.getContentText().setText(paramString);
    }

    /**
     * 检查网络状态
     *
     * @param context
     * @return true 表示有网，false 表示无网络
     */
    public static boolean checkNetworkStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            return true;
        }
        return false;
    }

    /**
     * 检查登录状态
     *
     * @param context
     * @return true 表示已登录，false 表示未登录
     */
    public static boolean checkLoginState(Context context) {
        String userName = PreferenceManager.getDefaultSharedPreferences(context).getString(SysConfig.USER_NAME, "");
        return StringUtils.isNotBlank(userName);
    }

    /**
     * 清除用户存在本地中的数据
     */
    public static void clearUserInfo(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(SysConfig.ACCESS_TOKEN, "").apply();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(SysConfig.USER_NAME, "").apply();
    }

    /**
     * 限制暴力点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    // 是否是使用wifi
    public static boolean isCurrentNetworkStatusWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        String name = activeNetInfo.getTypeName().toLowerCase();
        if (name != null && name.startsWith("wifi")) {
            return true;
        }
        return false;
    }

//    public static boolean intentToNoNetworkActivity(Activity activity){
//        if(!checkNetworkStatus(activity)){
//            Intent intent = new Intent(activity, NoNetworkActivity.class);
//            startActivitytranslate(activity,intent);
//        }else{
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean force2Login(String errorCode,Activity activity,int requestCode){
//        String code = errorCode.split(",")[1];
//        if(StringUtils.isNotBlank(code) && code.equals("004")){
//            Intent intent = new Intent(activity, LoginActivity.class);
//            activity.startActivityForResult(intent, requestCode);
//            activity.overridePendingTransition(R.anim.activity_left_in, R.anim.activity_alpha_out);
//            return true;
//        }
//
//        return false;
//    }

    public static String getUserToken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(SysConfig.ACCESS_TOKEN, "");
    }

    /**
     * @param theString
     * @return String
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    public static void compatibleLowVersion(View view, Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

    //计算字符的个数  一个汉字=两个英文字母，一个中文标点=两个英文标点
    public static long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }


    // 检查设备是否提供摄像头
    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // 摄像头存在
            return true;
        } else {
            // 摄像头不存在
            return false;
        }
    }

    // 安全获取Camera对象实例的方法*/
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // 试图获取Camera实例
        } catch (Exception e) {
            // 摄像头不可用（正被占用或不存在）
        }
        return c; // 不可用则返回null
    }

    public static String getProcessName(Context context) {
        String processName = null;

        // ActivityManager
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;
                    break;
                }
            }

            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName;
            }

            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
