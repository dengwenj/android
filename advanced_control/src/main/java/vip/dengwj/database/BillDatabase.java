package vip.dengwj.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import vip.dengwj.dao.BillDao;
import vip.dengwj.entity.Bill;

@Database(entities = {Bill.class}, version = 1)
public abstract class BillDatabase extends RoomDatabase {
    public abstract BillDao billDao();
}
