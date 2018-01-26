package win.demonlegion.wechatservice.module;

import java.io.Serializable;

/**
 * 微信公众号授权详细范围
 */
public class FunctionScopeCategory implements Serializable {
    private static final long serialVersionUID = 2106861660694512450L;

    public static final String[] CATEGORY_DETAIL = {"消息管理权限", "用户管理权限", "帐号服务权限", "网页服务权限", "微信小店权限",
            "微信多客服权限", "群发与通知权限", "微信卡券权限", "微信扫一扫权限", "微信连WIFI权限", "素材管理权限", "微信摇周边权限", "微信门店权限",
            "微信支付权限", "自定义菜单权限", "获取认证状态及信息", "帐号管理权限（小程序）", "开发管理与数据分析权限（小程序）", "客服消息管理权限（小程序）",
            "微信登录权限（小程序）", "数据分析权限（小程序）", "城市服务接口权限", "广告管理权限", "开放平台帐号管理权限", "开放平台帐号管理权限（小程序）",
            "微信电子发票权限"};

    private int id;
    private String detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if(id <= CATEGORY_DETAIL.length) this.detail = CATEGORY_DETAIL[id - 1];
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
