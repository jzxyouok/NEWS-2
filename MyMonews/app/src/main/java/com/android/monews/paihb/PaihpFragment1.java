package com.android.monews.paihb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.monews.R;
import com.android.monews.activity.WebViewActivity;
import com.android.monews.adapter.HomeRecyclerAdapter;
import com.android.monews.analysis.ContentList;
import com.android.monews.utils.MyJSON;
import com.android.monews.utils.OKHTTP;
import com.android.monews.utils.URLUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/20 0020.
 *
 * 文章的碎片
 */
public class PaihpFragment1 extends Fragment {
    private List<ContentList> contentLists;
    private RecyclerView recyclerView;
    private HomeRecyclerAdapter adapter;
    private String typeid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeid = getArguments().getString("typeid");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.paihpfragment1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.paihb_fragment1);

       if(bundle!=null){
           ContentList[]Lists= (ContentList[]) bundle.getParcelableArray("contentLists");

           if(Lists!=null && Lists.length!=0){
               contentLists=Arrays.asList(Lists);
               adapter=new HomeRecyclerAdapter(getContext(),contentLists);
               recyclerView.setAdapter(adapter);
               setOnClickLitener();//设置点击事件
           }else{
               setAdapter();//去执行异步任务
           }


       }else{
           setAdapter();//去执行异步任务
       }

        return view;
    }

    private void setAdapter() {//执行异步任务
        Request request = new Request.Builder()
                .url(URLUtils.PAIHBZI1+typeid)
                .build();

        Call call = OKHTTP.okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                contentLists = MyJSON.getContentList(json,"list");

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

    @Override//保存数据的方法
    public void onSaveInstanceState(Bundle outState) {
        if(contentLists==null)return;

        ContentList[]Lists=new ContentList[contentLists.size()];
        contentLists.toArray(Lists);
        //outState.putParcelableArray("contentLists",Lists);//保存数据
    }

    private void setOnClickLitener() {//设置监听
        adapter.setmOnItemClickLitener(new HomeRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(String aid) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", aid);
                startActivity(intent);//跳转到详情页面
            }
        });
    }
}
