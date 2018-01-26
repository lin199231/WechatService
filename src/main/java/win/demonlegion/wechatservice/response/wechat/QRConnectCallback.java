package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;

/**
 * 微信二维码用户扫描回调
 */
public class QRConnectCallback extends WechatResponse implements Serializable {
    private static final long serialVersionUID = 7970123486635616096L;

    private String code;
    private String state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
