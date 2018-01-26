package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 链接消息
 */
public class LinkMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = 3110581222015154785L;

    private String Title;
    private String Description;
    private String Url;
    private String MsgId;

    @JSONField(name = "Title")
    public String getTitle() {
        return Title;
    }

    @JSONField(name = "Description")
    public String getDescription() {
        return Description;
    }

    @JSONField(name = "Url")
    public String getUrl() {
        return Url;
    }

    @JSONField(name = "MsgId")
    public String getMsgId() {
        return MsgId;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
