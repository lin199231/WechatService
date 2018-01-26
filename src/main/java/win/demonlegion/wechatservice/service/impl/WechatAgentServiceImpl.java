package win.demonlegion.wechatservice.service.impl;

import com.alibaba.fastjson.JSON;
import win.demonlegion.wechatservice.service.CacheService;
import win.demonlegion.wechatservice.util.RandomUtil;
import win.demonlegion.wechatservice.config.WechatAgentConfig;
import win.demonlegion.wechatservice.config.WechatComponentConfig;
import win.demonlegion.wechatservice.config.WechatConfig;
import win.demonlegion.wechatservice.request.agent.AccessTokenRequest;
import win.demonlegion.wechatservice.request.agent.GetAuthorizationUrlRequest;
import win.demonlegion.wechatservice.request.agent.RefreshAccessTokenRequest;
import win.demonlegion.wechatservice.response.WechatResponse;
import win.demonlegion.wechatservice.response.agent.AccessTokenResponse;
import win.demonlegion.wechatservice.response.agent.RefreshAccessTokenResponse;
import win.demonlegion.wechatservice.service.WechatAgentService;
import win.demonlegion.wechatservice.service.WechatComponentService;
import win.demonlegion.wechatservice.service.WechatService;
import win.demonlegion.wechatservice.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("wechatAgentService")
public class WechatAgentServiceImpl implements WechatAgentService {
    private static final Logger logger = LoggerFactory.getLogger(WechatAgentServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WechatConfig wechatConfig;
    @Autowired
    private WechatComponentConfig wechatComponentConfig;
    @Autowired
    private WechatAgentConfig wechatAgentConfig;
    @Autowired
    private WechatComponentService wechatComponentService;
    @Autowired
    private CacheService cacheService;

    @Override
    public String getAuthorizationUrl(String appid) {
        GetAuthorizationUrlRequest getAuthorizationUrlRequest = new GetAuthorizationUrlRequest();
        getAuthorizationUrlRequest.setAppid(appid);
        getAuthorizationUrlRequest.setRedirect_uri(wechatAgentConfig.getAuthorizationRedirectUri());
        getAuthorizationUrlRequest.setResponse_type(wechatConfig.getResponseType());
        getAuthorizationUrlRequest.setScope(wechatConfig.getUserInfoScope());
        // 重定向后会带上state参数，开发者可以填写任意参数值，最多128字节
        String state = RandomUtil.getRandomString(32);
        // 添加至缓存用户授权时检验, key规则为appid_state
        cacheService.saveTempToken(appid + "_" + state, state);
        getAuthorizationUrlRequest.setState(state);
        getAuthorizationUrlRequest.setComponent_appid(wechatComponentConfig.getAppId());
        // 微信第三方平台授权页发起链接强制参数顺序
        List<String> keyList = new ArrayList<>();
        keyList.add("appid");
        keyList.add("redirect_uri");
        keyList.add("response_type");
        keyList.add("scope");
        keyList.add("state");
        keyList.add("component_appid");
        return RequestUtil.getUrl(wechatAgentConfig.getAuthorizationUrl(),
                getAuthorizationUrlRequest, keyList, wechatConfig.getSuffix(), GetAuthorizationUrlRequest.class);
    }

    @Override
    public AccessTokenResponse getAccessToken(String appid, String code) {
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setAppid(appid);
        accessTokenRequest.setCode(code);
        accessTokenRequest.setGrant_type(wechatConfig.getAuthGrantType());
        accessTokenRequest.setComponent_appid(wechatComponentConfig.getAppId());
        accessTokenRequest.setComponent_access_token(wechatComponentService.getComponentAccessToken());
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatAgentConfig.getGetAccessTokenUrl(), accessTokenRequest), String.class);
        logger.debug(response);
        AccessTokenResponse accessTokenResponse = JSON.parseObject(response, AccessTokenResponse.class);
        if(accessTokenResponse.getErrcode() == WechatResponse.CODE_OK) {
            return accessTokenResponse;
        } else {
            logger.error("WechatAgentService-getAccessToken: " + accessTokenResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public RefreshAccessTokenResponse refreshAccessToken(String appid, String refreshToken) {
        RefreshAccessTokenRequest refreshAccessTokenRequest = new RefreshAccessTokenRequest();
        refreshAccessTokenRequest.setAppid(appid);
        refreshAccessTokenRequest.setRefresh_token(refreshToken);
        refreshAccessTokenRequest.setGrant_type(wechatConfig.getRefreshGrantType());
        refreshAccessTokenRequest.setComponent_appid(wechatComponentConfig.getAppId());
        refreshAccessTokenRequest.setComponent_access_token(wechatComponentService.getComponentAccessToken());
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatAgentConfig.getRefreshAccessTokenUrl(), refreshAccessTokenRequest), String.class);
        logger.debug(response);
        RefreshAccessTokenResponse refreshAccessTokenResponse = JSON.parseObject(response, RefreshAccessTokenResponse.class);
        if(refreshAccessTokenResponse.getErrcode() == WechatResponse.CODE_OK) {
            return refreshAccessTokenResponse;
        } else {
            logger.error("WechatAgentService-refreshAccessToken: " + refreshAccessTokenResponse.getErrmsg());
            return null;
        }
    }
}
