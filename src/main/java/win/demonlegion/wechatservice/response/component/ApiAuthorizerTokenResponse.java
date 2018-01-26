package win.demonlegion.wechatservice.response.component;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）返回
 */
public class ApiAuthorizerTokenResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -2854369231355198523L;

    private String authorizer_access_token;
    private int expires_in;
    private String authorizer_refresh_token;

    public String getAuthorizer_access_token() {
        return authorizer_access_token;
    }

    public void setAuthorizer_access_token(String authorizer_access_token) {
        this.authorizer_access_token = authorizer_access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getAuthorizer_refresh_token() {
        return authorizer_refresh_token;
    }

    public void setAuthorizer_refresh_token(String authorizer_refresh_token) {
        this.authorizer_refresh_token = authorizer_refresh_token;
    }
}
