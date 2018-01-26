package win.demonlegion.wechatservice.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户列表
 */
public class UserList implements Serializable {
    private static final long serialVersionUID = -4796338295057634985L;

    private int total;
    private int count;
    private List<String> openids;

    public UserList() {
        openids = new ArrayList<>();
    }

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

    public List<String> getOpenids() {
        return openids;
    }

    public void setOpenids(List<String> openids) {
        this.openids = openids;
    }
}
