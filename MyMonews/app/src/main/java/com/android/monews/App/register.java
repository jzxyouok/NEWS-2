package com.android.monews.App;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.monews.R;
import com.android.monews.data.VideoOkhttp;

/**
 * 主持界面
 * Created by My on 2016/7/20 0020.
 */
public class register extends AppCompatActivity implements View.OnClickListener {

    private EditText et_num, et_no, et_key;
    private boolean CheckBox = true;
    private TimeCount time;
    private Button btn, btn_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
        super.onCreate(savedInstanceState);
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

        setContentView(R.layout.register);

        initView();


    }

    private void initView() {
        et_key = (EditText) findViewById(R.id.register_key);
        et_no = (EditText) findViewById(R.id.register_no);
        et_num = (EditText) findViewById(R.id.register_num);

        btn_2 = (Button) findViewById(R.id.register_btn);
        btn_2.setOnClickListener(this);

        findViewById(R.id.register_login).setOnClickListener(this);
        findViewById(R.id.register_webview).setOnClickListener(this);
        findViewById(R.id.regsiter_back).setOnClickListener(this);

        btn = (Button) findViewById(R.id.register_login_yes);
        btn.setOnClickListener(this);

        findViewById(R.id.register_yes).setOnClickListener(this);

        findViewById(R.id.login_wb).setOnClickListener(this);
        findViewById(R.id.login_wx).setOnClickListener(this);
        findViewById(R.id.lgin_qq).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.regsiter_back:
                onBackPressed();
                break;
            case R.id.register_btn:
                String num = et_num.getText().toString().trim();
                if (TextUtils.isEmpty(num)) {
                    VideoOkhttp.showShort(this, "请输入手机号码 ");
                } else {
                    if (isMobileNO(num)) {
                        VideoOkhttp.showShort(this, "验证码已发送");
                     time.start();//开始计时
                    }

                    VideoOkhttp.showShort(this, "请输入正确手机号码");
                }
                break;

            case R.id.register_login_yes:

                final String username = et_num.getText().toString().trim();
                final String password = et_key.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    VideoOkhttp.showShort(this, "手机号或密码不能为空");

                } else {


                }

                break;

        }


    }


    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }


    //
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            btn_2.setText("重新验证");
            btn_2.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            btn_2.setClickable(false);//防止重复点击
            btn_2.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}

































