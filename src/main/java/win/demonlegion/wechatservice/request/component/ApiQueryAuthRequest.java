package win.demonlegion.wechatservice.request.component;

import java.io.Serializable;

/**
 * 微信第三方平台换取接口调用凭据请求
 */
public class ApiQueryAuthRequest extends WechatComponentRequest implements Serializable {
    private static final long serialVersionUID = -8579115827080109534L;

    private String component_appid;
    private String authorization_code;

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }

    public String getAuthorization_code() {
        return authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }
}
