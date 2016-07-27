package com.android.monews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.monews.R;
import com.android.monews.adapter.HomeRecyclerAdapter;
import com.android.monews.analysis.ContentList;
import com.android.monews.analysis.Paihb;
import com.android.monews.utils.MyJSON;
import com.android.monews.utils.OKHTTP;
import com.android.monews.utils.URLUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 专业测试类
 */
public class MyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HomeRecyclerAdapter adapter;
    List<ContentList> contentLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Fresco库的初始化,要先初始化库，才能完成布局文件的加载
        Fresco.initialize(this);//初始化框架
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paihp2);


        OKHTTP.getOkHttp();
        //initView();

    }


    private void initView() {

        Request request = new Request.Builder()
                .url(URLUtils.PAIHB)
                .build();

        Call call = OKHTTP.okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                List<Paihb> paihbs= MyJSON.getPaihbs(json);

                if(paihbs!=null){
                    for (int i = 0; i < paihbs.size(); i++) {
                        Log.i("onResponse", "-----------------"+paihbs.get(i).toString()+"\n");
                    }
                }


            }
        });
    }
}