package win.demonlegion.wechatservice.request.agent;

import java.io.Serializable;

/**
 * 获取代公众号用户授权AccessToken请求
 */
public class AccessTokenRequest implements Serializable {
    private static final long serialVersionUID = 2537516367235211198L;

    private String appid;
    private String code;
    private String grant_type;
    private String component_appid;
    private String component_access_token;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }

    public String getComponent_access_token() {
        return component_access_token;
    }

    public void setComponent_access_token(String component_access_token) {
        this.component_access_token = component_access_token;
    }
}
