package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 微信获取accessToken请求
 */
public class UserInfoRequest implements Serializable {
    private static final long serialVersionUID = 3911753888847915408L;

    private String access_token;
    private String openid;
    private String lang;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
