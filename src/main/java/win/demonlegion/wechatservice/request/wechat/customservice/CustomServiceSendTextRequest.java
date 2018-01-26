package win.demonlegion.wechatservice.request.wechat.customservice;

import win.demonlegion.wechatservice.module.customservice.CustomServiceTextMessage;
import win.demonlegion.wechatservice.module.message.WechatMessage;

import java.io.Serializable;

/**
 * 微信客服文本消息发送请求
 */
public class CustomServiceSendTextRequest extends CustomServiceSendRequest implements Serializable {
    private static final long serialVersionUID = -1646230890122520260L;

    private String msgtype;
    private CustomServiceTextMessage text;

    public CustomServiceSendTextRequest() {
        setMsgtype(WechatMessage.MSG_TYPE_TEXT);
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public CustomServiceTextMessage getText() {
        return text;
    }

    public void setText(CustomServiceTextMessage text) {
        this.text = text;
    }
}
