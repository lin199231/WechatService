package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.module.customservice.CustomServiceAccount;
import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 获取客服列表返回
 */
public class CustomServiceAccountListResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = 4930426363465163403L;

    private List<CustomServiceAccount> kf_list;

    public List<CustomServiceAccount> getKf_list() {
        return kf_list;
    }

    public void setKf_list(List<CustomServiceAccount> kf_list) {
        this.kf_list = kf_list;
    }
}
