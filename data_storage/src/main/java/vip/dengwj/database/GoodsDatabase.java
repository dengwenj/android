package vip.dengwj.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import vip.dengwj.dao.GoodsDao;
import vip.dengwj.entity.GoodsInfo;

@Database(entities = {GoodsInfo.class}, version = 1)
public abstract class GoodsDatabase extends RoomDatabase {
    public abstract GoodsDao goodsDao();
}
