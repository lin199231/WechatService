package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;

/**
 * 清楚微信api调用次数
 */
public class ClearQuotaRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = 4394913981310120527L;

    private String appid;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
