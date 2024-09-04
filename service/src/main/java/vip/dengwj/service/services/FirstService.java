package vip.dengwj.service.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import vip.dengwj.service.actions.interfaces.ICommunication;

/**
 * 服务的开启方式有两种：
 * 1、startService() 开启服务 ---> stopService() 停止服务
 * 优点：服务可以长期于后台运行，缺点：不能够进行通讯
 * 生命周期：
 * 最基本的生命周期：onCreate() -> onStartCommand() -> onDestroy()
 * 多次启动服务的生命周期，如果服务已经启动时，那么就不会再走 onCreate 方法了。除非执行了 onDestroy()
 * onCreate() -> onStartCommand() -...-> onStartCommand() -> onDestroy()
 *
 * 2、bindService() 绑定服务，如果服务没有启动，自动启动 -> unBindService() 解绑服务
 * 优点：可以进行通讯，缺点：不可以长期于后台运行，如果不解绑则会发生泄漏，如果解绑了，服务将停止运行
 * 生命周期：onCreate() -> onBind() -> onUnbind() -> onDestroy()
 */
public class FirstService extends Service {
    public static final String TAG = "pumu";

    private class MyBinder extends Binder implements ICommunication {
        @Override
        public void callServiceInnerMethod() {
            sayHello();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind。。。");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate...");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand。。。");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy。。。");
    }

    private void sayHello() {
        Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show();
    }
}
