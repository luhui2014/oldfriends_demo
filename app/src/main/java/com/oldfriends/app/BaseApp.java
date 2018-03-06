package com.oldfriends.app;

/**
 * Created by Administrator on 2016/2/23.
 */

import android.app.Application;
import android.content.Context;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.oldfriends.app.network.OkHttpImageDownloader;
import com.oldfriends.app.util.Utility;
import com.squareup.okhttp.OkHttpClient;

public class BaseApp extends Application
{
    public static Context mContext;

    public static boolean inMainProcess(Context paramContext)
    {
        return paramContext.getPackageName().equals(Utility.getProcessName(paramContext));
    }

//    private LoginInfo loginInfo()
//    {
//        return null;
//    }

//    private SDKOptions options()
//    {
//        SDKOptions localSDKOptions = new SDKOptions();
//        StatusBarNotificationConfig localStatusBarNotificationConfig = new StatusBarNotificationConfig();
//        localStatusBarNotificationConfig.notificationEntrance = MainActivity.class;
//        localStatusBarNotificationConfig.notificationSmallIconId = 2130837649;
//        localSDKOptions.statusBarNotificationConfig = localStatusBarNotificationConfig;
//        StringBuilder localStringBuilder = new StringBuilder();
//        localSDKOptions.sdkStorageRootPath = (Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/nim");
//        localSDKOptions.preloadAttach = true;
//        localSDKOptions.thumbnailSize = (getResources().getDisplayMetrics().widthPixels / 2);
//        UserInfoProvider local1 = new UserInfoProvider()
//        {
//            public Bitmap getAvatarForMessageNotifier(String paramAnonymousString)
//            {
//                return null;
//            }
//
//            public int getDefaultIconResId()
//            {
//                return 2130837571;
//            }
//
//            public String getDisplayNameForMessageNotifier(String paramAnonymousString1, String paramAnonymousString2, SessionTypeEnum paramAnonymousSessionTypeEnum)
//            {
//                return null;
//            }
//
//            public Bitmap getTeamIcon(String paramAnonymousString)
//            {
//                return null;
//            }
//
//            public UserInfoProvider.UserInfo getUserInfo(String paramAnonymousString)
//            {
//                return null;
//            }
//        };
//        localSDKOptions.userInfoProvider = local1;
//        return localSDKOptions;
//    }

    public void initImageLoader(Context paramContext)
    {
        ImageLoaderConfiguration.Builder localBuilder = new ImageLoaderConfiguration.Builder(paramContext);
        localBuilder.threadPriority(3);
        localBuilder.denyCacheImageMultipleSizesInMemory();
        Md5FileNameGenerator localMd5FileNameGenerator = new Md5FileNameGenerator();
        localBuilder.diskCacheFileNameGenerator(localMd5FileNameGenerator);
        localBuilder.diskCacheSize(52428800);
        localBuilder.tasksProcessingOrder(QueueProcessingType.LIFO);
        Context localContext = getApplicationContext();
        OkHttpClient localOkHttpClient = new OkHttpClient();
        OkHttpImageDownloader localOkHttpImageDownloader = new OkHttpImageDownloader(localContext, localOkHttpClient);
        localBuilder.imageDownloader(localOkHttpImageDownloader);
        ImageLoader.getInstance().init(localBuilder.build());
    }

    public void onCreate()
    {
        super.onCreate();
        mContext = getBaseContext();
//        NIMClient.init(this, loginInfo(), options());
        if (inMainProcess(mContext))
            initImageLoader(getApplicationContext());
    }
}