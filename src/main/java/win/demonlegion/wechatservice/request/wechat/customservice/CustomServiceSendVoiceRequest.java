package win.demonlegion.wechatservice.request.wechat.customservice;

import win.demonlegion.wechatservice.module.customservice.CustomServiceVoiceMessage;
import win.demonlegion.wechatservice.module.message.WechatMessage;

import java.io.Serializable;

/**
 * 微信客服语音消息发送请求
 */
public class CustomServiceSendVoiceRequest extends CustomServiceSendRequest implements Serializable {
    private static final long serialVersionUID = 1781343629744156762L;

    private String msgtype;
    private CustomServiceVoiceMessage voice;

    public CustomServiceSendVoiceRequest() {
        setMsgtype(WechatMessage.MSG_TYPE_VOICE);
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public CustomServiceVoiceMessage getVoice() {
        return voice;
    }

    public void setVoice(CustomServiceVoiceMessage voice) {
        this.voice = voice;
    }
}
