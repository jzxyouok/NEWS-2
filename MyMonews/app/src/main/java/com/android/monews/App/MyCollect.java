package com.android.monews.App;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.android.monews.R;

/**
 *
 *  收藏界面
 * Created by My on 2016/7/20 0020.
 */
public class MyCollect extends AppCompatActivity {
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏应用的标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect);

        layout  = (LinearLayout) findViewById(R.id.collect_back);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


}
