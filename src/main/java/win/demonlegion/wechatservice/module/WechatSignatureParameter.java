package win.demonlegion.wechatservice.module;

import java.io.Serializable;

/**
 * 生成微信签名的参数
 */
public class WechatSignatureParameter implements Serializable {
    private static final long serialVersionUID = 295442369406806292L;

    private String jsapi_ticket;
    private String noncestr;
    private long timestamp;
    private String url;

    public String getJsapi_ticket() {
        return jsapi_ticket;
    }

    public void setJsapi_ticket(String jsapi_ticket) {
        this.jsapi_ticket = jsapi_ticket;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
