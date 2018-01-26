package win.demonlegion.wechatservice.request.component;

import java.io.Serializable;

/**
 * 获取微信第三方平台授权页请求
 */
public class ComponentLoginPageRequest implements Serializable {
    private static final long serialVersionUID = -7215570711619342118L;

    // 要授权的帐号类型，1则商户扫码后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。
    // 如果为未制定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
    public static final int AUTH_TYPE_DEFAULT = 3;

    private String component_appid;
    private String pre_auth_code;
    private String redirect_uri;
    private int auth_type;

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }

    public String getPre_auth_code() {
        return pre_auth_code;
    }

    public void setPre_auth_code(String pre_auth_code) {
        this.pre_auth_code = pre_auth_code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public int getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(int auth_type) {
        this.auth_type = auth_type;
    }
}
