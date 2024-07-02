package vip.dengwj;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.room.Room;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.dengwj.database.BookInfoDatabase;
import vip.dengwj.database.GoodsDatabase;
import vip.dengwj.entity.GoodsInfo;
import vip.dengwj.util.FileUtil;
import vip.dengwj.util.SharedUtil;

public class MyApplication extends Application {
    public static MyApplication app;

    public Map<String, String> globalInfo = new HashMap<>();

    public static MyApplication getInstance() {
        return app;
    }

    // 书籍数据库对象
    private BookInfoDatabase bookInfoDatabase;

    private GoodsDatabase goodsDatabase;

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

        goodsDatabase = Room.databaseBuilder(this, GoodsDatabase.class, "goods")
                .addMigrations()
                .allowMainThreadQueries()
                .build();

        // 初始化商品信息
        initGoodsInfo();
    }

    private void initGoodsInfo() {
        boolean first = SharedUtil.getInstance(this).getBoolean("first", true);
        // 获取当前App的私有下载路径
        String directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar;
        if (first) {
            // 模拟网络图片下载
            List<GoodsInfo> list = GoodsInfo.getDefaultList();
            for (GoodsInfo info : list) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), info.pic);
                String path = directory + info.id + ".jpg";
                // 往存储卡保存商品图片
                FileUtil.saveImage(path, bitmap);
                // 回收位图对象
                bitmap.recycle();
                info.picPath = path;
            }
            // 打开数据库，把商品信息插入到表中
            for (GoodsInfo info : list) {
                getGoodsDatabase().goodsDao().insert(info);
            }
            // 把是否首次打开写入共享参数
            SharedUtil.getInstance(this).setBoolean("first", false);
        }
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

    public GoodsDatabase getGoodsDatabase() {
        return goodsDatabase;
    };
}
