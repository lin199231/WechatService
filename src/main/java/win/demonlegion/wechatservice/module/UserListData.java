package win.demonlegion.wechatservice.module;

import java.io.Serializable;
import java.util.List;

/**
 * 用户列表数据
 */
public class UserListData implements Serializable {
    private static final long serialVersionUID = 1585951719034460301L;

    private List<String> openid;

    public List<String> getOpenid() {
        return openid;
    }

    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }
}
