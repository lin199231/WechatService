package win.demonlegion.wechatservice.module.action;

import java.io.Serializable;

/**
 * 二维码生成信息
 */
public class QRCodeActionInfo implements Serializable {
    private static final long serialVersionUID = 3738742773698687785L;

    private int scene_id;
    private String scene_str;

    public int getScene_id() {
        return scene_id;
    }

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public String getScene_str() {
        return scene_str;
    }

    public void setScene_str(String scene_str) {
        this.scene_str = scene_str;
    }
}
