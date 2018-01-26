package win.demonlegion.wechatservice.response.component;

import win.demonlegion.wechatservice.module.WechatAuthorizationInfo;
import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 微信第三方平台换取接口调用凭据返回
 */
public class ApiQueryAuthResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -7723106416476902716L;

    private WechatAuthorizationInfo authorization_info;

    public WechatAuthorizationInfo getAuthorization_info() {
        return authorization_info;
    }

    public void setAuthorization_info(WechatAuthorizationInfo authorization_info) {
        this.authorization_info = authorization_info;
    }
}
