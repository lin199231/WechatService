package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 微信第三方平台推送消息
 */
public class ComponentPushMessage implements Serializable {
    private static final long serialVersionUID = 1235830128152643577L;

    public static final String INFO_TYPE_TICKET = "component_verify_ticket";
    public static final String INFO_TYPE_AUTHORIZED = "authorized";
    public static final String INFO_TYPE_UPDATEAUTHORIZED = "updateauthorized";
    public static final String INFO_TYPE_UNAUTHORIZED = "unauthorized";

    private String AppId;
    private long CreateTime;
    private String InfoType;
    private String ComponentVerifyTicket;
    private String AuthorizerAppid;
    private String AuthorizationCode;
    private long AuthorizationCodeExpiredTime;
    private String PreAuthCode;

    @JSONField(name = "AppId")
    public String getAppId() {
        return AppId;
    }

    @JSONField(name = "CreateTime")
    public long getCreateTime() {
        return CreateTime;
    }

    @JSONField(name = "InfoType")
    public String getInfoType() {
        return InfoType;
    }

    @JSONField(name = "ComponentVerifyTicket")
    public String getComponentVerifyTicket() {
        return ComponentVerifyTicket;
    }

    @JSONField(name = "AuthorizerAppid")
    public String getAuthorizerAppid() {
        return AuthorizerAppid;
    }

    @JSONField(name = "AuthorizationCode")
    public String getAuthorizationCode() {
        return AuthorizationCode;
    }

    @JSONField(name = "AuthorizationCodeExpiredTime")
    public long getAuthorizationCodeExpiredTime() {
        return AuthorizationCodeExpiredTime;
    }

    @JSONField(name = "PreAuthCode")
    public String getPreAuthCode() {
        return PreAuthCode;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public void setInfoType(String infoType) {
        InfoType = infoType;
    }

    public void setComponentVerifyTicket(String componentVerifyTicket) {
        ComponentVerifyTicket = componentVerifyTicket;
    }

    public void setAuthorizerAppid(String authorizerAppid) {
        AuthorizerAppid = authorizerAppid;
    }

    public void setAuthorizationCode(String authorizationCode) {
        AuthorizationCode = authorizationCode;
    }

    public void setAuthorizationCodeExpiredTime(long authorizationCodeExpiredTime) {
        AuthorizationCodeExpiredTime = authorizationCodeExpiredTime;
    }

    public void setPreAuthCode(String preAuthCode) {
        PreAuthCode = preAuthCode;
    }
}