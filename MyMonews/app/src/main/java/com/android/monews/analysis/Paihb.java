package com.android.monews.analysis;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/20 0020.
 *
 * 排行榜的封装类,进行序列化
 */
public class Paihb implements Parcelable{
    private String typeid;
    private String category_name;//标题
    private String imgurl;

    protected Paihb(Parcel in) {
        typeid = in.readString();
        category_name = in.readString();
        imgurl = in.readString();
    }

    public static final Creator<Paihb> CREATOR = new Creator<Paihb>() {
        @Override
        public Paihb createFromParcel(Parcel in) {
            return new Paihb(in);
        }

        @Override
        public Paihb[] newArray(int size) {
            return new Paihb[size];
        }
    };

    @Override
    public String toString() {
        return "Paihb{" +
                "category_name='" + category_name + '\'' +
                ", typeid='" + typeid + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public Paihb() {

    }

    public Paihb(String category_name, String imgurl, String typeid) {
        this.category_name = category_name;
        this.imgurl = imgurl;
        this.typeid = typeid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(typeid);
        dest.writeString(category_name);
        dest.writeString(imgurl);
    }
}
