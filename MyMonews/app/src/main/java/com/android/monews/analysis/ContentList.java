package com.android.monews.analysis;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19 0019.
 *
 *  实现序列化的接口:Parcelable
 */
public class ContentList implements Parcelable{
    private String aid;
    private String title;//标题
    private String weixin;//微信
    private String readNum;//浏览次数
    private String nickname;//名称
    private String imgurl;//图片网址
    private String lastId;

    private String showType;//类型   cover一张、small多张
    private List<String> imgList;//图片网址集合

    public ContentList() {
    }


    public ContentList(String aid, List<String> imgList, String imgurl, String lastId, String nickname, String readNum, String showType, String title, String weixin) {
        this.aid = aid;
        this.imgList = imgList;
        this.imgurl = imgurl;
        this.lastId = lastId;
        this.nickname = nickname;
        this.readNum = readNum;
        this.showType = showType;
        this.title = title;
        this.weixin = weixin;
    }

    protected ContentList(Parcel in) {
        aid = in.readString();
        title = in.readString();
        weixin = in.readString();
        readNum = in.readString();
        nickname = in.readString();
        imgurl = in.readString();
        lastId = in.readString();
        showType = in.readString();
        imgList = in.createStringArrayList();
    }

    public static final Creator<ContentList> CREATOR = new Creator<ContentList>() {
        @Override
        public ContentList createFromParcel(Parcel in) {
            return new ContentList(in);
        }

        @Override
        public ContentList[] newArray(int size) {
            return new ContentList[size];
        }
    };

    @Override
    public String toString() {
        return "ContentList{" +
                "aid='" + aid + '\'' +
                ", title='" + title + '\'' +
                ", weixin='" + weixin + '\'' +
                ", readNum='" + readNum + '\'' +
                ", nickname='" + nickname + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", lastId='" + lastId + '\'' +
                ", showType='" + showType + '\'' +
                ", imgList=" + imgList +
                '}';
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(aid);
        dest.writeString(title);
        dest.writeString(weixin);
        dest.writeString(readNum);
        dest.writeString(nickname);
        dest.writeString(imgurl);
        dest.writeString(lastId);
        dest.writeString(showType);
        dest.writeStringList(imgList);
    }
}
