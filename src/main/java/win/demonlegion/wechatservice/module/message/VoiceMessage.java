package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 语音消息
 */
public class VoiceMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = -3954907129065521680L;

    private String MediaId;
    private String Format;
    private String Recognition; // 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段
    private String MsgId;

    @JSONField(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    @JSONField(name = "Format")
    public String getFormat() {
        return Format;
    }

    @JSONField(name = "Recognition")
    public String getRecognition() {
        return Recognition;
    }

    @JSONField(name = "MsgId")
    public String getMsgId() {
        return MsgId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
