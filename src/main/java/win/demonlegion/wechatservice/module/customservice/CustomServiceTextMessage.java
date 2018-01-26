package win.demonlegion.wechatservice.module.customservice;

import java.io.Serializable;

/**
 * 客服文本消息
 */
public class CustomServiceTextMessage implements Serializable {
    private static final long serialVersionUID = -5098382690652067304L;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
