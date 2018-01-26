package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 微信公众号刷新用户AccessToken
 */
public class RefreshAccessTokenRequest implements Serializable {
    private static final long serialVersionUID = -2735601308039824305L;

    private String appid;
    private String grant_type;
    private String refresh_token;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
