package vip.dengwj.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.service.services.FirstService;

public class MainActivity extends AppCompatActivity {

    private static FirstService.MyBinder mService;
    private boolean isService;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 启动服务
     */
    public void handleStartService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        startService(intent);
    }

    /**
     * 停止服务
     */
    public void handleStopService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        stopService(intent);
    }

    /**
     * 绑定服务
     */
    public void handleBindService(View view) {
        Intent intent= new Intent();
        intent.setClass(this, FirstService.class);

        serviceConnection = new MyServiceConnection();
        isService = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     */
    public void handleUnbindService(View view) {
        if (isService && serviceConnection != null) {
            unbindService(serviceConnection);
            mService = null;
        }
    }

    /**
     * 调用服务内部方法
     */
    public void handleServiceInnerMethod(View view) {
        if (mService != null) {
            mService.callMethod();
        }
    }

    private static class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // IBinder service 就是在服务类里面自己 new 的
            Log.d("pumu", "onServiceConnected..." + " name" + name);
            mService = (FirstService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("pumu", "onServiceDisconnected..." + " name" + name);
        }
    };
}