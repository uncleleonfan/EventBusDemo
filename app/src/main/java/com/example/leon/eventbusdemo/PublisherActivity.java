package com.example.leon.eventbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Leon on 0013.
 */

public class PublisherActivity extends AppCompatActivity {

    private static final String TAG = "PublisherActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);
    }

    /**
     * 在主线程中发布事件
     * @param view
     */
    public void onPublishEventOnMainThread(View view) {
        Log.d(TAG, "onPublishEventOnMainThread: ");
        MyEvent event = new MyEvent("主线程的消息");
        EventBus.getDefault().post(event);
    }

    /**
     * 在子线程中发送事件
     * @param view
     */
    public void onPublishEventOnBGThread(View view) {
        Log.d(TAG, "onPublishEventOnBGThread: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyEvent event = new MyEvent("后台线程的消息");
                EventBus.getDefault().post(event);
            }
        }).start();
    }
}
