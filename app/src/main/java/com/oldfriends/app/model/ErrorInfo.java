package com.oldfriends.app.model;


import org.json.JSONObject;

/**
 * Created by Administrator on 2015/4/14.
 */
public class ErrorInfo {
    private String status;
    private String message;

    public ErrorInfo(JSONObject errorObject){
        this.status = errorObject.optString("code");
        this.message = errorObject.optString("error");
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
