package com.android.monews.App;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.android.monews.R;

/**
 * Created by My on 2016/7/20 0020.
 */
public class About extends AppCompatActivity {
    WebView mWebView;
    ImageView img;
    String url = "http://api.myhaowai.com/api/article/get_template?template=aboutUs";
//    String url1 = "http://api.myhaowai.com/api/article/get_template?template=shareArticle&aid=J0AOPtjeQt0=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏

        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);


        init();
    }

    private void init() {
        mWebView = (WebView) findViewById(R.id.about_web);
        img = (ImageView) findViewById(R.id.about_back);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });



        //WebView加载web资源
        mWebView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

}
