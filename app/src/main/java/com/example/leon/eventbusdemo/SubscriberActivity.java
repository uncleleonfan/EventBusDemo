package com.example.leon.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SubscriberActivity extends AppCompatActivity {

    private static final String TAG = "SubscriberActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        startSubscriberService();
    }

    private void startSubscriberService() {
        Intent intent = new Intent(this, SubscriberService.class);
        startService(intent);
    }

    public void onStartPublishActivity(View view) {
        Intent intent = new Intent(this, PublisherActivity.class);
        startActivity(intent);
    }

/*
    Modifier must be public.
    @Subscribe
    private void onEventPrivate(MyEvent event) {
        Log.d(TAG, "onEventPrivate: " + event.msg);
    }
*/

/*
    Must have exactly 1 parameter.
    @Subscribe
    public void onEvent(MyEvent event, int test) {
        Log.d(TAG, "onEvent: " + event.msg);
    }
*/


    @Subscribe
    public void onEvent(MyEvent event) {
        Log.d(TAG, "onEvent: " + event.msg);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostingEvent(MyEvent event) {
        Log.d(TAG, "onPostingEvent: " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(MyEvent event) {
        Log.d(TAG, "onMainEvent: " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEvent(MyEvent event) {
        Log.d(TAG, "onBackgroundEvent: " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncEvent(MyEvent event) {
        Log.d(TAG, "onAsyncEvent: " + Thread.currentThread().getName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
