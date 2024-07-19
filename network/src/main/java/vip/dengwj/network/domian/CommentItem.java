package vip.dengwj.network.domian;

public class CommentItem {
    private String articleId;
    private String commentContent;

    public CommentItem() {
    }

    public CommentItem(String articleId, String commentContent) {
        this.articleId = articleId;
        this.commentContent = commentContent;
    }

    /**
     * 获取
     * @return articleId
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * 设置
     * @param articleId
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取
     * @return commentContent
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置
     * @param commentContent
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String toString() {
        return "CommentItem{articleId = " + articleId + ", commentContent = " + commentContent + "}";
    }
}
