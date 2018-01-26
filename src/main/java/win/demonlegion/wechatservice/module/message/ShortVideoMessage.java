package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 小视频消息
 */
public class ShortVideoMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = 2456728850219704094L;

    private String MediaId;
    private String ThumbMediaId;
    private String MsgId;

    @JSONField(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    @JSONField(name = "ThumbMediaId")
    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    @JSONField(name = "MsgId")
    public String getMsgId() {
        return MsgId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
