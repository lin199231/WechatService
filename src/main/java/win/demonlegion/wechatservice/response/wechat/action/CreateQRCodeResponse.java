package win.demonlegion.wechatservice.response.wechat.action;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 生成二维码返回
 */
public class CreateQRCodeResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -2759070558501182826L;

    private String ticket;
    private int expire_seconds;
    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
