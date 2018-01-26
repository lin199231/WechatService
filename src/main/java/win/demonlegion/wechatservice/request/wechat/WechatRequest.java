package win.demonlegion.wechatservice.request.wechat;

/**
 * 微信公众号请求通用对象
 */
public class WechatRequest {
    protected String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
