package com.android.monews.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.monews.R;
import com.android.monews.activity.WebViewActivity;
import com.android.monews.adapter.HomeRecyclerAdapter;
import com.android.monews.analysis.ContentList;
import com.android.monews.utils.MyJSON;
import com.android.monews.utils.OKHTTP;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/19 0019.
 * <p/>
 * 首页推荐的碎片
 */
public class Home_Recommend extends Fragment {
    private RecyclerView recyclerView;
    private HomeRecyclerAdapter adapter;

    private List<ContentList> contentLists;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getArguments().getString("url");

        Log.i("onResponse", "--------------传过来的网址为:" + url);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.home_recommend, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_recyclrview1);

        if(bundle!=null){//保存了数据
            Log.i("onSaveInstanceState","----------------Bundle对象不为空！");
            ContentList [] Lists= (ContentList[]) bundle.getParcelableArray("contentLists");
            if(Lists!=null && Lists.length!=0){//数据不为空时
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                contentLists= Arrays.asList(Lists);
                Log.i("onSaveInstanceState","----------------contentLists集合长度为:"+contentLists.size());
                adapter=new HomeRecyclerAdapter(getContext(),contentLists);
                recyclerView.setAdapter(adapter);

                setOnClickLitener();//设置点击事件

            }else{//否则，数据为空
                setAdapter();//去执行异步任务
            }

        }else{
            setAdapter();//去执行异步任务
        }


        return view;
    }


    private void setAdapter() {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = OKHTTP.okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                contentLists = MyJSON.getContentList(json, "contentList");

                if (contentLists != null && contentLists.size() != 0) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                            adapter = new HomeRecyclerAdapter(getContext(), contentLists);
                            recyclerView.setAdapter(adapter);

                            setOnClickLitener();
                        }
                    });
                }
            }
        });
    }

    private void setOnClickLitener() {//设置监听
        adapter.setmOnItemClickLitener(new HomeRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(String aid) {
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url",aid);
                startActivity(intent);//跳转到详情页面
            }
        });
    }


    /**
     *  Fragment销毁的时候调用
     * 在这里对数据进行保存(缓存)
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {

        //判断数据集合是否为空
        if(contentLists==null || contentLists.size()==0) return;

        Log.i("onSaveInstanceState","----------------onSaveInstanceState方法，保存数据！");
        ContentList[]Lists=new ContentList[contentLists.size()];

        contentLists.toArray(Lists);//数据集合转换为数组
        //outState.putParcelableArray("contentLists",Lists);//保存数据(必须为序列化的对象)
    }
}
