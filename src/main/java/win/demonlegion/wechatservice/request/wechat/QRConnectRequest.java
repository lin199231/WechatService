package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 微信获取登录二维码请求
 */
public class QRConnectRequest implements Serializable {
    private static final long serialVersionUID = -4265531742235374869L;

    private String appid;
    private String redirect_uri;
    private String response_type;
    private String scope;
    private String state;

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
}
