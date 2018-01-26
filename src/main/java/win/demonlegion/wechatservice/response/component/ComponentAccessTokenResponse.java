package win.demonlegion.wechatservice.response.component;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 获取微信第三方平台AccessToken返回
 */
public class ComponentAccessTokenResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -183825694831689964L;

    private String component_access_token;
    private int expires_in;

    public String getComponent_access_token() {
        return component_access_token;
    }

    public void setComponent_access_token(String component_access_token) {
        this.component_access_token = component_access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
