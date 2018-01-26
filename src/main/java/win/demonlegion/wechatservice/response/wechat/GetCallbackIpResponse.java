package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 获取微信回调服务器IP返回
 */
public class GetCallbackIpResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -3099375576166777024L;

    private List<String> ip_list;

    public List<String> getIp_list() {
        return ip_list;
    }

    public void setIp_list(List<String> ip_list) {
        this.ip_list = ip_list;
    }
}
