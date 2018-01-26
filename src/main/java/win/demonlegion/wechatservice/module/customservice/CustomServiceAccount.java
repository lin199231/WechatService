package win.demonlegion.wechatservice.module.customservice;

import java.io.Serializable;

/**
 * 客户信息
 */
public class CustomServiceAccount implements Serializable {
    private static final long serialVersionUID = 1326491144235018340L;

    private String kf_account;
    private String kf_nick;
    private int kf_id;
    private String kf_headimgurl;

    public String getKf_account() {
        return kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getKf_nick() {
        return kf_nick;
    }

    public void setKf_nick(String kf_nick) {
        this.kf_nick = kf_nick;
    }

    public int getKf_id() {
        return kf_id;
    }

    public void setKf_id(int kf_id) {
        this.kf_id = kf_id;
    }

    public String getKf_headimgurl() {
        return kf_headimgurl;
    }

    public void setKf_headimgurl(String kf_headimgurl) {
        this.kf_headimgurl = kf_headimgurl;
    }
}
