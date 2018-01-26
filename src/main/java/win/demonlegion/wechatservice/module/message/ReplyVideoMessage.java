package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 回复视频消息
 */
public class ReplyVideoMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = 5438206205624417181L;

    private String MediaId;
    private String Title;
    private String Description;

    public ReplyVideoMessage() {
        setMsgType(WechatMessage.MSG_TYPE_VIDEO);
        setCreateTime((System.currentTimeMillis() / 1000) + "");
    }

    @JSONField(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    @JSONField(name = "Title")
    public String getTitle() {
        return Title;
    }

    @JSONField(name = "Description")
    public String getDescription() {
        return Description;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
