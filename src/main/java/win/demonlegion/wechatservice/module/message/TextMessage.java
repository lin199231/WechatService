package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 文本消息
 */
public class TextMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = 1853119499450303920L;

    private String Content;
    private String MsgId;

    @JSONField(name = "Content")
    public String getContent() {
        return Content;
    }

    @JSONField(name = "MsgId")
    public String getMsgId() {
        return MsgId;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
