package vip.dengwj.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import vip.dengwj.entity.CartInfo;

@Dao
public interface CartDao {
    @Insert
    void insert(CartInfo cartInfo);

    // 获取 count 总数
    @Query("select sum(count) from cartinfo")
    int getCount();

    // 更新
    @Update
    void update(CartInfo cartInfo);

    // 通过 goodsId 获取数据
    @Query("select * from cartinfo where goodsId = :goodsId")
    CartInfo getCartById(Integer goodsId);
}
