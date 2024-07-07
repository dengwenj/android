package vip.dengwj;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StaticBroadcastActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String STATIC_BROADCAST = "staticBroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_broadcast_avtivity);

        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 发送广播
        Intent intent = new Intent(STATIC_BROADCAST);
        // 静态注册
        // 完整类名
        String fullName = "vip.dengwj.receiver.StaticReceiver";
        ComponentName componentName = new ComponentName(this, fullName);
        intent.setComponent(componentName);
        sendBroadcast(intent);
    }
}