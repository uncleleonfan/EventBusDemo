package com.example.leon.eventbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Leon on 2016/9/5.
 */
public class PublisherActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);
    }

    public void onPublishEventOnMainThread(View view) {
        MyEvent event = new MyEvent("msg from publisher main thread");
        EventBus.getDefault().post(event);
    }

    public void onPublishEventOnBGThread(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyEvent event = new MyEvent("msg from publisher bg thread");
                EventBus.getDefault().post(event);
            }
        }).start();
    }
}
