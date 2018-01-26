package win.demonlegion.wechatservice.service.impl;

import com.alibaba.fastjson.JSON;
import win.demonlegion.wechatservice.service.CacheService;
import win.demonlegion.wechatservice.util.XMLUtil;
import win.demonlegion.wechatservice.config.WechatComponentConfig;
import win.demonlegion.wechatservice.crypt.WXBizMsgCrypt;
import win.demonlegion.wechatservice.exception.AesException;
import win.demonlegion.wechatservice.module.message.ReceiveMessage;
import win.demonlegion.wechatservice.request.component.*;
import win.demonlegion.wechatservice.response.*;
import win.demonlegion.wechatservice.response.component.*;
import win.demonlegion.wechatservice.service.WechatComponentService;
import win.demonlegion.wechatservice.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("wechatComponentService")
public class WechatComponentServiceImpl implements WechatComponentService {
    private static final Logger logger = LoggerFactory.getLogger(WechatComponentServiceImpl.class);

    @Autowired
    private WechatComponentConfig wechatComponentConfig;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WXBizMsgCrypt wxBizMsgCrypt;

    @Override
    public String encryptWechatMessage(String plainMessage, String toUserName, String timestamp, String nonce) {
        try {
            return wxBizMsgCrypt.encryptMsg(plainMessage, timestamp, nonce);
        } catch(AesException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decryptWechatMessage(String encryptMessage, String msgSignature, String timestamp, String nonce) {
        ReceiveMessage receiveMessage = XMLUtil.parseXML(encryptMessage, ReceiveMessage.class);
        if(receiveMessage != null) {
            try {
                return wxBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, receiveMessage.getEncrypt());
            } catch (AesException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getComponentAccessToken() {
        // 从缓存中获取ComponentAccessToken, 过期则再请求微信更新
        String componentAccessToken = cacheService.selectCache(WechatComponentConfig.CACHE_COMPONENT_ACCESS_TOKEN);
        if(StringUtils.isNotEmpty(componentAccessToken)) {
            return componentAccessToken;
        } else {
            ComponentAccessTokenResponse componentAccessTokenResponse = componentAccessToken();
            if(componentAccessTokenResponse.getErrcode() == WechatResponse.CODE_OK) {
                cacheService.saveToken(WechatComponentConfig.CACHE_COMPONENT_ACCESS_TOKEN,
                        componentAccessTokenResponse.getComponent_access_token(), componentAccessTokenResponse.getExpires_in());
                return componentAccessTokenResponse.getComponent_access_token();
            } else {
                // 微信返回错误
                logger.error("WechatComponentService-getComponentAccessToken: "
                        + componentAccessTokenResponse.getErrcode() + ", " + componentAccessTokenResponse.getErrmsg());
                return null;
            }
        }
    }

    @Override
    public String getComponentVerifyTicket() {
        // 从缓存中获取ComponentVerifyTicket, 因为微信会每10分钟调用, 所以不需要担心过期问题
        return cacheService.selectCache(WechatComponentConfig.CACHE_COMPONENT_VERIFY_TICKET);
    }

    @Override
    public void setComponentVerifyTicket(String componentVerifyTicket) {
        if(StringUtils.isNotEmpty(componentVerifyTicket)) {
            cacheService.saveToken(WechatComponentConfig.CACHE_COMPONENT_VERIFY_TICKET, componentVerifyTicket);
        }
    }

    @Override
    public String getAuthorizerAccessToken(String authorizerAppid) {
        String authorizerAccessToken = cacheService.selectCache(authorizerAppid);
        if(StringUtils.isEmpty(authorizerAccessToken)) {
            // 授权方AccessToken过期则进行刷新
            String refreshToken = "";
            ApiAuthorizerTokenResponse apiAuthorizerTokenResponse = apiAuthorizerToken(authorizerAppid, refreshToken);
            if(apiAuthorizerTokenResponse != null) {
                authorizerAccessToken = apiAuthorizerTokenResponse.getAuthorizer_access_token();
                // 更新授权方的AccessToken
                setAuthorizerAccessToken(authorizerAppid, authorizerAccessToken, apiAuthorizerTokenResponse.getExpires_in());
            }
        }
        return authorizerAccessToken;
    }

    @Override
    public void setAuthorizerAccessToken(String authorizerAppid, String authorizerAccessToken, int expiresIn) {
        cacheService.saveToken(authorizerAppid, authorizerAccessToken, expiresIn);
    }

    @Override
    public String getPreAuthCode() {
        String preAuthCode = cacheService.selectCache(WechatComponentConfig.CACHE_PRE_AUTH_CODE);
        if(StringUtils.isNotEmpty(preAuthCode)) return preAuthCode;
        else {
            GetPreAuthCodeResponse getPreAuthCodeResponse = preAuthCode();
            if(getPreAuthCodeResponse.getErrcode() == WechatResponse.CODE_OK) {
                // 缓存预授权码
                cacheService.saveToken(WechatComponentConfig.CACHE_PRE_AUTH_CODE,
                        getPreAuthCodeResponse.getPre_auth_code(), getPreAuthCodeResponse.getExpires_in());
                return getPreAuthCodeResponse.getPre_auth_code();
            } else {
                logger.error("WechatComponentService-getPreAuthCode: "
                        + getPreAuthCodeResponse.getErrcode() + ", " + getPreAuthCodeResponse.getErrmsg());
                return null;
            }
        }
    }

    @Override
    public GetPreAuthCodeResponse preAuthCode() {
        GetPreAuthCodeRequest getPreAuthCodeRequest = new GetPreAuthCodeRequest();
        getPreAuthCodeRequest.setComponent_access_token(getComponentAccessToken());
        getPreAuthCodeRequest.setComponent_appid(wechatComponentConfig.getAppId());
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatComponentConfig.getGetPreAuthCodeUrl(), getPreAuthCodeRequest, WechatComponentRequest.class),
                getPreAuthCodeRequest, String.class);
        logger.debug(response);
        GetPreAuthCodeResponse getPreAuthCodeResponse = JSON.parseObject(response, GetPreAuthCodeResponse.class);
        return getPreAuthCodeResponse;
    }

    @Override
    public String componentLoginPage(String brandWechatAuthorizerId) {
        ComponentLoginPageRequest componentLoginPageRequest = new ComponentLoginPageRequest();
        componentLoginPageRequest.setComponent_appid(wechatComponentConfig.getAppId());
        componentLoginPageRequest.setPre_auth_code(getPreAuthCode());
        // 需要在回调地址上增加PathVariable, 用于绑定授权方与品牌
        componentLoginPageRequest.setRedirect_uri(wechatComponentConfig.getRedirectUri()
                + "/" + brandWechatAuthorizerId);
        componentLoginPageRequest.setAuth_type(ComponentLoginPageRequest.AUTH_TYPE_DEFAULT);
        return RequestUtil.getUrl(wechatComponentConfig.getComponentLoginPageUrl(), componentLoginPageRequest);
    }

    @Override
    public ComponentAccessTokenResponse componentAccessToken() {
        ComponentAccessTokenRequest componentAccessTokenRequest = new ComponentAccessTokenRequest();
        componentAccessTokenRequest.setComponent_appid(wechatComponentConfig.getAppId());
        componentAccessTokenRequest.setComponent_appsecret(wechatComponentConfig.getAppSecret());
        componentAccessTokenRequest.setComponent_verify_ticket(getComponentVerifyTicket());
        String response = restTemplate.postForObject(
                wechatComponentConfig.getComponentAccessTokenUrl(), componentAccessTokenRequest, String.class);
        logger.debug(response);
        ComponentAccessTokenResponse componentAccessTokenResponse = JSON.parseObject(response, ComponentAccessTokenResponse.class);
        if(componentAccessTokenResponse.getErrcode() == WechatResponse.CODE_OK) {
            // 缓存第三方平台的ComponentAccessToken
            cacheService.saveToken(WechatComponentConfig.CACHE_COMPONENT_ACCESS_TOKEN,
                    componentAccessTokenResponse.getComponent_access_token(), componentAccessTokenResponse.getExpires_in());

            return componentAccessTokenResponse;
        } else {
            logger.error("WechatComponentService-componentAccessToken: "
                    + componentAccessTokenResponse.getErrcode() + ", " + componentAccessTokenResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public ApiQueryAuthResponse apiQueryAuth(String authorizationCode) {
        ApiQueryAuthRequest apiQueryAuthRequest = new ApiQueryAuthRequest();
        apiQueryAuthRequest.setComponent_appid(wechatComponentConfig.getAppId());
        apiQueryAuthRequest.setComponent_access_token(getComponentAccessToken());
        apiQueryAuthRequest.setAuthorization_code(authorizationCode);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatComponentConfig.getApiQueryAuthUrl(), apiQueryAuthRequest, WechatComponentRequest.class),
                apiQueryAuthRequest, String.class);
        logger.debug(response);
        ApiQueryAuthResponse apiQueryAuthResponse = JSON.parseObject(response, ApiQueryAuthResponse.class);
        if(apiQueryAuthResponse.getErrcode() == WechatResponse.CODE_OK) {
            return apiQueryAuthResponse;
        } else {
            logger.error("WechatComponentService-apiQueryAuth: " + apiQueryAuthResponse.getErrcode() + ", "
                    + apiQueryAuthResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public ApiAuthorizerTokenResponse apiAuthorizerToken(String authorizerAppid, String authorizerRefreshToken) {
        ApiAuthorizerTokenRequest apiAuthorizerTokenRequest = new ApiAuthorizerTokenRequest();
        apiAuthorizerTokenRequest.setComponent_appid(wechatComponentConfig.getAppId());
        apiAuthorizerTokenRequest.setComponent_access_token(getComponentAccessToken());
        apiAuthorizerTokenRequest.setAuthorizer_appid(authorizerAppid);
        apiAuthorizerTokenRequest.setAuthorizer_refresh_token(authorizerRefreshToken);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatComponentConfig.getApiAuthorizerTokenUrl(), apiAuthorizerTokenRequest, WechatComponentRequest.class),
                apiAuthorizerTokenRequest, String.class);
        logger.debug(response);
        ApiAuthorizerTokenResponse apiAuthorizerTokenResponse = JSON.parseObject(response, ApiAuthorizerTokenResponse.class);
        if(apiAuthorizerTokenResponse.getErrcode() == WechatResponse.CODE_OK) {
            return apiAuthorizerTokenResponse;
        } else {
            logger.error("WechatComponentService-apiAuthorizerToken: "
                    + apiAuthorizerTokenResponse.getErrcode() + ", " + apiAuthorizerTokenResponse.getErrmsg());
            return null;
        }

    }

    @Override
    public ApiGetAuthorizerInfoResponse apiGetAuthorizerInfo(String authorizerAppid) {
        // 获取授权方的帐号基本信息
        ApiGetAuthorizerInfoRequest apiGetAuthorizerInfoRequest = new ApiGetAuthorizerInfoRequest();
        apiGetAuthorizerInfoRequest.setComponent_appid(wechatComponentConfig.getAppId());
        apiGetAuthorizerInfoRequest.setAuthorizer_appid(authorizerAppid);
        apiGetAuthorizerInfoRequest.setComponent_access_token(getComponentAccessToken());
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatComponentConfig.getApiGetAuthorizerInfoUrl(), apiGetAuthorizerInfoRequest, WechatComponentRequest.class),
                apiGetAuthorizerInfoRequest, String.class);
        logger.debug(response);
        ApiGetAuthorizerInfoResponse apiGetAuthorizerInfoResponse = JSON.parseObject(response, ApiGetAuthorizerInfoResponse.class);
        if(apiGetAuthorizerInfoResponse.getErrcode() == WechatResponse.CODE_OK) {
            return apiGetAuthorizerInfoResponse;
        } else {
            logger.error("WechatComponentService-apiGetAuthorizerInfo: "
                    + apiGetAuthorizerInfoResponse.getErrcode() + ", " + apiGetAuthorizerInfoResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public ApiGetAuthorizerOptionResponse apiGetAuthorizerOption(String authorizerAppid, String optionName) {
        // 获取授权方的选项设置信息
        ApiGetAuthorizerOptionRequest apiGetAuthorizerOptionRequest = new ApiGetAuthorizerOptionRequest();
        apiGetAuthorizerOptionRequest.setComponent_appid(wechatComponentConfig.getAppId());
        apiGetAuthorizerOptionRequest.setComponent_access_token(getComponentAccessToken());
        apiGetAuthorizerOptionRequest.setAuthorizer_appid(authorizerAppid);
        apiGetAuthorizerOptionRequest.setOption_name(optionName);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatComponentConfig.getApiGetAuthorizerOptionUrl(), apiGetAuthorizerOptionRequest, WechatComponentRequest.class),
                apiGetAuthorizerOptionRequest, String.class);
        logger.debug(response);
        ApiGetAuthorizerOptionResponse apiGetAuthorizerOptionResponse = JSON.parseObject(response, ApiGetAuthorizerOptionResponse.class);
        if(apiGetAuthorizerOptionResponse.getErrcode() == WechatResponse.CODE_OK) {
            return apiGetAuthorizerOptionResponse;
        } else {
            logger.error("WechatComponentService-apiGetAuthorizerOption: " + apiGetAuthorizerOptionResponse.getErrcode()
                    + ", " + apiGetAuthorizerOptionResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse apiSetAuthorizerOption(String authorizerAppid, String optionName, int optionValue) {
        // 设置授权方的选项设置信息
        ApiSetAuthorizerOptionRequest apiSetAuthorizerOptionRequest = new ApiSetAuthorizerOptionRequest();
        apiSetAuthorizerOptionRequest.setComponent_appid(wechatComponentConfig.getAppId());
        apiSetAuthorizerOptionRequest.setComponent_access_token(getComponentAccessToken());
        apiSetAuthorizerOptionRequest.setAuthorizer_appid(authorizerAppid);
        apiSetAuthorizerOptionRequest.setOption_name(optionName);
        apiSetAuthorizerOptionRequest.setOption_value(optionValue);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatComponentConfig.getApiSetAuthorizerOptionUrl(), apiSetAuthorizerOptionRequest, WechatComponentRequest.class),
                apiSetAuthorizerOptionRequest, String.class);
        logger.debug(response);
        WechatResponse apiSetAuthorizerOptionResponse = JSON.parseObject(response, WechatResponse.class);
        if(apiSetAuthorizerOptionResponse.getErrcode() == WechatResponse.CODE_OK) {
            return apiSetAuthorizerOptionResponse;
        } else {
            logger.error("WechatComponentService-apiSetAuthorizerOption: "
                    + apiSetAuthorizerOptionResponse.getErrcode() + ", " + apiSetAuthorizerOptionResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse clearQuota() {
        ComponentClearQuotaRequest componentClearQuotaRequest = new ComponentClearQuotaRequest();
        componentClearQuotaRequest.setComponent_appid(wechatComponentConfig.getAppId());
        componentClearQuotaRequest.setComponent_access_token(getComponentAccessToken());
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatComponentConfig.getClearQuotaUrl(), componentClearQuotaRequest, WechatComponentRequest.class),
                componentClearQuotaRequest, String.class);
        logger.debug(response);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.debug("WechatComponentService-clearQuota: " + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }
}
