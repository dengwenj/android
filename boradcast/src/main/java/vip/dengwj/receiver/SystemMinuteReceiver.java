package vip.dengwj.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SystemMinuteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("每分钟到达的广播");
    }
}
