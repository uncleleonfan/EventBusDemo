package com.example.leon.eventbusdemo;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Leon on 0013.
 */

public class MyObject {


    private static final String TAG = "MyObject";


    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }


    public void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostingEvent(MyEvent myEvent){
        Log.d(TAG, "onPostingEvent: " + myEvent.msg);
    }
}
