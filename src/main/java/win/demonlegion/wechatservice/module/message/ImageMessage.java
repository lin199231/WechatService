package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 图片消息
 */
public class ImageMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = 8386424194742656100L;

    private String PicUrl;
    private String MediaId;
    private String MsgId;

    @JSONField(name = "PicUrl")
    public String getPicUrl() {
        return PicUrl;
    }

    @JSONField(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    @JSONField(name = "MsgId")
    public String getMsgId() {
        return MsgId;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
