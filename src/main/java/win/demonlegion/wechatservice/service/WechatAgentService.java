package win.demonlegion.wechatservice.service;

import win.demonlegion.wechatservice.response.agent.AccessTokenResponse;
import win.demonlegion.wechatservice.response.agent.RefreshAccessTokenResponse;

public interface WechatAgentService {
    String getAuthorizationUrl(String appid);
    AccessTokenResponse getAccessToken(String appid, String code);
    RefreshAccessTokenResponse refreshAccessToken(String appid, String refreshToken);
}
