package win.demonlegion.wechatservice.module;

import java.io.Serializable;

/**
 * 授权方公众号类型
 */
public class ServiceTypeInfo implements Serializable {
    private static final long serialVersionUID = 8817136376691943590L;

    public static final String[] SERVICE_TYPE_DETAIL = {"订阅号", "由历史老帐号升级后的订阅号", "服务号"};

    // 0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
    private int id;
    private String detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if(id < SERVICE_TYPE_DETAIL.length)this.detail = SERVICE_TYPE_DETAIL[id];
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
