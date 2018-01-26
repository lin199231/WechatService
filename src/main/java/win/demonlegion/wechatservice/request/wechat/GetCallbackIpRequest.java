package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 获取微信回调服务器IP请求
 */
public class GetCallbackIpRequest implements Serializable {
    private static final long serialVersionUID = -5801576012704181130L;

    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
