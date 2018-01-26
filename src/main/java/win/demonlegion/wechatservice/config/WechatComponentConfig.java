package win.demonlegion.wechatservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@ConfigurationProperties("wechat-component")
@Component
public class WechatComponentConfig implements Serializable {
    private static final long serialVersionUID = 8507560487919030933L;

    public static final String CACHE_COMPONENT_ACCESS_TOKEN = "COMPONENT_ACCESS_TOKEN";
    public static final String CACHE_COMPONENT_VERIFY_TICKET = "COMPONENT_VERIFY_TICKET";
    public static final String CACHE_PRE_AUTH_CODE = "PRE_AUTH_CODE";

    private String appId;
    private String appSecret;
    private String messageToken;
    private String encodingAESKey;
    private String redirectUri;
    private String componentAccessTokenUrl;
    private String getPreAuthCodeUrl;
    private String componentLoginPageUrl;
    private String apiQueryAuthUrl;
    private String apiAuthorizerTokenUrl;
    private String apiGetAuthorizerInfoUrl;
    private String apiGetAuthorizerOptionUrl;
    private String apiSetAuthorizerOptionUrl;
    private String clearQuotaUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMessageToken() {
        return messageToken;
    }

    public void setMessageToken(String messageToken) {
        this.messageToken = messageToken;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getComponentAccessTokenUrl() {
        return componentAccessTokenUrl;
    }

    public void setComponentAccessTokenUrl(String componentAccessTokenUrl) {
        this.componentAccessTokenUrl = componentAccessTokenUrl;
    }

    public String getGetPreAuthCodeUrl() {
        return getPreAuthCodeUrl;
    }

    public void setGetPreAuthCodeUrl(String getPreAuthCodeUrl) {
        this.getPreAuthCodeUrl = getPreAuthCodeUrl;
    }

    public String getComponentLoginPageUrl() {
        return componentLoginPageUrl;
    }

    public void setComponentLoginPageUrl(String componentLoginPageUrl) {
        this.componentLoginPageUrl = componentLoginPageUrl;
    }

    public String getApiQueryAuthUrl() {
        return apiQueryAuthUrl;
    }

    public void setApiQueryAuthUrl(String apiQueryAuthUrl) {
        this.apiQueryAuthUrl = apiQueryAuthUrl;
    }

    public String getApiAuthorizerTokenUrl() {
        return apiAuthorizerTokenUrl;
    }

    public void setApiAuthorizerTokenUrl(String apiAuthorizerTokenUrl) {
        this.apiAuthorizerTokenUrl = apiAuthorizerTokenUrl;
    }

    public String getApiGetAuthorizerInfoUrl() {
        return apiGetAuthorizerInfoUrl;
    }

    public void setApiGetAuthorizerInfoUrl(String apiGetAuthorizerInfoUrl) {
        this.apiGetAuthorizerInfoUrl = apiGetAuthorizerInfoUrl;
    }

    public String getApiGetAuthorizerOptionUrl() {
        return apiGetAuthorizerOptionUrl;
    }

    public void setApiGetAuthorizerOptionUrl(String apiGetAuthorizerOptionUrl) {
        this.apiGetAuthorizerOptionUrl = apiGetAuthorizerOptionUrl;
    }

    public String getApiSetAuthorizerOptionUrl() {
        return apiSetAuthorizerOptionUrl;
    }

    public void setApiSetAuthorizerOptionUrl(String apiSetAuthorizerOptionUrl) {
        this.apiSetAuthorizerOptionUrl = apiSetAuthorizerOptionUrl;
    }

    public String getClearQuotaUrl() {
        return clearQuotaUrl;
    }

    public void setClearQuotaUrl(String clearQuotaUrl) {
        this.clearQuotaUrl = clearQuotaUrl;
    }
}
