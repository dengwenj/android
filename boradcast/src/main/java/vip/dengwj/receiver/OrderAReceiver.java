package vip.dengwj.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vip.dengwj.OrderBroadcastActivity;

public class OrderAReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(OrderBroadcastActivity.ORDER_ACTION)) {
            System.out.println("有序广播A");
        }
    }
}
