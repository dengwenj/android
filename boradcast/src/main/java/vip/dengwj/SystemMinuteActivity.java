package vip.dengwj;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.receiver.SystemMinuteReceiver;

public class SystemMinuteActivity extends AppCompatActivity {
    private SystemMinuteReceiver systemMinuteReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_minute);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 分钟到达的广播
        systemMinuteReceiver = new SystemMinuteReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        registerReceiver(systemMinuteReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 取消注册
        unregisterReceiver(systemMinuteReceiver);
    }
}