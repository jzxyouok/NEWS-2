package com.android.monews.fragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.monews.data.VideoOkhttp;
import com.android.monews.R;
import com.android.monews.data.VideoJSON;
import com.android.monews.adapter.videoAdapter;
import com.android.monews.data.VideoIofo;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by My on 2016/7/19 0019
 * <p/>
 * 视频碎片
 * <p/>
 * .
 */
public class fragment_void extends Fragment {
    private videoAdapter mAdapter;
    private List<VideoIofo> mIofos = new ArrayList<>();
    private ListView mListView;
    private String url;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());//初始化框架
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
        VideoOkhttp.getOkHttp();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_void_item, container, false);

        initView(view);
        getData();
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.void_item);
    }

    public void getData() {


        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = VideoOkhttp.okHttpClient.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                System.out.println("--------------=======---");
                String jsonStr=response.body().string();
                System.out.println("-----------------"+jsonStr);

                try {
                    mIofos = VideoJSON.getVideo(jsonStr);

                    if (mIofos != null&&mIofos.size()!=0){

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                mAdapter = new videoAdapter(mIofos);
                                mListView.setAdapter(mAdapter);



                                mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                    @Override
                                    public void onScrollStateChanged(AbsListView view, int scrollState) {

                                    }

                                    @Override
                                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {



                                    }
                                });
                            }
                        });

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
