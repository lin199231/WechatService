package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.module.UserInfo;
import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 批量用户详情返回
 */
public class BatchUserInfoResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -1526946607697618257L;

    private List<UserInfo> user_info_list;

    public List<UserInfo> getUser_info_list() {
        return user_info_list;
    }

    public void setUser_info_list(List<UserInfo> user_info_list) {
        this.user_info_list = user_info_list;
    }
}
