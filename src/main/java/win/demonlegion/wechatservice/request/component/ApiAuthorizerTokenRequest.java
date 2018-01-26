package win.demonlegion.wechatservice.request.component;

import java.io.Serializable;

/**
 * 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）请求
 */
public class ApiAuthorizerTokenRequest extends WechatComponentRequest implements Serializable {
    private static final long serialVersionUID = 6667207790766786633L;

    private String component_appid;
    private String authorizer_appid;
    private String authorizer_refresh_token;

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }

    public String getAuthorizer_appid() {
        return authorizer_appid;
    }

    public void setAuthorizer_appid(String authorizer_appid) {
        this.authorizer_appid = authorizer_appid;
    }

    public String getAuthorizer_refresh_token() {
        return authorizer_refresh_token;
    }

    public void setAuthorizer_refresh_token(String authorizer_refresh_token) {
        this.authorizer_refresh_token = authorizer_refresh_token;
    }
}
