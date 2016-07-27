package com.android.monews.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.monews.R;

import com.android.monews.utils.MyJSON;
import com.android.monews.utils.OKHTTP;
import com.android.monews.utils.URLUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private LinearLayout layout;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();

    }

    private void initView() {
        
        webView = (WebView) findViewById(R.id.web_webview);
        layout = (LinearLayout) findViewById(R.id.web_layout);

        url = getIntent().getStringExtra("url");

        Request request = new Request.Builder()
                .url(URLUtils.DETAILS1 + url + URLUtils.DETAILS2)
                .build();

        Call call = OKHTTP.okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                url = MyJSON.getURL(response.body().string());
                Log.i("setmOnItemClickLitener", "----------JSON解析的网址为:" + url);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //防止跳转到系统自带的浏览器打开网页
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl(url);//让WebView加载指定的url网页数据

                        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                        webView.getSettings().setJavaScriptEnabled(true);

                        webView.setWebChromeClient(new WebChromeClient() {
                            @Override//当网页加载进度发生变化时会调用
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                if (newProgress == 100) {
                                    layout.setVisibility(View.GONE);
                                    Toast.makeText(WebViewActivity.this, "详情页面加载完成!", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override//当接收到网页的图标时会调用
                            public void onReceivedIcon(WebView view, Bitmap icon) {
                                super.onReceivedIcon(view, icon);
                            }

                            @Override//当接收到标题时会调用该方法
                            public void onReceivedTitle(WebView view, String title) {
                                super.onReceivedTitle(view, title);
                            }

                            @Override//当javascript调用alert方法的时候调用
                            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                                return super.onJsAlert(view, url, message, result);
                            }
                        });
                    }
                });

            }
        });
    }
}
