package com.android.monews.utils;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class URLUtils {

    //首页的网址数组
    public static final String[] HOMES = {
            "http://api.myhaowai.com/api/article/get_commend_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1497&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1508&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1506&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1532&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1546&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1547&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1591&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1498&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1514&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1500&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1512",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1501",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1590",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1499&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1542&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1531&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1543&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1939&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1525&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1502&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1504&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1503&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1589&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1541&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1528&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1515&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1523&direction=1&pageNumber=1",
            "http://api.myhaowai.com/api/article/get_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateId=1505&direction=1&pageNumber=1"
    };


    //热点的网址
    public static final String HOT = "http://api.myhaowai.com/api/article/get_hot_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&direction=1";


    //详情页面网址(上下)
    public static final String DETAILS1 = "http://api.myhaowai.com/api/article/get_article_by_aid?aid=";
    public static final String DETAILS2 = "&readFrom=app";


    //排行榜
    public static final String PAIHB = "http://api.myhaowai.com/api/rank/get_rank_category_list?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012";

    //排行榜的文章分类      参数 cateid=?
    public static final String PAIHBZI1 = "http://api.myhaowai.com/api/rank/get_article_by_cateid?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateid=";
    //排行榜的公众号分类    参数 cateid=?
    public static final String PAIHBZI2 = "http://api.myhaowai.com/api/rank/get_rank_by_cateid?devid=8e53478ecd4daf201a7a84a9ea7d8050&version=3.2&pcode=01100012&cateid=";


    //进入公众号        参数weixin=？
    public static final String PAIHP2_URL1 = "http://api.myhaowai.com/api/article/get_weixin_article_list?weixin=";
    public static final String PAIHP2_URL2 = "&newSource=1&page=1";


}
