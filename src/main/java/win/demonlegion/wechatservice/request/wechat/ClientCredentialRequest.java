package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 微信公众号通用凭据请求
 */
public class ClientCredentialRequest implements Serializable {
    private static final long serialVersionUID = -68245347392984700L;

    private String grant_type;
    private String appid;
    private String secret;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
