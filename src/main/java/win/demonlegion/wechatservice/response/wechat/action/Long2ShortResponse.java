package win.demonlegion.wechatservice.response.wechat.action;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 长连接转短连接返回
 */
public class Long2ShortResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -7804554491389488327L;

    private String short_url;

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }
}
