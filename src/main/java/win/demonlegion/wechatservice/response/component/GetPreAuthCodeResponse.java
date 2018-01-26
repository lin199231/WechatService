package win.demonlegion.wechatservice.response.component;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 获取微信第三方平台预授权码返回
 */
public class GetPreAuthCodeResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -8938259934744622684L;

    private String pre_auth_code;
    private int expires_in;

    public String getPre_auth_code() {
        return pre_auth_code;
    }

    public void setPre_auth_code(String pre_auth_code) {
        this.pre_auth_code = pre_auth_code;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
