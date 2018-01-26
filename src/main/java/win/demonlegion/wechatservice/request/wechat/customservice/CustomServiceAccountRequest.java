package win.demonlegion.wechatservice.request.wechat.customservice;

import win.demonlegion.wechatservice.request.wechat.WechatRequest;

import java.io.Serializable;

/**
 * 微信客服管理请求
 */
public class CustomServiceAccountRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = -3415353350576539382L;

    private String kf_account;
    private String nickname;
    private String password;

    public String getKf_account() {
        return kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
