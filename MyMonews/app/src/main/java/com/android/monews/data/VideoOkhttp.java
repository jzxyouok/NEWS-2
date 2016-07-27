package com.android.monews.data;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/7/12 0012.
 * <p/>
 * 网络请求的单例模式
 */
public class VideoOkhttp {
    public static VideoOkhttp okhttp;
    public static OkHttpClient okHttpClient;

    private VideoOkhttp() {
        okHttpClient = new OkHttpClient();
    }

    public static VideoOkhttp getOkHttp() {
        if (okhttp == null) {
            synchronized (VideoOkhttp.class) {
                if (okhttp == null) {
                    okhttp = new VideoOkhttp();
                }
            }
        }
        return okhttp;
    }


    //加载图片并且显示在控件上的方法
    public static void videoImage(Context context,ImageView imageView,String url){
        Picasso.with(context)
                .load(url)
                        //.placeholder(R.mipmap.bb6)
                        //.error(R.mipmap.bb7)
                .into(imageView);
    }



    Context context;
    // Toast
    public static void showShort(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }



}