package win.demonlegion.wechatservice.module.menu;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义菜单
 */
public class WechatMenu implements Serializable {
    private static final long serialVersionUID = -5208975175100205026L;

    private List<WechatMenuButton> button;

    public List<WechatMenuButton> getButton() {
        return button;
    }

    public void setButton(List<WechatMenuButton> button) {
        this.button = button;
    }
}
