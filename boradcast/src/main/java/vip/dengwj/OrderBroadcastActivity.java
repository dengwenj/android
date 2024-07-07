package vip.dengwj;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.receiver.OrderAReceiver;
import vip.dengwj.receiver.OrderBReceiver;

public class OrderBroadcastActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String ORDER_ACTION = "orderAction";


    private OrderAReceiver orderAReceiver;
    private OrderBReceiver orderBReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_broadcast);

        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 发送有序广播
        Intent intent = new Intent(ORDER_ACTION);
        sendOrderedBroadcast(intent, null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 多个接收器处理有序广播的顺序规则为：
        // 1、优先级越大的接收器，越早收到有序广播
        // 2、优先级相同的时候，越早注册的接收器越早收到有序广播
        // 注册广播
        orderAReceiver = new OrderAReceiver();
        IntentFilter filter = new IntentFilter(ORDER_ACTION);
        filter.setPriority(1);
        registerReceiver(orderAReceiver, filter);

        orderBReceiver = new OrderBReceiver();
        IntentFilter filterB = new IntentFilter(ORDER_ACTION);
        filterB.setPriority(2);
        registerReceiver(orderBReceiver, filterB);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 销毁广播
        unregisterReceiver(orderAReceiver);
        unregisterReceiver(orderBReceiver);
    }
}