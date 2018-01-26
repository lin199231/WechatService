package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 微信获取ticket请求
 */
public class GetTicketRequest implements Serializable {
    private static final long serialVersionUID = -405386606432509375L;

    private String access_token;
    private String type;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
