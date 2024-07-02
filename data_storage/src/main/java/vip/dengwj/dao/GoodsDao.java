package vip.dengwj.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import vip.dengwj.entity.GoodsInfo;

@Dao
public interface GoodsDao {
    @Insert
    void insert(GoodsInfo goodsInfo);

    @Delete
    void delete(GoodsInfo goodsInfo);

    @Query("delete from goodsinfo")
    void deleteAll();

    @Query("select * from goodsinfo")
    List<GoodsInfo> query();

    @Query("select * from goodsinfo where id = :id")
    GoodsInfo getGoodsById(int id);
}
