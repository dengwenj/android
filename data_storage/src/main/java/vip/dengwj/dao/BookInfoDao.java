package vip.dengwj.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vip.dengwj.entity.BookInfoEntity;

@Dao
public interface BookInfoDao {
    // 增加
    @Insert
    void insert(BookInfoEntity... bookInfoEntity);

    // 删除
    @Delete
    void delete(BookInfoEntity... bookInfoEntity);

    // 删除全部
    @Query("delete from bookinfoentity")
    void deleteAll();

    @Update
    int update(BookInfoEntity... bookInfoEntity);

    @Query("select * from bookinfoentity")
    List<BookInfoEntity> queryAll();
    
    @Query("select * from bookinfoentity where bookName = :bookName order by id desc limit 1")
    BookInfoEntity queryByName(String bookName);
}
