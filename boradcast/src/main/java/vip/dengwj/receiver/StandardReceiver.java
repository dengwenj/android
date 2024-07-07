package vip.dengwj.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

import vip.dengwj.Callback;

// 定义一个标准广播的接收器
public class StandardReceiver extends BroadcastReceiver {
    public static final String PUMU = "pumu";

    // 一旦接收到标准广播，马上触发接收器的 onReceive
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && Objects.equals(intent.getAction(), PUMU)) {
            Bundle extras = intent.getExtras();
            assert extras != null;
            int year = extras.getInt("year");
            System.out.println("接收到了广播");
            // 这里面可以写一些回调，去调用
            new Callback().method(year);
        }
    }
}
