package com.android.monews.utils;

import com.android.monews.analysis.ContentList;
import com.android.monews.analysis.Paihb;
import com.android.monews.analysis.Paihp2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class MyJSON {
    //解析首页里面的推荐数据
    public static List<ContentList> getContentList(String jsonStr,String arrayStr){

        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            JSONArray array=jsonObject.getJSONArray(arrayStr);
            List<ContentList>contentLists=new ArrayList<>();
            List<String> imgList = null;
            ContentList contentList;
            String s;
            for (int i = 0; i <array.length() ; i++) {
                JSONObject object=array.getJSONObject(i);
                 String aid=object.optString("aid");
                 String title=object.optString("title");
                 String weixin=object.optString("weixin");
                 String readNum=object.optString("readNum");
                 String nickname=object.optString("nickname");
                 String imgurl=object.optString("imgurl");
                 String lastId=object.optString("lastId");
                 String showType=object.optString("showType");

                 if(showType.equals("small")){
                     JSONArray array1=object.getJSONArray("imgList");
                     imgList=new ArrayList<>();
                     for (int j = 0; j <array1.length() ; j++) {
                         s=array1.getString(j);
                         imgList.add(s);
                     }
                 }else{
                     imgList=null;
                 }

                contentList=new ContentList(aid,imgList,imgurl,lastId,nickname,readNum,showType,title,weixin);
                contentLists.add(contentList);
            }

            return contentLists;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解析JSON,返回详情页面的网址
    public static String getURL(String json){

        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject object=jsonObject.getJSONObject("article_info");

            String url=object.optString("shareUrl");

            return url;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解析JSON，返回排行榜里面对象集合
    public static List<Paihb> getPaihbs(String jsonStr){
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            JSONArray array=jsonObject.getJSONArray("category_list");
            List<Paihb>paihbs=new ArrayList<>();
            Paihb paihb;
            for (int i = 0; i <array.length() ; i++) {
                JSONObject object=array.getJSONObject(i);
                String typeid=object.optString("typeid");
                String category_name=object.optString("category_name");
                String imgurl=object.optString("imgurl");

                paihb=new Paihb(category_name,imgurl,typeid);
                paihbs.add(paihb);
            }

        return paihbs;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


      //解析JSON，返回排行榜里面公众号对象集合
    public static List<Paihp2> getPaihb2s(String jsonStr){
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            JSONArray array=jsonObject.getJSONArray("list");
            List<Paihp2>paihb2s=new ArrayList<>();
            Paihp2 paihp2;

            for (int i = 0; i <array.length() ; i++) {
                JSONObject object=array.getJSONObject(i);
                String weixin=object.optString("weixin");
                String nickname=object.optString("nickname");
                String avatar=object.optString("avatar");
                String new_source=object.optString("new_source");
                String desc=object.optString("desc");

                paihp2=new Paihp2(avatar,desc,new_source,nickname,weixin);
                paihb2s.add(paihp2);
            }

        return paihb2s;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }




}
