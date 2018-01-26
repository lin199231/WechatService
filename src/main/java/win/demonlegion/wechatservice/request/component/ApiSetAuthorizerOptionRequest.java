package win.demonlegion.wechatservice.request.component;

import java.io.Serializable;

/**
 * 设置授权方的选项设置请求
 */
public class ApiSetAuthorizerOptionRequest extends WechatComponentRequest implements Serializable {
    private static final long serialVersionUID = -6733490931936839557L;

    private String component_appid;
    private String authorizer_appid;
    private String option_name;
    private int option_value;

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

    public int getOption_value() {
        return option_value;
    }

    public void setOption_value(int option_value) {
        this.option_value = option_value;
    }
}
