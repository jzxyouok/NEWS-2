package com.android.monews.App;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.android.monews.R;
import com.android.monews.data.UpdateManager;

/**
 * 我的 、设置页面
 * <p>
 * Created by My on 2016/7/19 0019.
 */
public class MySetting extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysetting_activity);


        initView();
    }

    private void initView() {
        findViewById(R.id.my_about).setOnClickListener(this);
        findViewById(R.id.my_back).setOnClickListener(this);
        findViewById(R.id.my_collect).setOnClickListener(this);
        findViewById(R.id.my_follow).setOnClickListener(this);
        findViewById(R.id.my_login).setOnClickListener(this);
        findViewById(R.id.my_updata).setOnClickListener(this);
        findViewById(R.id.my_login_im).setOnClickListener(this);
        findViewById(R.id.my_register).setOnClickListener(this);
        findViewById(R.id.my_request).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.my_updata:
                UpdateManager manager = new UpdateManager(MySetting.this);
                // 检查软件更新
                manager.checkUpdate();
                break;
            case R.id.my_back:
                onBackPressed();
                break;
            case R.id.my_collect:
                intent.setClass(this, MyCollect.class);
                startActivity(intent);
                break;
            case R.id.my_about:
                intent.setClass(this, About.class);
                startActivity(intent);
                break;
            case R.id.my_register:
                intent.setClass(this, register.class);
                startActivity(intent);
                break;
            case R.id.my_login:
                intent.setClass(this, Login.class);
                startActivity(intent);
                break;
            case R.id.my_login_im:
                intent.setClass(this, Login.class);
                startActivity(intent);
                break;
            case R.id.my_request:
                share();
                break;
            case R.id.my_follow:
                intent.setClass(this, Login.class);
                startActivity(intent);
                break;

        }

    }


    //调用系统分享功能
    public void share() {
        // 启动分享发送的属性
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain"); // 分享发送的数据类型
        String msg = "推荐给大家";
        // 分享的内容
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        // 目标应用选择对话框的标题
        startActivity(Intent.createChooser(intent, "选择到"));
    }
}
