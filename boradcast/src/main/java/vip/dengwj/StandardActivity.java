package vip.dengwj;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.receiver.StandardReceiver;

public class StandardActivity extends AppCompatActivity implements View.OnClickListener {
    private StandardReceiver standardReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        findViewById(R.id.send_boradcast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent integer = new Intent(StandardReceiver.PUMU);
        // 发送广播
        sendBroadcast(integer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 注册广播
        // 创建一个意图过滤器，只处理 PUMU 的广播
        standardReceiver = new StandardReceiver();
        IntentFilter filter = new IntentFilter(StandardReceiver.PUMU);
        // 注册接收器，注册之后才能正常接收广播
        registerReceiver(standardReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 销毁广播
        unregisterReceiver(standardReceiver);
    }
}