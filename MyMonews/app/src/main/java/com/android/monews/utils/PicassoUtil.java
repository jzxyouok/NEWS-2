package com.android.monews.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class PicassoUtil {
    //加载图片并且显示在控件上的方法
    public static void setImageView(Context context,ImageView imageView,String url){
        Picasso.with(context)
                .load(url)
                .into(imageView);
    }


}
