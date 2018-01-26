package win.demonlegion.wechatservice.response.component;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 获取授权方的选项设置信息返回
 */
public class ApiGetAuthorizerOptionResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = 2185642755953773522L;

    private String authorizer_appid;
    private String option_name;
    private int option_value;

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
