package com.android.monews.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.monews.data.VideoOkhttp;
import com.android.monews.R;
import com.android.monews.data.VideoIofo;

import java.util.List;

/**
 * Created by My on 2016/7/19 0019.
 * <p/>
 * 视频列表适配器
 */
public class videoAdapter extends BaseAdapter {
    Context context;
    List<VideoIofo> mVideoIfo;
    private int currentIndex = -1;
    private int playPosition = -1;
    private boolean isPaused = false;
    private boolean isPlaying = false;
    private ImageView video_play_btn;
    private ViewHolder mHolder;
    WebView mWebView;


    public videoAdapter(List<VideoIofo> mVideoIfo) {
        this.mVideoIfo = mVideoIfo;
    }

    @Override
    public int getCount() {
        return mVideoIfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mVideoIfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.webvoid, parent, false);
            mHolder = new ViewHolder();

            mHolder.userLogo = (ImageView) convertView.findViewById(R.id.video_logo);
            mHolder.videoCovers = (ImageView) convertView.findViewById(R.id.video_image);

            mHolder.void_username = (TextView) convertView.findViewById(R.id.video_user);
            mHolder.void_playNum = (TextView) convertView.findViewById(R.id.video_playNum);
            mHolder.void_title = (TextView) convertView.findViewById(R.id.video_title);
            mHolder.video_url = (TextView) convertView.findViewById(R.id.video_url);
            mHolder.mWebView = (WebView) convertView.findViewById(R.id.video_web);

            convertView.setTag(mHolder);
        } else {


            VideoIofo videoIofo = (VideoIofo) getItem(position);

            mHolder = (ViewHolder) convertView.getTag();

            mHolder.void_title.setText(videoIofo.getTitle());
            mHolder.void_playNum.setText(videoIofo.getReadNum());
            mHolder.void_username.setText(videoIofo.getNickname());
            mHolder.video_url.setText(videoIofo.getVideo_url());


          /*  mHolder.mWebView.getSettings().setJavaScriptEnabled(true);
            mHolder.mWebView.setWebViewClient(new WebViewClient());
            mHolder.mWebView.requestFocus();
            mHolder.mWebView.loadUrl(mHolder.video_url + "");
            String url=mVideoIfo.get(position);*/
            //防止跳转到系统自带的浏览器打开网页
            mHolder.mWebView.setWebViewClient(new WebViewClient());
            mHolder.mWebView.loadUrl(videoIofo.getVideo_url());//让WebView加载指定的url网页数据
            mHolder.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mHolder.mWebView.getSettings().setJavaScriptEnabled(true);



/*
            mWebView.getSettings().setJavaScriptEnabled(true);
            mHolder.video_url.requestFocus();
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.loadUrl(mHolder.video_url + "");
*/

            mHolder.videoCovers.setImageResource(R.mipmap.ic_launcher);
            VideoOkhttp.videoImage(parent.getContext(), mHolder.userLogo, videoIofo.getWeixinLogo());
            VideoOkhttp.videoImage(parent.getContext(), mHolder.videoCovers, videoIofo.getVideoCovers());

            video_play_btn = (ImageView) convertView.findViewById(R.id.video_play);
            video_play_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("VideoOkhttp", "=================================");

//                    Toast.makeText(context,"开始",Toast.LENGTH_LONG).show();
                    mHolder.videoCovers.setVisibility(View.GONE);
                    video_play_btn.setVisibility(View.GONE);
                    mHolder.mWebView.setVisibility(View.VISIBLE);


                }
            });


        }


        return convertView;
    }

    public class ViewHolder {
        public ImageView userLogo, videoCovers;
        public TextView void_title, void_username, void_playNum, video_url;
        public WebView mWebView;
    }


}
