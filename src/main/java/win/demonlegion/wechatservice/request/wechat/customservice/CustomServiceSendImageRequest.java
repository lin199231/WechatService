package win.demonlegion.wechatservice.request.wechat.customservice;

import win.demonlegion.wechatservice.module.customservice.CustomServiceImageMessage;
import win.demonlegion.wechatservice.module.message.WechatMessage;

import java.io.Serializable;

/**
 * 微信客服图片消息发送请求
 */
public class CustomServiceSendImageRequest extends CustomServiceSendRequest implements Serializable {
    private static final long serialVersionUID = -7908675395746137570L;

    private String msgtype;
    private CustomServiceImageMessage image;

    public CustomServiceSendImageRequest() {
        setMsgtype(WechatMessage.MSG_TYPE_IMAGE);
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public CustomServiceImageMessage getImage() {
        return image;
    }

    public void setImage(CustomServiceImageMessage image) {
        this.image = image;
    }
}
