package com.oldfriends.app.network;

/**
 * Created by Administrator on 2016/2/23.
 */

import android.util.Log;
import com.oldfriends.app.model.NetworkException;
import com.oldfriends.app.model.User;
import org.json.JSONException;
import org.json.JSONObject;


public class UserRepository
{
    private static final String TAG = "UserRepository";

    private JSONObject splicingJson(String paramString1, String paramString2, String paramString3, String paramString4)
    {
        JSONObject localJSONObject = new JSONObject();
        try
        {
            localJSONObject.put("username", paramString1);
            localJSONObject.put("password", paramString2);
            localJSONObject.put("nickName", paramString3);
            localJSONObject.put("registerCode", paramString4);
            return localJSONObject;
        }
        catch (JSONException localJSONException)
        {
            Log.e("UserRepository", "splicingJson 解析JSON时发生错误", localJSONException);
        }
        return localJSONObject;
    }

    private JSONObject splicingUserJson(String paramString1, String paramString2)
    {
        JSONObject localJSONObject = new JSONObject();
        try
        {
            localJSONObject.put("username", paramString1);
            localJSONObject.put("password", paramString2);
            localJSONObject.put("client", "app");
            return localJSONObject;
        }
        catch (JSONException localJSONException)
        {
           Log.e("UserRepository", "splicingUserJson 解析JSON时发生错误", localJSONException);
        }

        return localJSONObject;
    }

    public User login(String paramString1, String paramString2) throws NetworkException
    {
        JSONObject localJSONObject = splicingUserJson(paramString1, paramString2);
        RemoteApiHelper localRemoteApiHelper = new RemoteApiHelper<User>();
        return (User)localRemoteApiHelper.appendPath("users").appendPath("login").responseParser(new User.Builder()).doPost(localJSONObject).asSingle();
    }

    public void register(String paramString1, String paramString2, String paramString3, String paramString4)
            throws NetworkException
    {
        JSONObject localJSONObject = splicingJson(paramString1, paramString2, paramString3, paramString4);
        RemoteApiHelper localRemoteApiHelper = new RemoteApiHelper();
        localRemoteApiHelper.appendPath("users").doPost(localJSONObject);
    }

    public void verificationCode(String paramString1, String paramString2)
            throws NetworkException
    {
        RemoteApiHelper localRemoteApiHelper = new RemoteApiHelper();
        localRemoteApiHelper.appendPath("users").appendPath("smsCode").appendQueryParameter("mobile", paramString1).appendQueryParameter("type", paramString2).doGet();
    }
}
