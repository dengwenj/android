package vip.dengwj.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vip.dengwj.OrderBroadcastActivity;

public class OrderBReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(OrderBroadcastActivity.ORDER_ACTION)) {
            System.out.println("有序广播B");
            abortBroadcast(); // 中断广播，后面的广播接收不到
        }
    }
}
