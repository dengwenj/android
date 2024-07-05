package vip.dengwj.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import vip.dengwj.entity.Bill;

@Dao
public interface BillDao {
    // 新增
    @Insert
    void insert(Bill bill);

    // 查询
    @Query("select * from bill where date like :formatDate order by date")
    // date -> '2024-07%'
    List<Bill> query(String formatDate);
}
