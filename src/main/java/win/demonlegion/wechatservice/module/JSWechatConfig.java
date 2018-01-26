package win.demonlegion.wechatservice.module;

import java.io.Serializable;

/**
 * JSAPI的配置参数实体类
 */
public class JSWechatConfig implements Serializable {
    private static final long serialVersionUID = -6102783380793002775L;

    public static final String CACHE_WECHAT_TICKET = "WECHAT_TICKET";

    // 微信公众号appId
    private String appId;
    // 生成签名用随机字符串
    private String nonceStr;
    // 时间戳
    private long timestamp;
    // 签名
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
