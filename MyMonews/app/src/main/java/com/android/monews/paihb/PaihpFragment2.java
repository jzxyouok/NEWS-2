package com.android.monews.paihb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.monews.R;
import com.android.monews.activity.Paihp2Activity;
import com.android.monews.adapter.Paihp2Adapter;
import com.android.monews.analysis.Paihp2;
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
 * <p>
 * 公众号的碎片
 */
public class PaihpFragment2 extends Fragment {
    List<Paihp2> paihb2s;
    private ListView listView;
    private Paihp2Adapter adapter;
    private String typeid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeid = getArguments().getString("typeid");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.paihpfragment2, container, false);
        listView = (ListView) view.findViewById(R.id.paihb_fragment2);

        if (bundle != null) {
            Paihp2[] Lists = (Paihp2[]) bundle.getParcelableArray("paihb2s");

            if (Lists != null && Lists.length != 0) {
                paihb2s = Arrays.asList(Lists);
                adapter = new Paihp2Adapter(paihb2s);
                listView.setAdapter(adapter);

                setOnClickLitener();//设置点击事件
            } else {
                setAdapter();//去执行异步任务
            }

        } else {
            setAdapter();//去执行异步任务
        }

        return view;
    }


    private void setAdapter() {//执行异步任务
        Request request = new Request.Builder()
                .url(URLUtils.PAIHBZI2 + typeid)
                .build();

        Call call = OKHTTP.okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                paihb2s = MyJSON.getPaihb2s(json);

                if (paihb2s != null && paihb2s.size() != 0) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new Paihp2Adapter(paihb2s);
                            listView.setAdapter(adapter);
                            setOnClickLitener();//设置点击事件

                        }
                    });
                }
            }
        });
    }

    @Override//保存数据的方法
    public void onSaveInstanceState(Bundle outState) {
        if (paihb2s == null) return;

        Paihp2[] Lists = new Paihp2[paihb2s.size()];
        paihb2s.toArray(Lists);
       // outState.putParcelableArray("paihb2s", Lists);//保存数据
    }


    private void setOnClickLitener() {//设置监听

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Paihp2 paihp2 = paihb2s.get(position);

                Intent intent = new Intent(getActivity(), Paihp2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Paihp2", paihp2);//把对象传过去
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }
}


