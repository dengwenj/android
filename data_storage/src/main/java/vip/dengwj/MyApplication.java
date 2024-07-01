package vip.dengwj;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.room.Room;

import java.util.HashMap;
import java.util.Map;

import vip.dengwj.database.BookInfoDatabase;

public class MyApplication extends Application {
    public static MyApplication app;

    public Map<String, String> globalInfo = new HashMap<>();

    public static MyApplication getInstance() {
        return app;
    }

    // 书籍数据库对象
    private BookInfoDatabase bookInfoDatabase;

    // 在 App 启动时调用
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        System.out.println("最先创建");

        bookInfoDatabase = Room.databaseBuilder(this, BookInfoDatabase.class, "book")
                // 允许迁移数据库
                .addMigrations()
                // 允许在主线程中操作数据库（一般不这样做）
                .allowMainThreadQueries()
                .build();
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

    public BookInfoDatabase getBookInfoDatabase() {
        return bookInfoDatabase;
    }
}
