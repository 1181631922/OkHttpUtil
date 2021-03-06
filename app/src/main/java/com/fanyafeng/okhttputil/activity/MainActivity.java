package com.fanyafeng.okhttputil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.fanyafeng.okhttputil.R;
import com.fanyafeng.okhttputil.BaseActivity;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isSetNavigationIcon = false;
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
//        title = getString(R.string.title_activity_main);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar_center_title.setText(getString(R.string.title_activity_main));
    }

    //初始化UI空间
    private void initView() {

    }

    //初始化数据
    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnGet:
                startActivity(new Intent(this, GetActivity.class));
                break;
            case R.id.btnPost:
                startActivity(new Intent(this,PostActivity.class));
                break;
        }
    }
}
