package com.android.monews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.monews.App.MySetting;
import com.android.monews.data.VideoOkhttp;
import com.android.monews.R;
import com.android.monews.data.VideoURL;
import com.android.monews.fragment.fragment.fragment_void;

/**
 * Created by Administrator on 2016/7/19 0019.
 * <p>
 * <p>
 * 电影的碎片
 */
public class Video_Fragment extends Fragment {
    private TextView textView;
    private String[] tabs = {"精选", "夜色", "原创大片", "看天下", "VR全景", "娱乐圈", "无厘头", "高逼格", "体育控", "最爱玩", "军事迷", "爱生活", "汽车族", "萌萌哒", "创业家"};
    private TabLayout void_tablayout;
    private ViewPager void_viewpager;
    private String text;

    private ImageView video_my,video_search;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        text = getArguments().getString("text");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.void_fragment, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {


        void_tablayout = (TabLayout) view.findViewById(R.id.void_tablayout);
        void_viewpager = (ViewPager) view.findViewById(R.id.void_viewpager);

        //设置适配器
        void_viewpager.setAdapter(new MyAdapter(getChildFragmentManager()));
        void_tablayout.setupWithViewPager(void_viewpager);//设置关联ViewPager

        video_my = (ImageView) view.findViewById(R.id.video_my);
        video_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MySetting.class);
                startActivity(intent);
            }
        });

        video_search = (ImageView) view.findViewById(R.id.video_search);
        video_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoOkhttp.showShort(getContext(), "暂不提供搜索服务");
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }






    //ViewPager的碎片专用适配器
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public Fragment getItem(int position) {
            //创建碎片对象
            fragment_void fragment = new fragment_void();
            Bundle bundle = new Bundle();
            bundle.putString("url", VideoURL.VIDER_URL);

            fragment.setArguments(bundle);

            return fragment;
        }

        @Override//设置导航条的名称
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }


}