package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 向微信发送的加密后的消息体
 */
public class ReceiveMessage implements Serializable {
    private static final long serialVersionUID = -7454901331571462592L;

    private String ToUserName;
    private String Encrypt;

    @JSONField(name = "ToUserName")
    public String getToUserName() {
        return ToUserName;
    }

    @JSONField(name = "Encrypt")
    public String getEncrypt() {
        return Encrypt;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public void setEncrypt(String encrypt) {
        Encrypt = encrypt;
    }
}
