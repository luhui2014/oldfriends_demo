package com.oldfriends.app.network;

import android.content.Context;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * Implementation of ImageDownloader which uses {@link com.squareup.okhttp.OkHttpClient} for image stream retrieving.
 *
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class OkHttpImageDownloader extends BaseImageDownloader {
    private static final String TAG = "OkHttpImageDownloader";

    private OkHttpClient client;

    public OkHttpImageDownloader(Context context, OkHttpClient client) {
        super(context);
        this.client = client;
    }

    @Override
    protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
        Request request = new Request.Builder().url(imageUri).build();
        Response response = client.newCall(request).execute();
//        if (!response.isSuccessful()) {
//            Log.e(TAG, "下载图片失败，HTTP STATUS " + response.code());
//            return null;
//        }
        return response.body().byteStream();
    }


}
