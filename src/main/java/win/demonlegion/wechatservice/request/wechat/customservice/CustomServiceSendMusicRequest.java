package win.demonlegion.wechatservice.request.wechat.customservice;

import win.demonlegion.wechatservice.module.customservice.CustomServiceMusicMessage;
import win.demonlegion.wechatservice.module.message.WechatMessage;

import java.io.Serializable;

/**
 * 微信客服音乐消息发送请求
 */
public class CustomServiceSendMusicRequest extends CustomServiceSendRequest implements Serializable {
    private static final long serialVersionUID = 7071376282640815961L;

    private String msgtype;
    private CustomServiceMusicMessage music;

    public CustomServiceSendMusicRequest() {
        setMsgtype(WechatMessage.MSG_TYPE_MUSIC);
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public CustomServiceMusicMessage getMusic() {
        return music;
    }

    public void setMusic(CustomServiceMusicMessage music) {
        this.music = music;
    }
}
