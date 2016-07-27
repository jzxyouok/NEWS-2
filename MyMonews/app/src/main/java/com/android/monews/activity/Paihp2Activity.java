package com.android.monews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.monews.R;
import com.android.monews.adapter.Paigp2Adapter;
import com.android.monews.analysis.ContentList;
import com.android.monews.analysis.Paihp2;
import com.android.monews.utils.MyJSON;
import com.android.monews.utils.OKHTTP;
import com.android.monews.utils.URLUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class Paihp2Activity extends AppCompatActivity {
    private List<ContentList> contentLists;
    private RecyclerView recyclerView;
    private Paigp2Adapter adapter;

    private Bundle bundle;
    private Paihp2 paihp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paihp2);

        OKHTTP.getOkHttp();
        bundle = getIntent().getExtras();
        paihp2 = bundle.getParcelable("Paihp2");//获得传递过来的对象

        if (paihp2 != null) {
            initView();
        }

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_paihp_listview);


        Request request = new Request.Builder()
                .url(URLUtils.PAIHP2_URL1 + paihp2.getWeixin() + URLUtils.PAIHP2_URL2)
                .build();

        Call call = OKHTTP.okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                contentLists = MyJSON.getContentList(json, "article_list");

                if (contentLists != null && contentLists.size() != 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter = new Paigp2Adapter(contentLists, paihp2, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                            //监听点击事件
                            adapter.setmOnItemClickLitener(new Paigp2Adapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(String aid) {
                                    Intent intent = new Intent(Paihp2Activity.this, WebViewActivity.class);
                                    intent.putExtra("url", aid);
                                    startActivity(intent);//跳转到详情页面
                                }
                            });
                        }
                    });
                }
            }
        });
    }


}