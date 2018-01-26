package win.demonlegion.wechatservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@ConfigurationProperties("wechat")
@Component
public class WechatConfig implements Serializable {
    private static final long serialVersionUID = -7645973041463455636L;

    public static final String CACHE_BASE_ACCESSTOKEN = "BASE_ACCESSTOKEN";

    private String appId;
    private String appSecret;
    private String clientCredentialUrl;
    private String getCallbackIpUrl;
    private String qrconnectUrl;
    private String accessTokenUrl;
    private String refreshAccessTokenUrl;
    private String verifyAccessTokenUrl;
    private String userInfoUrl;
    private String getTicketUrl;
    private String menuCreateUrl;
    private String menuGetUrl;
    private String menuDeleteUrl;
    private String customServiceAccountListUrl;
    private String addCustomServiceAccountUrl;
    private String updateCustomServiceAccountUrl;
    private String deleteCustomServiceAccountUrl;
    private String setCustomServiceAccountHeadimgUrl;
    private String sendCustomServiceMessageUrl;
    private String userListUrl;
    private String userInfoUnionIDUrl;
    private String batchUserInfoUrl;
    private String selectBlacklistUrl;
    private String batchBlacklistUrl;
    private String batchUnblacklistUrl;
    private String createQRCodeUrl;
    private String showQRCodeUrl;
    private String long2shortUrl;
    private String clearQuotaUrl;
    private String jsapiType;
    private String responseType;
    private String authGrantType;
    private String refreshGrantType;
    private String credGrantType;
    private String baseScope;
    private String loginScope;
    private String userInfoScope;
    private String suffix;
    private String language;
    private String redirectUri;

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

    public String getClientCredentialUrl() {
        return clientCredentialUrl;
    }

    public void setClientCredentialUrl(String clientCredentialUrl) {
        this.clientCredentialUrl = clientCredentialUrl;
    }

    public String getGetCallbackIpUrl() {
        return getCallbackIpUrl;
    }

    public void setGetCallbackIpUrl(String getCallbackIpUrl) {
        this.getCallbackIpUrl = getCallbackIpUrl;
    }

    public String getQrconnectUrl() {
        return qrconnectUrl;
    }

    public void setQrconnectUrl(String qrconnectUrl) {
        this.qrconnectUrl = qrconnectUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getRefreshAccessTokenUrl() {
        return refreshAccessTokenUrl;
    }

    public void setRefreshAccessTokenUrl(String refreshAccessTokenUrl) {
        this.refreshAccessTokenUrl = refreshAccessTokenUrl;
    }

    public String getVerifyAccessTokenUrl() {
        return verifyAccessTokenUrl;
    }

    public void setVerifyAccessTokenUrl(String verifyAccessTokenUrl) {
        this.verifyAccessTokenUrl = verifyAccessTokenUrl;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    public String getGetTicketUrl() {
        return getTicketUrl;
    }

    public void setGetTicketUrl(String getTicketUrl) {
        this.getTicketUrl = getTicketUrl;
    }

    public String getMenuCreateUrl() {
        return menuCreateUrl;
    }

    public void setMenuCreateUrl(String menuCreateUrl) {
        this.menuCreateUrl = menuCreateUrl;
    }

    public String getMenuGetUrl() {
        return menuGetUrl;
    }

    public void setMenuGetUrl(String menuGetUrl) {
        this.menuGetUrl = menuGetUrl;
    }

    public String getMenuDeleteUrl() {
        return menuDeleteUrl;
    }

    public void setMenuDeleteUrl(String menuDeleteUrl) {
        this.menuDeleteUrl = menuDeleteUrl;
    }

    public String getCustomServiceAccountListUrl() {
        return customServiceAccountListUrl;
    }

    public void setCustomServiceAccountListUrl(String customServiceAccountListUrl) {
        this.customServiceAccountListUrl = customServiceAccountListUrl;
    }

    public String getAddCustomServiceAccountUrl() {
        return addCustomServiceAccountUrl;
    }

    public void setAddCustomServiceAccountUrl(String addCustomServiceAccountUrl) {
        this.addCustomServiceAccountUrl = addCustomServiceAccountUrl;
    }

    public String getUpdateCustomServiceAccountUrl() {
        return updateCustomServiceAccountUrl;
    }

    public void setUpdateCustomServiceAccountUrl(String updateCustomServiceAccountUrl) {
        this.updateCustomServiceAccountUrl = updateCustomServiceAccountUrl;
    }

    public String getDeleteCustomServiceAccountUrl() {
        return deleteCustomServiceAccountUrl;
    }

    public void setDeleteCustomServiceAccountUrl(String deleteCustomServiceAccountUrl) {
        this.deleteCustomServiceAccountUrl = deleteCustomServiceAccountUrl;
    }

    public String getSetCustomServiceAccountHeadimgUrl() {
        return setCustomServiceAccountHeadimgUrl;
    }

    public void setSetCustomServiceAccountHeadimgUrl(String setCustomServiceAccountHeadimgUrl) {
        this.setCustomServiceAccountHeadimgUrl = setCustomServiceAccountHeadimgUrl;
    }

    public String getSendCustomServiceMessageUrl() {
        return sendCustomServiceMessageUrl;
    }

    public void setSendCustomServiceMessageUrl(String sendCustomServiceMessageUrl) {
        this.sendCustomServiceMessageUrl = sendCustomServiceMessageUrl;
    }

    public String getUserListUrl() {
        return userListUrl;
    }

    public void setUserListUrl(String userListUrl) {
        this.userListUrl = userListUrl;
    }

    public String getUserInfoUnionIDUrl() {
        return userInfoUnionIDUrl;
    }

    public void setUserInfoUnionIDUrl(String userInfoUnionIDUrl) {
        this.userInfoUnionIDUrl = userInfoUnionIDUrl;
    }

    public String getBatchUserInfoUrl() {
        return batchUserInfoUrl;
    }

    public void setBatchUserInfoUrl(String batchUserInfoUrl) {
        this.batchUserInfoUrl = batchUserInfoUrl;
    }

    public String getSelectBlacklistUrl() {
        return selectBlacklistUrl;
    }

    public void setSelectBlacklistUrl(String selectBlacklistUrl) {
        this.selectBlacklistUrl = selectBlacklistUrl;
    }

    public String getBatchBlacklistUrl() {
        return batchBlacklistUrl;
    }

    public void setBatchBlacklistUrl(String batchBlacklistUrl) {
        this.batchBlacklistUrl = batchBlacklistUrl;
    }

    public String getBatchUnblacklistUrl() {
        return batchUnblacklistUrl;
    }

    public void setBatchUnblacklistUrl(String batchUnblacklistUrl) {
        this.batchUnblacklistUrl = batchUnblacklistUrl;
    }

    public String getCreateQRCodeUrl() {
        return createQRCodeUrl;
    }

    public void setCreateQRCodeUrl(String createQRCodeUrl) {
        this.createQRCodeUrl = createQRCodeUrl;
    }

    public String getShowQRCodeUrl() {
        return showQRCodeUrl;
    }

    public void setShowQRCodeUrl(String showQRCodeUrl) {
        this.showQRCodeUrl = showQRCodeUrl;
    }

    public String getLong2shortUrl() {
        return long2shortUrl;
    }

    public void setLong2shortUrl(String long2shortUrl) {
        this.long2shortUrl = long2shortUrl;
    }

    public String getClearQuotaUrl() {
        return clearQuotaUrl;
    }

    public void setClearQuotaUrl(String clearQuotaUrl) {
        this.clearQuotaUrl = clearQuotaUrl;
    }

    public String getJsapiType() {
        return jsapiType;
    }

    public void setJsapiType(String jsapiType) {
        this.jsapiType = jsapiType;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getAuthGrantType() {
        return authGrantType;
    }

    public void setAuthGrantType(String authGrantType) {
        this.authGrantType = authGrantType;
    }

    public String getRefreshGrantType() {
        return refreshGrantType;
    }

    public void setRefreshGrantType(String refreshGrantType) {
        this.refreshGrantType = refreshGrantType;
    }

    public String getCredGrantType() {
        return credGrantType;
    }

    public void setCredGrantType(String credGrantType) {
        this.credGrantType = credGrantType;
    }

    public String getBaseScope() {
        return baseScope;
    }

    public void setBaseScope(String baseScope) {
        this.baseScope = baseScope;
    }

    public String getLoginScope() {
        return loginScope;
    }

    public void setLoginScope(String loginScope) {
        this.loginScope = loginScope;
    }

    public String getUserInfoScope() {
        return userInfoScope;
    }

    public void setUserInfoScope(String userInfoScope) {
        this.userInfoScope = userInfoScope;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
