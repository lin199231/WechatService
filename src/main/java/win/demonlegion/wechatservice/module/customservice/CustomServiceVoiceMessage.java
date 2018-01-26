package win.demonlegion.wechatservice.module.customservice;

import java.io.Serializable;

/**
 * 客服语音消息
 */
public class CustomServiceVoiceMessage implements Serializable {
    private static final long serialVersionUID = 3272980455410922942L;

    private String media_id;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
