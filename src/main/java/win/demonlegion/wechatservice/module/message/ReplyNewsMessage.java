package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 回复图文消息
 */
public class ReplyNewsMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = 8986989946832935005L;

    private String ArticleCount;
    private String Articles;
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public ReplyNewsMessage() {
        setMsgType(WechatMessage.MSG_TYPE_NEWS);
        setCreateTime((System.currentTimeMillis() / 1000) + "");
    }

    @JSONField(name = "ArticleCount")
    public String getArticleCount() {
        return ArticleCount;
    }

    @JSONField(name = "Articles")
    public String getArticles() {
        return Articles;
    }

    @JSONField(name = "Title")
    public String getTitle() {
        return Title;
    }

    @JSONField(name = "Description")
    public String getDescription() {
        return Description;
    }

    @JSONField(name = "PicUrl")
    public String getPicUrl() {
        return PicUrl;
    }

    @JSONField(name = "Url")
    public String getUrl() {
        return Url;
    }

    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }

    public void setArticles(String articles) {
        Articles = articles;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
