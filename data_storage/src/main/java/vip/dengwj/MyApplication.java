package vip.dengwj;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {
    public static MyApplication app;

    public Map<String, String> globalInfo = new HashMap<>();

    public static MyApplication getInstance() {
        return app;
    }

    // 在 App 启动时调用
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        System.out.println("最先创建");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    // 在配置改变时调用，例如从竖屏变成横屏
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("在配置改变时调用，例如从竖屏变成横屏");
    }
}
