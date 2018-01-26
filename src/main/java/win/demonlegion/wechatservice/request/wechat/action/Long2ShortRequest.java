package win.demonlegion.wechatservice.request.wechat.action;

import win.demonlegion.wechatservice.request.wechat.WechatRequest;

import java.io.Serializable;

/**
 * 长连接转短连接请求
 */
public class Long2ShortRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = 6599739911719822473L;

    public static final String ACTION_NAME_LONG2SHORT = "long2short";

    private String action;
    private String long_url;

    public Long2ShortRequest() {
        setAction(ACTION_NAME_LONG2SHORT);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }
}
