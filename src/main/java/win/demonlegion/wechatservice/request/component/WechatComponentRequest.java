package win.demonlegion.wechatservice.request.component;

/**
 * 微信第三方平台服务通用请求
 */
public class WechatComponentRequest {
    protected String component_access_token;

    public String getComponent_access_token() {
        return component_access_token;
    }

    public void setComponent_access_token(String component_access_token) {
        this.component_access_token = component_access_token;
    }
}
