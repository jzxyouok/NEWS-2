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

import com.android.monews.App.MySetting;
import com.android.monews.R;
import com.android.monews.home.Home_Recommend;
import com.android.monews.utils.URLUtils;

/**
 * Created by Administrator on 2016/7/19 0019.
 * <p>
 * <p>
 *      首页的碎片
 */
public class Home_Fragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String url;

    private String[] tabs = {"推荐","天下","旅行","美食","两性","美女","灵异","男色","八卦","军事","情感","电影","野史","Les-girl","健康","探索","星座","品茗","风水","宠物","科技","段子","读书","G-boy","创业","健身","汽车","亲子","时尚"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url=getArguments().getString("text");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.home_fragment,container,false);
        viewPager= (ViewPager) view.findViewById(R.id.home_viewpager);
        tabLayout= (TabLayout) view.findViewById(R.id.home_tablayout);
        ImageView imageView = (ImageView) view.findViewById(R.id.home_my);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySetting.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置适配器
        viewPager.setAdapter(new HomeAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);//设置关联ViewPager

    }

    //ViewPager的碎片专用适配器
    class HomeAdapter extends FragmentPagerAdapter {
        public HomeAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return tabs.length;
        }
        @Override
        public Fragment getItem(int position) {
            //创建碎片对象
            Home_Recommend fragment=new Home_Recommend();
            Bundle bundle=new Bundle();
            bundle.putString("url", URLUtils.HOMES[position]);
            fragment.setArguments(bundle);

            return fragment;
        }
        @Override//设置导航条的名称
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

}
