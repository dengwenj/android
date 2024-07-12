package vip.dengwj.custom_control;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class App extends Application {
    private static Handler handler;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }
}
