package com.oldfriends.app.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * lh on 2015/12/7.
 */
public abstract class JsonReader<T> {
    public abstract T fromJson(JSONObject jsonObject) throws JSONException;

    public List<T> fromJsonArray(JSONArray array) throws JSONException{
        List<T> list = new ArrayList<T>();
        for(int i=0;i<array.length();i++){
            list.add(fromJson(array.getJSONObject(i)));
        }
        return list;
    }

    public List<T> fromJsonArray(JSONObject root,String path) throws JSONException{
        String[] segments = path.split("\\.");
        JSONObject jsonNode = root;
        for(int i=0;i<segments.length-1;i++){
            jsonNode = root.getJSONObject(segments[i]);
        }
        return fromJsonArray(jsonNode.getJSONArray(segments[segments.length - 1]));
    }


}
