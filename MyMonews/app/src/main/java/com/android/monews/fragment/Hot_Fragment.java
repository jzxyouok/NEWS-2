package com.android.monews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.monews.App.MySetting;
import com.android.monews.R;
import com.android.monews.activity.WebViewActivity;
import com.android.monews.adapter.HomeRecyclerAdapter;
import com.android.monews.analysis.ContentList;
import com.android.monews.data.VideoOkhttp;
import com.android.monews.utils.MyJSON;
import com.android.monews.utils.URLUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/19 0019.
 * <p/>
 * <p/>
 *      热点的碎片
 */
public class Hot_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private HomeRecyclerAdapter adapter;
    private List<ContentList> contentLists;
    private String text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString("text");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.hot_recycler);
        ImageView imageView = (ImageView) view.findViewById(R.id.hot_my);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySetting.class);
                startActivity(intent);
            }
        });





        setAdapter();

        return view;
    }

    private void setAdapter() {
        Request request = new Request.Builder()
                .url(URLUtils.HOT)
                .build();

        Call call = VideoOkhttp.okHttpClient.newCall(request);
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

                            adapter.setmOnItemClickLitener(new HomeRecyclerAdapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(String aid) {
                                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
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
