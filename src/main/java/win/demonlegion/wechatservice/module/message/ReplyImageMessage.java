package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 回复图片消息
 */
public class ReplyImageMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = -3993416324241597011L;

    private String MediaId;

    @JSONField(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    public ReplyImageMessage() {
        setMsgType(WechatMessage.MSG_TYPE_IMAGE);
        setCreateTime((System.currentTimeMillis() / 1000) + "");
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
