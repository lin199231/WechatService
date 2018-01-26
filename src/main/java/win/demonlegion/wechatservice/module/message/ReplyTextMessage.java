package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 回复文本消息
 */
public class ReplyTextMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = -4685344988031121387L;

    private String Content;

    public ReplyTextMessage() {
        setMsgType(WechatMessage.MSG_TYPE_TEXT);
        setCreateTime((System.currentTimeMillis() / 1000) + "");
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @JSONField(name = "Content")
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
