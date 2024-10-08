package vip.dengwj.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.service.actions.interfaces.ICommunication;
import vip.dengwj.service.services.FirstService;

public class MainActivity extends AppCompatActivity {

    private static ICommunication mService;
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
            // TODO unbindService 没执行 onServiceDisconnected
            unbindService(serviceConnection);
        }
    }

    /**
     * 调用服务内部方法
     */
    public void handleServiceInnerMethod(View view) {
        if (mService != null) {
            mService.callServiceInnerMethod();
        }
    }

    private static class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // IBinder service 就是在服务类里面自己 new 的
            Log.d("pumu", "onServiceConnected..." + " name" + name);
            mService = (ICommunication) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("pumu", "onServiceDisconnected..." + " name" + name);
            mService = null;
        }
    };
}