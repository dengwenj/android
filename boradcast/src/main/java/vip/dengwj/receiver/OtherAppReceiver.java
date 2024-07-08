package vip.dengwj.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OtherAppReceiver extends BroadcastReceiver {
    public OtherAppReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("进了");
        // String action = intent.getAction();
        // int year = intent.getIntExtra("year", 0);
        // System.out.println("我接收到了其他应用的广播" + year);
    }
}
