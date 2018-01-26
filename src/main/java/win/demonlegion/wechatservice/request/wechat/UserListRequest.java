package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 获取用户列表请求
 */
public class UserListRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = 7497144544604162280L;

    private String next_openid;

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }
}
