package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 接收微信发送的加密的消息体
 */
public class SendMessage implements Serializable {
    private static final long serialVersionUID = -2482971891761965764L;

    private String Encrypt;
    private String MsgSignature;
    private String TimeStamp;
    private String Nonce;

    @JSONField(name = "Encrypt")
    public String getEncrypt() {
        return Encrypt;
    }

    @JSONField(name = "MsgSignature")
    public String getMsgSignature() {
        return MsgSignature;
    }

    @JSONField(name = "TimeStamp")
    public String getTimeStamp() {
        return TimeStamp;
    }

    @JSONField(name = "Nonce")
    public String getNonce() {
        return Nonce;
    }

    public void setEncrypt(String encrypt) {
        Encrypt = encrypt;
    }

    public void setMsgSignature(String msgSignature) {
        MsgSignature = msgSignature;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public void setNonce(String nonce) {
        Nonce = nonce;
    }
}
