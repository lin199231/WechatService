package win.demonlegion.wechatservice.module.menu;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义菜单按钮
 */
public class WechatMenuButton implements Serializable {
    private static final long serialVersionUID = 6853421099168013828L;

    public static final String BUTTON_TYPE_VIEW = "view";
    public static final String BUTTON_TYPE_CLICK = "click";
    public static final String BUTTON_TYPE_MINIPROGRAM = "miniprogram";

    private String name;
    private String key;
    private String type;
    private String url;
    private String media_id;
    private String appid;
    private String pagepath;
    private List<WechatMenuButton> sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<WechatMenuButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WechatMenuButton> sub_button) {
        this.sub_button = sub_button;
    }
}
