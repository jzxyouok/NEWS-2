package com.android.monews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.monews.fragment.Home_Fragment;
import com.android.monews.fragment.Hot_Fragment;
import com.android.monews.fragment.Paihb_Fragment;
import com.android.monews.fragment.Video_Fragment;
import com.android.monews.utils.OKHTTP;
import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnClickListener {

    private RadioGroup radioGroup;
    private FrameLayout layout;

    private Home_Fragment fragment1;
    private Video_Fragment fragment2;
    private Hot_Fragment fragment3;
    private Paihb_Fragment fragment4;

    private Fragment cunnFragment;
    private Bundle bundle;

    private String[] strs = {"首页碎片", "视频碎片", "热点碎片", "排行榜碎片"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Fresco库的初始化,要先初始化库，才能完成布局文件的加载
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
        Fresco.initialize(this);//初始化框架
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OKHTTP.getOkHttp();
        initView();
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.main_group);
        layout = (FrameLayout) findViewById(R.id.main_layout);

        for (int i = 0; i < radioGroup.getChildCount(); i++) {//遍历单选框按钮组
            RadioButton button = (RadioButton) radioGroup.getChildAt(i);
            button.setOnClickListener(this);//设置添加事件
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        bundle = new Bundle();
        bundle.putString("text", strs[0]);
        fragment1 = new Home_Fragment();
        fragment1.setArguments(bundle);
        transaction.add(R.id.main_layout, fragment1);
        cunnFragment = fragment1;
        transaction.commit();

    }


    private int counnt;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_but1:
                counnt=0;
                isitFragment(1, fragment1);
                break;
            case R.id.main_but2:
                counnt=1;
                isitFragment(2, fragment2);
                break;
            case R.id.main_but3:
                counnt=2;
                isitFragment(3, fragment3);
                break;
            case R.id.main_but4:
                counnt=3;
                isitFragment(4, fragment4);
                break;
        }

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton button = (RadioButton) radioGroup.getChildAt(i);
            if (i == counnt) {
                button.setTextColor(getResources().getColor(R.color.main_text_yes));
                continue;
            }
            button.setTextColor(getResources().getColor(R.color.main_text_no));
        }
    }

    public void isitFragment(int counnt, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment == null) {//碎片对象为空
            if(counnt==1){
                fragment = new Home_Fragment();
            }else if(counnt==2) {
                fragment = new Video_Fragment();
            }else if(counnt==3) {
                fragment = new Hot_Fragment();
            }else if(counnt==4) {
                fragment = new Paihb_Fragment();
            }
            bundle = new Bundle();
            bundle.putString("text", strs[counnt - 1]);

            Log.i("fragment","------------碎片为："+fragment);
            fragment.setArguments(bundle);
        }

        if (fragment.isAdded()) {//碎片如果存在
            transaction.hide(cunnFragment);
            transaction.show(fragment);
        } else {
            transaction.hide(cunnFragment);
            transaction.add(R.id.main_layout, fragment);
        }
        transaction.commit();
        cunnFragment = fragment;
    }




}
