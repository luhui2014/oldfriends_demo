package com.oldfriends.app.config;

/**
 * Created by Administrator on 2016/2/23.
 */
public class SysConfig
{
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String API_ENDPOINT = "http://121.40.100.90:3000/api/";
    public static final String APP_CACAHE_DIRNAME = "/webcache";
    private static final String CLOUD_IMAGE_HEAD_URL = "http://7xp2j6.com2.z0.glb.qiniucdn.com/";
    public static final String PACKAGE_NAME = "com.oldfriends.app";
    public static final String PREF_IS_FIRST_ENTER = "IS_FIRST_ENTER";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_NICK_NAME = "USER_NICK_NAME";
    public static final String USER_SEX = "USER_SEX";

    public static String getConfigFileInCloud(String paramString)
    {
        StringBuilder localStringBuilder = new StringBuilder();
        return "http://7xp2j6.com2.z0.glb.qiniucdn.com/" + paramString;
    }

    public static String getResourceFileInCLoud(String paramString)
    {
        StringBuilder localStringBuilder = new StringBuilder();
        return "http://7xp2j6.com2.z0.glb.qiniucdn.com/" + paramString;
    }
}
