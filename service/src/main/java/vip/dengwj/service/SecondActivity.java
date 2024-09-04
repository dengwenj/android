package vip.dengwj.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.service.actions.interfaces.ISecond;
import vip.dengwj.service.services.SecondService;

/**
 * 混合开启服务方式：
 * 1、开启服务 -> 为了确保服务可以长期在后台运行
 * 2、绑定服务 -> 为了可以进行通讯
 * 3、调用服务内部方法，执行一系列操作
 * 4、退出 Activity，要记得解绑服务 -> 释放资源
 * 5、如果不使用服务了，要让服务停止，那么就调用 stopService
 */
public class SecondActivity extends AppCompatActivity {

    private boolean isBindService;
    private ISecond bindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    /**
     * 开启服务
     *
     * @param view
     */
    public void handleStartService2(View view) {
        Intent intent = new Intent(this, SecondService.class);
        startService(intent);
    }

    /**
     * 绑定服务
     *
     * @param view
     */
    public void handleBindService2(View view) {
        Intent intent = new Intent(this, SecondService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                bindService = (ISecond) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bindService = null;
                serviceConnection = null;
            }
        };
        isBindService = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection;

    /**
     * 调用服务内部方法
     *
     * @param view
     */
    public void handleServiceInnerMethod2(View view) {
        if (bindService != null) {
            bindService.callMethod();
        }
    }

    /**
     * 解绑服务
     *
     * @param view
     */
    public void handleUnbindService2(View view) {
        if (isBindService && serviceConnection != null) {
            unbindService(serviceConnection);
            bindService = null;
            serviceConnection = null;
        }
    }

    /**
     * 停止服务
     *
     * @param view
     */
    public void handleStopService2(View view) {
        serviceConnection = null;
        bindService = null;
        isBindService = false;
    }
}