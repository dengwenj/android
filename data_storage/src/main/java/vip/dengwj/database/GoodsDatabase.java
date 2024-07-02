package vip.dengwj.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import vip.dengwj.dao.CartDao;
import vip.dengwj.dao.GoodsDao;
import vip.dengwj.entity.CartInfo;
import vip.dengwj.entity.GoodsInfo;

@Database(entities = {GoodsInfo.class, CartInfo.class}, version = 2)
public abstract class GoodsDatabase extends RoomDatabase {
    public abstract GoodsDao goodsDao();

    public abstract CartDao cartDao();
}
