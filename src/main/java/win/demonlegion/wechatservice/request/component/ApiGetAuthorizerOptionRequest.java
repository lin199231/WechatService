package win.demonlegion.wechatservice.request.component;

import java.io.Serializable;

/**
 * 获取授权方的选项设置信息请求
 */
public class ApiGetAuthorizerOptionRequest extends WechatComponentRequest implements Serializable {
    private static final long serialVersionUID = -2299099538428687141L;

    private String component_appid;
    private String authorizer_appid;
    private String option_name;

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

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }
}
