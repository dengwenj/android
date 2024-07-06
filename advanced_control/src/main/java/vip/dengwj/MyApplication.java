package vip.dengwj;

import android.app.Application;

import androidx.room.Room;

import java.util.HashMap;
import java.util.Map;

import vip.dengwj.database.BillDatabase;

public class MyApplication extends Application {
    public static MyApplication app;

    public Map<String, Object> globalInfo = new HashMap<>();

    private BillDatabase billDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        billDatabase = Room.databaseBuilder(this, BillDatabase.class, "bill")
                .allowMainThreadQueries()
                .build();
    }

    public static MyApplication getInstance() {
        return app;
    }

    public BillDatabase getBillDatabase() {
        return billDatabase;
    }
}
