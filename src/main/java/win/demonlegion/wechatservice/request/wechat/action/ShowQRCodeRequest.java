package win.demonlegion.wechatservice.request.wechat.action;

import java.io.Serializable;

/**
 * 通过ticket换取二维码请求
 */
public class ShowQRCodeRequest implements Serializable {
    private static final long serialVersionUID = -6131214502121298652L;

    private String ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
