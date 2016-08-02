package com.fanyafeng.okhttputil.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.okhttputil.R;
import com.fanyafeng.okhttputil.BaseActivity;
import com.fanyafeng.okhttputil.util.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class GetActivity extends BaseActivity {
    private TextView ivGet1;
    private TextView ivGet2;
    private Handler handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_get);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //toolbar_center_title.setText(getString(R.string.title_activity_get));
    }

    //初始化UI空间
    private void initView() {
        ivGet1 = (TextView) findViewById(R.id.ivGet1);
        ivGet2 = (TextView) findViewById(R.id.ivGet2);
    }

    //初始化数据
    private void initData() {
        handle = new Handler(getMainLooper());
    }

    private void getRequest2() {
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        OkHttpUtil.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ivGet2.setText(s);
//                    }
//                });
                handle.post(new Runnable() {
                    @Override
                    public void run() {
                        ivGet2.setText(s);
                    }
                });
            }
        });
    }

    private void getRequest1() {
        Thread thread = new Thread(new GetRunnable());
        thread.start();
    }

    class GetRunnable implements Runnable {

        @Override
        public void run() {
            Request request = new Request.Builder()
                    .url("https://www.baidu.com")
                    .build();
            try {
                Response response = OkHttpUtil.getOkHttpClient().newCall(request).execute();
                Log.d("get", response.body().toString());
                Message message = Message.obtain();
                message.what = 1;
                message.obj = response.body().string();
                handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("get", "加载失败");
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    ivGet1.setText((String) msg.obj);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnGet1:
                getRequest1();
//                Snackbar.make(v, "测试点击", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btnGet2:
                getRequest2();
                break;
        }
    }
}
