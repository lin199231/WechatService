package win.demonlegion.wechatservice.request.wechat.customservice;

import win.demonlegion.wechatservice.module.customservice.CustomServiceVideoMessage;
import win.demonlegion.wechatservice.module.message.WechatMessage;

import java.io.Serializable;

/**
 * 微信客服视频消息发送请求
 */
public class CustomServiceSendVideoRequest extends CustomServiceSendRequest implements Serializable {
    private static final long serialVersionUID = -5580709638139250369L;

    private String msgtype;
    private CustomServiceVideoMessage video;

    public CustomServiceSendVideoRequest() {
        setMsgtype(WechatMessage.MSG_TYPE_VIDEO);
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public CustomServiceVideoMessage getVideo() {
        return video;
    }

    public void setVideo(CustomServiceVideoMessage video) {
        this.video = video;
    }
}
