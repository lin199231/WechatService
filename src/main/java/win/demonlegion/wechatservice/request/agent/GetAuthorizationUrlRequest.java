package win.demonlegion.wechatservice.request.agent;

import java.io.Serializable;

/**
 * 获取代公众号授权链接请求
 */
public class GetAuthorizationUrlRequest implements Serializable {
    private static final long serialVersionUID = -5044720140702471861L;

    private String appid;
    private String redirect_uri;
    private String response_type;
    private String scope;
    private String state;
    private String component_appid;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }
}
