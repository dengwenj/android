package vip.dengwj.domain;

public class RecyclerItem {
    private String title;

    public RecyclerItem() {
    }

    public RecyclerItem(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return "RecyclerItem{title = " + title + "}";
    }
}
