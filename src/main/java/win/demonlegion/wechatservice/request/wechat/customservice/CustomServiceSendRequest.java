package win.demonlegion.wechatservice.request.wechat.customservice;

import win.demonlegion.wechatservice.request.wechat.WechatRequest;

import java.io.Serializable;

/**
 * 微信客服消息发送请求
 */
public class CustomServiceSendRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = -4236985548546962990L;

    public static final String MSG_TYPE_TEXT = "text";

    private String touser;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }
}
