package vip.dengwj;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import vip.dengwj.receiver.OtherAppReceiver;
import vip.dengwj.receiver.StandardReceiver;

public class StandardActivity extends AppCompatActivity implements View.OnClickListener {
    private StandardReceiver standardReceiver;
    private OtherAppReceiver otherAppReceiver;

    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        findViewById(R.id.send_boradcast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent integer = new Intent("vip.dengwj.TEST");
        Bundle bundle = new Bundle();
        bundle.putInt("year", 2023);
        integer.putExtras(bundle);
        // 发送广播
        sendBroadcast(integer);

        // Intent intent = new Intent("vip.dengwj.TEST");
        // String fullName = "vip.dengwj.receiver.OtherAppReceiver";
        // ComponentName componentName = new ComponentName(this, fullName);
        // intent.setComponent(componentName);
        // sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 注册广播
        // 创建一个意图过滤器，只处理 PUMU 的广播
        // standardReceiver = new StandardReceiver();
        // IntentFilter filter = new IntentFilter(StandardReceiver.PUMU);
        // // 注册接收器，注册之后才能正常接收广播
        // registerReceiver(standardReceiver, filter);

        otherAppReceiver = new OtherAppReceiver();
        IntentFilter filter1 = new IntentFilter();
        filter1.addAction("vip.dengwj.TEST");
        registerReceiver(otherAppReceiver, filter1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 销毁广播
        // unregisterReceiver(standardReceiver);
        // unregisterReceiver(otherAppReceiver);

        unregisterReceiver(br);
    }

    @Override
    public void addMenuProvider(@NonNull MenuProvider provider, @NonNull LifecycleOwner owner, @NonNull Lifecycle.State state) {
        // BroadcastReceiver br = new MyBroadcastReceiver();
        // IntentFilter filter = new IntentFilter("APP_SPECIFIC_BROADCAST");
        // boolean listenToBroadcastsFromOtherApps = false;
        // int receiverFlags;
        // if (listenToBroadcastsFromOtherApps) {
        //     receiverFlags = ContextCompat.RECEIVER_EXPORTED;
        // } else {
        //     receiverFlags = ContextCompat.RECEIVER_NOT_EXPORTED;
        // }
        // ContextCompat.registerReceiver(this, br, filter, receiverFlags);
    }
}