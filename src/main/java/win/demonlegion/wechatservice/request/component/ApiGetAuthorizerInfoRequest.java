package win.demonlegion.wechatservice.request.component;

import java.io.Serializable;

/**
 * 获取授权方的帐号基本信息请求
 */
public class ApiGetAuthorizerInfoRequest extends WechatComponentRequest implements Serializable {
    private static final long serialVersionUID = -1743540408534982991L;

    private String component_appid;
    private String authorizer_appid;

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
}
