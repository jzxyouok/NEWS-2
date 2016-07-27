package com.android.monews.analysis;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19 0019.
 *
 * 首页，推荐里面的封装对象
 */
public class HomeObject {
    private String count;//大小

    private List<ContentList> contentList;

    public HomeObject() {
    }

    @Override
    public String toString() {
        return "HomeObject{" +
                "contentList=" + contentList +
                ", count='" + count + '\'' +
                '}';
    }

    public List<ContentList> getContentLists() {
        return contentList;
    }

    public void setContentLists(List<ContentList> contentLists) {
        this.contentList = contentLists;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public HomeObject(List<ContentList> contentLists, String count) {

        this.contentList = contentLists;
        this.count = count;
    }
}
