package vip.dengwj.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BookInfoEntity {
    // autoGenerate 自增长
    @PrimaryKey(autoGenerate = true)
    private Long id;

    // 书籍名称
    private String bookName;

    // 作者
    private String author;

    // 出版社
    private String press;

    // 价格
    private double price;


    public BookInfoEntity() {
    }

    public BookInfoEntity(Long id, String bookName, String author, String press, double price) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.press = press;
        this.price = price;
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
     * @return bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置
     * @param bookName
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取
     * @return press
     */
    public String getPress() {
        return press;
    }

    /**
     * 设置
     * @param press
     */
    public void setPress(String press) {
        this.press = press;
    }

    /**
     * 获取
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "BookInfo{id = " + id + ", bookName = " + bookName + ", author = " + author + ", press = " + press + ", price = " + price + "}";
    }
}
