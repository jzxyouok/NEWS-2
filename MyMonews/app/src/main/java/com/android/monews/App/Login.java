package com.android.monews.App;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.android.monews.R;
import com.android.monews.data.VideoOkhttp;

/**
 * register
 * <p>
 * 登录
 * Created by My on 2016/7/20 0020.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText et_num, et_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();
    }

    private void initView() {
        findViewById(R.id.login_back).setOnClickListener(this);
        et_num = (EditText) findViewById(R.id.login_num);
        et_key = (EditText) findViewById(R.id.login_key);
        findViewById(R.id.login_yes).setOnClickListener(this);
        findViewById(R.id.login_register).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_back:
                onBackPressed();
                break;
            case R.id.login_yes:

                final String username = et_num.getText().toString().trim();
                final String password = et_key.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    VideoOkhttp.showShort(this, "手机号或密码不能为空");
                } else {
                    VideoOkhttp.showShort(this, "程序猿还在加班中。。。。");
                }
                break;
            case R.id.login_register:
                Intent intent = new Intent(this, register.class);
                startActivity(intent);
                break;

        }


    }
}
