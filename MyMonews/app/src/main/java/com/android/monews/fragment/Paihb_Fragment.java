package com.android.monews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.monews.App.MySetting;
import com.android.monews.R;
import com.android.monews.analysis.Paihb;
import com.android.monews.data.VideoOkhttp;
import com.android.monews.paihb.PaihpFragment1;
import com.android.monews.paihb.PaihpFragment2;
import com.android.monews.utils.MyJSON;
import com.android.monews.utils.URLUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/19 0019.
 * <p>
 * <p>
 * 排行榜的碎片
 */
public class Paihb_Fragment extends Fragment implements RadioGroup.OnClickListener {
    List<Paihb> paihbs;
    List<String> strs;
    private RadioGroup radioGroup, paihb_group1;
    private FrameLayout layout;
    private String text;
    private List<PaihpFragment1> fragment1s;
    private List<PaihpFragment2> fragment2s;

    private static int A = 0;//记录文字和公众号
    private static int B = 0;//记录分类

    private Fragment cunnFragment;
    private Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString("text");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.paihb_fragment, container, false);
        layout = (FrameLayout) view.findViewById(R.id.paihb_layout);
        radioGroup = (RadioGroup) view.findViewById(R.id.paihb_group);
        paihb_group1 = (RadioGroup) view.findViewById(R.id.paihb_group1);

        ImageView imageView = (ImageView) view.findViewById(R.id.paihb_my);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySetting.class);
                startActivity(intent);
            }
        });


        initOKHTTP();


        return view;
    }

    private void initView() {

        for (int i = 0; i < paihb_group1.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) paihb_group1.getChildAt(i);
            radioButton.setOnClickListener(this);
        }


        fragment1s=new ArrayList<>(strs.size());//给集合指定大小
        //创建碎片对象
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        bundle = new Bundle();
        bundle.putString("typeid",paihbs.get(0).getTypeid());
        PaihpFragment1 fragment1 = new PaihpFragment1();
        fragment1.setArguments(bundle);
        transaction.add(R.id.paihb_layout, fragment1);
        fragment1s.add(fragment1);

        cunnFragment = fragment1;
        transaction.commit();
    }

    private void initOKHTTP() {

        Request request = new Request.Builder()
                .url(URLUtils.PAIHB)
                .build();

        Call call = VideoOkhttp.okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                paihbs = MyJSON.getPaihbs(json);

                if (paihbs != null && paihbs.size() != 0) {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            strs = new ArrayList<String>();
                            for (int i = 0; i < paihbs.size(); i++) {
                                Paihb paihb = paihbs.get(i);
                                strs.add(paihb.getCategory_name());
                            }

                            for (int i = 0; i < strs.size(); i++) {
                                RadioButton radioButton = (RadioButton) LayoutInflater.from(getContext()).inflate(R.layout.radiobutton, null);
                                if (i == 0) {
                                    radioButton.setTextColor(getResources().getColor(R.color.main_text_yes));
                                } else {
                                    radioButton.setTextColor(getResources().getColor(R.color.main_text_no));
                                }

                                radioButton.setTag(i);
                                radioButton.setOnClickListener(Paihb_Fragment.this);
                                radioButton.setText(strs.get(i));
                                radioGroup.addView(radioButton);//添加到父容器中

                            }

                            initView();//创建碎片的方法
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(fragment1s==null){
            fragment1s=new ArrayList<>(strs.size());
        }
        if(fragment2s==null){
            fragment2s=new ArrayList<>(strs.size());
        }

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                B=i;//获得当前点击的分类(下标)
                radioButton.setTextColor(getResources().getColor(R.color.main_text_yes));
            } else {
                radioButton.setTextColor(getResources().getColor(R.color.main_text_no));
            }
        }


        switch (v.getId()) {
            case R.id.paihb_button1://文章

                A = 0;
                ((RadioButton) paihb_group1.getChildAt(0)).setTextColor(getResources().getColor(R.color.main_text_yes));
                ((RadioButton) paihb_group1.getChildAt(1)).setTextColor(getResources().getColor(R.color.main_text_no));

                break;
            case R.id.paihb_button2://公众号

                A = 1;
                ((RadioButton) paihb_group1.getChildAt(0)).setTextColor(getResources().getColor(R.color.main_text_no));
                ((RadioButton) paihb_group1.getChildAt(1)).setTextColor(getResources().getColor(R.color.main_text_yes));

                break;
        }

        isitFragment(A,B);

    }

    //隐藏、显示、创建碎片对象(参数1：类别标题(下标)、   参数2：分类标题(下标))
    public void isitFragment(int A, int B) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment=null;

        if(A==0){
            fragment=new PaihpFragment1();
            bundle = new Bundle();
            bundle.putString("typeid",paihbs.get(B).getTypeid());
            fragment.setArguments(bundle);

        }else if(A==1){
            fragment=new PaihpFragment2();
            bundle = new Bundle();
            bundle.putString("typeid",paihbs.get(B).getTypeid());
            fragment.setArguments(bundle);

        }


        if (fragment.isAdded()) {//碎片如果存在
            transaction.hide(cunnFragment);
            transaction.show(fragment);
        } else {
            transaction.hide(cunnFragment);
            transaction.add(R.id.paihb_layout, fragment);
        }
        transaction.commit();
        cunnFragment = fragment;
    }

}