package vip.dengwj.custom_control;

import android.app.Application;
import android.os.Handler;

public class App extends Application {
    public static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler();
    }

    public static Handler getHandler() {
        return handler;
    }
}
