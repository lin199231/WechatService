package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 查看黑名单接口
 */
public class SelectBlacklistRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = -461921803896455435L;

    private String begin_openid;

    public String getBegin_openid() {
        return begin_openid;
    }

    public void setBegin_openid(String begin_openid) {
        this.begin_openid = begin_openid;
    }
}
