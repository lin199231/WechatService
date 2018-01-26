package win.demonlegion.wechatservice.request.component;

import java.io.Serializable;

/**
 * 获取微信第三方平台预授权码请求
 */
public class GetPreAuthCodeRequest extends WechatComponentRequest implements Serializable {
    private static final long serialVersionUID = 7859551333708317950L;

    private String component_appid;

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }
}
