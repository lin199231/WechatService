package win.demonlegion.wechatservice.response;

import java.io.Serializable;

/**
 * 微信服务通用返回
 */
public class WechatResponse implements Serializable {
    private static final long serialVersionUID = -7212852585582074586L;
    public static final int  CODE_OK = 0;

    protected int errcode;
    protected String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
