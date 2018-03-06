package com.oldfriends.app.network;

import android.net.Uri;
import android.util.Log;
import com.oldfriends.app.config.SysConfig;
import com.oldfriends.app.model.ErrorInfo;
import com.oldfriends.app.model.JsonReader;
import com.oldfriends.app.model.NetworkException;
import com.oldfriends.app.util.StringUtils;
import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * lh on 2015/12/7.
 * okHttp 网络框架的封装
 */
public class RemoteApiHelper<T> {
    public static final String TAG = "RemoteApiHelper";
    private final OkHttpClient client = new OkHttpClient();
    private Uri.Builder uriBuilder = Uri.parse(SysConfig.API_ENDPOINT).buildUpon();
    private JsonReader<T> responseReader = null;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public RemoteApiHelper<T> appendPath(String path){
        uriBuilder.appendPath(path);
        return this;
    }

    public RemoteApiHelper<T> appendQueryParameter(String key,String value){
        uriBuilder.appendQueryParameter(key, value);
        return this;
    }

    public RemoteApiHelper<T> responseParser(JsonReader<T> reader){
        responseReader = reader;
        return this;
    }

    public ResponseResult<T> getResponseResult(Request request) throws NetworkException {
        try {
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();
            Log.d(TAG, "getResponseResult jsonString: " + jsonString);
            if(response.isSuccessful()){
                if(StringUtils.isNotBlank(jsonString)){
                    Object object = new JSONTokener(jsonString).nextValue();
                    return new ResponseResult<T>(object,responseReader);
                }
            }
            else{
                //此处进行异常处理
                Log.w(TAG, "URL: " + request.urlString() + "\nBODY: " + jsonString);
                if(isJSONValid(jsonString)){
                    JSONObject errorJson = new JSONObject(jsonString);
                    ErrorInfo errorInfo = new ErrorInfo(errorJson);
                    throw new NetworkException(errorInfo.getMessage() + "," + errorInfo.getStatus());
                }else {
                    throw new NetworkException("BAD REQUEST");
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "发送网络请求时发生错误", e);
        }catch (JSONException e){
            Log.e(TAG, "解析JSON时发生错误", e);
        }

        return new ResponseResult<T>(null, responseReader){
            @Override
            public T asSingle() {
                return null;
            }

            @Override
            public List<T> asList() {
                return new ArrayList<T>();
            }
        };
    }

//    public ResponseResult<T> doUnifyUrlGet() throws NetworkException{
//        String url = Uri.parse(SysConfig.API_ENDPOINT_UNIFY).buildUpon().build().toString();
//        Log.d(TAG, "请求地址 doGet " + url);
//        Request request = new Request.Builder().url(url).build();
//        return getResponseResult(request);
//    }

    public ResponseResult<T> doGet() throws NetworkException{
        String url = uriBuilder.build().toString();
        Log.d(TAG, "请求地址 doGet " + url);
        Request request = new Request.Builder().url(url).build();
        return getResponseResult(request);
    }

    public ResponseResult<T> doPost(JSONObject json) throws NetworkException{
        String url = uriBuilder.build().toString();
        Log.d(TAG, "请求地址 doPost " + url);
        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder().url(url).post(body).build();
        return getResponseResult(request);
    }

    public ResponseResult<T> doDelete() throws NetworkException{
        String url = uriBuilder.build().toString();
        Log.d(TAG, "请求地址 doDelete " + url);
        Request request = new Request.Builder().url(url).delete().build();
        return getResponseResult(request);
    }

    public ResponseResult<T> doPut(JSONObject json) throws NetworkException{
        String url = uriBuilder.build().toString();
        Log.d(TAG, "请求地址 doPut " + url);
        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder().url(url).put(body).build();
        return getResponseResult(request);
    }

    private boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }


    /**
     * 返回结果的处理类
     * @param <T>
     */
    protected class ResponseResult<T>{
        private final Object jsonResult;
        private final JsonReader<T> jsonReader;

        public ResponseResult(Object jsonResult, JsonReader<T> jsonReader) {
            this.jsonResult = jsonResult;
            this.jsonReader = jsonReader;
        }

        public T asSingle(){
            try {
                return jsonReader.fromJson((JSONObject)jsonResult);
            } catch (JSONException e) {
                Log.e(TAG," 解析json时发生错误 ",e);
            }
            return null;
        }

        public List<T> asList(){
            try {
                return jsonReader.fromJsonArray((JSONArray)jsonResult);
            } catch (JSONException e) {
                Log.e(TAG, " 解析json时发生错误 ", e);
            }
            return new ArrayList<T>();
        }
    }
}
