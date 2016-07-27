package com.android.monews.data;

import android.webkit.WebView;

/**
 * Created by My on 2016/7/19 0019.
 */
public class VideoIofo {

    private String nickname;//用户名
    private String title;//标题
    private String readNum;//播放次数
    private String videoCovers;//预览图片
    private String weixinLogo;//用户头像
    private String video_url;
    private WebView webView;

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public VideoIofo(String nickname, String title, String readNum, String videoCovers, String weixinLogo, String video_url, WebView webView) {
        this.nickname = nickname;
        this.title = title;
        this.readNum = readNum;
        this.videoCovers = videoCovers;
        this.weixinLogo = weixinLogo;
        this.video_url = video_url;
        this.webView = webView;
    }


    public VideoIofo() {
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getVideoCovers() {
        return videoCovers;
    }

    public void setVideoCovers(String videoCovers) {
        this.videoCovers = videoCovers;
    }

    public String getWeixinLogo() {
        return weixinLogo;
    }

    public void setWeixinLogo(String weixinLogo) {
        this.weixinLogo = weixinLogo;
    }




}
