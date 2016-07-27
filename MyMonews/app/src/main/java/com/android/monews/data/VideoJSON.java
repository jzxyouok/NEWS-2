package com.android.monews.data;

import com.android.monews.data.VideoIofo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2016/7/19 0019.
 */
public class VideoJSON {

    public static List<VideoIofo> getVideo(String str_json) throws JSONException {
        if (str_json == null) return null;


        JSONObject jsonObject = new JSONObject(str_json);

        JSONArray array = jsonObject.getJSONArray("视频");


        List<VideoIofo> list = new ArrayList<>();

        int len = array.length();

        JSONObject object = null;

        VideoIofo videoIofo = null;

        for (int i = 0; i < len; i++) {


            object = array.getJSONObject(i);

            videoIofo = new VideoIofo();

            videoIofo.setNickname(object.optString("topicName"));
            videoIofo.setReadNum("播放次数:" + object.optString("playCount"));
            videoIofo.setTitle(object.optString("title"));
            videoIofo.setWeixinLogo(object.optString("topicImg"));
            videoIofo.setVideoCovers(object.optString("cover"));
            videoIofo.setVideo_url(object.optString("mp4_url"));

            list.add(videoIofo);
        }

        return list;
    }




}


