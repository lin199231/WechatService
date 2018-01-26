package win.demonlegion.wechatservice.request.component;

import java.io.Serializable;

/**
 * 设置授权方的选项设置请求
 */
public class ComponentClearQuotaRequest extends WechatComponentRequest implements Serializable {
    private static final long serialVersionUID = 1368037234613570996L;

    private String component_appid;

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }
}
