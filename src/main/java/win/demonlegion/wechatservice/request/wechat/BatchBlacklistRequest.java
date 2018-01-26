package win.demonlegion.wechatservice.request.wechat;

import java.io.Serializable;
import java.util.List;

/**
 * 批量用户详情请求
 */
public class BatchBlacklistRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = 4319460133528541811L;

    private List<String> openid_list;

    public List<String> getOpenid_list() {
        return openid_list;
    }

    public void setOpenid_list(List<String> openid_list) {
        this.openid_list = openid_list;
    }
}
