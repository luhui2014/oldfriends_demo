package com.oldfriends.app.model;

/**
 * Created by Administrator on 2016/2/23.
 */
import com.oldfriends.app.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class User
{
    //{"nimserver": nimserver是云信返回的登录数据
    // {"code":200,"info":{"accid":"15150516695","token":"d7c7e5829a9018cec995af1b5fb7ec3d"}},

    // server里面是后台的accesstoken
    // "server":{"accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnQiOiJhcHAiLCJleHAiOjE0NTg4
    //   ODk1NDcsImlzcyI6MTQ1NjI5NzU0Nywicm9sZXMiOiJ1c2VyIiwidXNlciI6IjE1MTUwNTE2Njk1In0.btRebpLjy0M9b3NDR7Gjj9Ujlu-t1wZA3zIukzpnYHQ"}}
    private String nimserver;
    private String server;
    private String phoneNumber;

    public String getNimserver()
    {
        return this.nimserver;
    }

    public String getServer()
    {
        return this.server;
    }

    public void setNimserver(String paramString)
    {
        this.nimserver = paramString;
    }

    public void setServer(String paramString)
    {
        this.server = paramString;
    }

    public static final class Builder extends JsonReader<User>
    {
        public User fromJson(JSONObject paramJSONObject) throws JSONException{
            User user = new User();
            JSONObject nimserverObect = paramJSONObject.optJSONObject("nimserver");
            if(StringUtils.isNotBlank(nimserverObect.toString())){
                JSONObject infoObect = nimserverObect.optJSONObject("info");
                if(StringUtils.isNotBlank(nimserverObect.toString())){
                    user.setPhoneNumber(infoObect.optString("accid"));
                    user.setNimserver(infoObect.optString("token"));
                }
            }
            user.setServer(paramJSONObject.optString("server"));
            return user;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
