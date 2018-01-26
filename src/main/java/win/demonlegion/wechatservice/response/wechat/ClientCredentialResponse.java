package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 微信公众号通用凭据返回
 */
public class ClientCredentialResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -8162925933146735966L;

    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
