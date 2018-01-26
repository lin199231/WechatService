package win.demonlegion.wechatservice.module.customservice;

import java.io.Serializable;

/**
 * 客服图片消息
 */
public class CustomServiceImageMessage implements Serializable {
    private static final long serialVersionUID = -4431405503241223284L;

    private String media_id;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
