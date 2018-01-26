package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.module.UserListData;
import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 获取用户列表返回
 */
public class UserListResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = 3619347231444439152L;

    private int total;
    private int count;
    private UserListData data;
    private String next_openid;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UserListData getData() {
        return data;
    }

    public void setData(UserListData data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }
}
