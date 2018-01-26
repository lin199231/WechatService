package win.demonlegion.wechatservice.service;

import win.demonlegion.wechatservice.response.*;
import win.demonlegion.wechatservice.response.component.*;

public interface WechatComponentService {
    String encryptWechatMessage(String plainMessage, String toUserName, String timestamp, String nonce);
    String decryptWechatMessage(String encryptMessage, String msgSignature, String timestamp, String nonce);
    String getComponentAccessToken();
    String getComponentVerifyTicket();
    void setComponentVerifyTicket(String componentVerifyTicket);
    String getAuthorizerAccessToken(String authorizerAppid);
    void setAuthorizerAccessToken(String authorizerAppid, String authorizerAccessToken, int expiresIn);
    String getPreAuthCode();
    GetPreAuthCodeResponse preAuthCode();
    ComponentAccessTokenResponse componentAccessToken();
    String componentLoginPage(String brandWechatAuthorizerId);
    ApiQueryAuthResponse apiQueryAuth(String authorizationCode);
    ApiAuthorizerTokenResponse apiAuthorizerToken(String authorizerAppid, String authorizerRefreshToken);
    ApiGetAuthorizerInfoResponse apiGetAuthorizerInfo(String authorizerAppid);
    ApiGetAuthorizerOptionResponse apiGetAuthorizerOption(String authorizerAppid, String optionName);
    WechatResponse apiSetAuthorizerOption(String authorizerAppid, String optionName, int optionValue);
    WechatResponse clearQuota();
}
