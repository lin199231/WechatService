package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 微信获取ticket返回
 */
public class GetTicketResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = 5315106568430083440L;

    private String ticket;
    private int expires_in;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
