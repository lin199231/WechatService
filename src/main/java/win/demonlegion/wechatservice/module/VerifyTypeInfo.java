package win.demonlegion.wechatservice.module;

import java.io.Serializable;

/**
 * 授权方认证类型
 */
public class VerifyTypeInfo implements Serializable {
    private static final long serialVersionUID = 4244539230929711927L;

    public static final String[] VERIFY_TYPE_DETAIL = {"未认证", "微信认证", "新浪微博认证", "腾讯微博认证", "已资质认证通过但还未通过名称认证",
            "已资质认证通过、还未通过名称认证，但通过了新浪微博认证", "已资质认证通过、还未通过名称认证，但通过了腾讯微博认证"};

    private int id;
    private String detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if(id >= -1 && id < VERIFY_TYPE_DETAIL.length - 1 ) this.detail = VERIFY_TYPE_DETAIL[id + 1];
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
