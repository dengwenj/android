package vip.dengwj;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
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
