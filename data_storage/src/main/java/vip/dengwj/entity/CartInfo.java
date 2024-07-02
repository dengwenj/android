package vip.dengwj.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//购物车信息
@Entity
public class CartInfo {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    // 商品编号
    public int goodsId;
    // 商品数量
    public int count;

    public CartInfo(){}

    public CartInfo(Integer id, int goodsId, int count) {
        this.id = id;
        this.goodsId = goodsId;
        this.count = count;
    }
}
