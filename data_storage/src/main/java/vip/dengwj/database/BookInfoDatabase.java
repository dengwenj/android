package vip.dengwj.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import vip.dengwj.dao.BookInfoDao;
import vip.dengwj.entity.BookInfoEntity;

// entities 表示该数据库有哪些表，version 表示数据库的版本号
// exportSchema 表示是否导出数据库信息的 json 串
@Database(entities = {BookInfoEntity.class}, version = 1, exportSchema = true)
public abstract class BookInfoDatabase extends RoomDatabase {
    public abstract BookInfoDao bookInfoDao();
}
