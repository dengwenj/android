package vip.dengwj.service.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import vip.dengwj.service.interfaces.ICommunication;

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
