package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 回复语音消息
 */
public class ReplyVoiceMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = 1165553752471545142L;

    private String MediaId;

    public ReplyVoiceMessage() {
        setMsgType(WechatMessage.MSG_TYPE_VOICE);
        setCreateTime((System.currentTimeMillis() / 1000) + "");
    }

    @JSONField(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
