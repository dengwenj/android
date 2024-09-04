package vip.dengwj.service.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import vip.dengwj.service.actions.interfaces.ISecond;

public class SecondService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("pumu", "onCreate。。。");
    }

    private class myBinder extends Binder implements ISecond {

        @Override
        public void callMethod() {
            sayHello();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("pumu", "onBind...");
        return new myBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("pumu", "onStartCommand。。。");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("pumu", "onUnbind。。。");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("pumu", "onDestroy...");
    }

    private void sayHello() {
        Toast.makeText(this, "你好啊", Toast.LENGTH_SHORT).show();
    }
}
