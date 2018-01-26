package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.module.menu.WechatMenu;
import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 获取自定义菜单返回
 */
public class MenuGetResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = 4876686562623584799L;

    private WechatMenu menu;

    public WechatMenu getMenu() {
        return menu;
    }

    public void setMenu(WechatMenu menu) {
        this.menu = menu;
    }
}
