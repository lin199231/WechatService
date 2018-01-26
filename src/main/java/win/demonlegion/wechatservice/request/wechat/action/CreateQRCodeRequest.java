package win.demonlegion.wechatservice.request.wechat.action;

import win.demonlegion.wechatservice.module.action.QRCodeActionInfo;
import win.demonlegion.wechatservice.request.wechat.WechatRequest;

import java.io.Serializable;

/**
 * 生成二维码请求
 */
public class CreateQRCodeRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = -8663300780119566677L;

    public static final String ACTION_NAME_QR_SCENE = "QR_SCENE";
    public static final String ACTION_NAME_QR_STR_SCENE = "QR_STR_SCENE";
    public static final String ACTION_NAME_QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
    public static final String ACTION_NAME_QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
    public static final int MONTH_EXPIRE_SECONDS = 2592000;

    private int expire_seconds;
    private String action_name;
    private QRCodeActionInfo action_info;

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public QRCodeActionInfo getAction_info() {
        return action_info;
    }

    public void setAction_info(QRCodeActionInfo action_info) {
        this.action_info = action_info;
    }
}
