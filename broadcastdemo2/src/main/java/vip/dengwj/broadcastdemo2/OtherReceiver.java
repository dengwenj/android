package vip.dengwj.broadcastdemo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OtherReceiver extends BroadcastReceiver {
    public OtherReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("pumu", "接收到了数据");
    }
}
