package win.demonlegion.wechatservice.response.component;

import win.demonlegion.wechatservice.module.WechatAuthorizationInfo;
import win.demonlegion.wechatservice.module.WechatAuthorizerInfo;
import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 获取授权方的帐号基本信息返回
 */
public class ApiGetAuthorizerInfoResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -8590531117418382445L;

    private WechatAuthorizerInfo authorizer_info;
    private WechatAuthorizationInfo authorization_info;

    public WechatAuthorizerInfo getAuthorizer_info() {
        return authorizer_info;
    }

    public void setAuthorizer_info(WechatAuthorizerInfo authorizer_info) {
        this.authorizer_info = authorizer_info;
    }

    public WechatAuthorizationInfo getAuthorization_info() {
        return authorization_info;
    }

    public void setAuthorization_info(WechatAuthorizationInfo authorization_info) {
        this.authorization_info = authorization_info;
    }
}
