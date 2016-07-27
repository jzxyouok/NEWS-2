package com.android.monews.analysis;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/21 0021.
 *
 *  排行榜里面的公众号封装类
 */
public class Paihp2 implements Parcelable{
    private String weixin;
    private String nickname;//名称
    private String avatar;//头像网址
    private String new_source;
    private String desc;//介绍


    protected Paihp2(Parcel in) {
        weixin = in.readString();
        nickname = in.readString();
        avatar = in.readString();
        new_source = in.readString();
        desc = in.readString();
    }

    public static final Creator<Paihp2> CREATOR = new Creator<Paihp2>() {
        @Override
        public Paihp2 createFromParcel(Parcel in) {
            return new Paihp2(in);
        }

        @Override
        public Paihp2[] newArray(int size) {
            return new Paihp2[size];
        }
    };

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNew_source() {
        return new_source;
    }

    public void setNew_source(String new_source) {
        this.new_source = new_source;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Paihp2(String avatar, String desc, String new_source, String nickname, String weixin) {
        this.avatar = avatar;
        this.desc = desc;
        this.new_source = new_source;
        this.nickname = nickname;
        this.weixin = weixin;
    }

    public Paihp2() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Paihp2{" +
                "avatar='" + avatar + '\'' +
                ", weixin='" + weixin + '\'' +
                ", nickname='" + nickname + '\'' +
                ", new_source='" + new_source + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(weixin);
        dest.writeString(nickname);
        dest.writeString(avatar);
        dest.writeString(new_source);
        dest.writeString(desc);
    }
}
