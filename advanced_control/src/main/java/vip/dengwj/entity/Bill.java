package vip.dengwj.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Bill {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String date;

    private int billIncomeExpenditure;

    private String desc;

    private double amount;


    public Bill() {
    }

    public Bill(Long id, String date, int billIncomeExpenditure, String desc, double amount) {
        this.id = id;
        this.date = date;
        this.billIncomeExpenditure = billIncomeExpenditure;
        this.desc = desc;
        this.amount = amount;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取
     * @return billIncomeExpenditure
     */
    public int getBillIncomeExpenditure() {
        return billIncomeExpenditure;
    }

    /**
     * 设置
     * @param billIncomeExpenditure
     */
    public void setBillIncomeExpenditure(int billIncomeExpenditure) {
        this.billIncomeExpenditure = billIncomeExpenditure;
    }

    /**
     * 获取
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 设置
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return "Bill{id = " + id + ", date = " + date + ", billIncomeExpenditure = " + billIncomeExpenditure + ", desc = " + desc + ", amount = " + amount + "}";
    }
}
