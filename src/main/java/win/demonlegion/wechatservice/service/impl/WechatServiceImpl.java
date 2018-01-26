package win.demonlegion.wechatservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import win.demonlegion.wechatservice.service.CacheService;
import win.demonlegion.wechatservice.util.RandomUtil;
import win.demonlegion.wechatservice.util.SHAUtil;
import win.demonlegion.wechatservice.util.TimestampUtil;
import win.demonlegion.wechatservice.module.UserInfo;
import win.demonlegion.wechatservice.module.UserList;
import win.demonlegion.wechatservice.module.action.QRCodeActionInfo;
import win.demonlegion.wechatservice.module.customservice.*;
import win.demonlegion.wechatservice.module.menu.WechatMenu;
import win.demonlegion.wechatservice.request.wechat.*;
import win.demonlegion.wechatservice.request.wechat.action.CreateQRCodeRequest;
import win.demonlegion.wechatservice.request.wechat.action.Long2ShortRequest;
import win.demonlegion.wechatservice.request.wechat.action.ShowQRCodeRequest;
import win.demonlegion.wechatservice.request.wechat.customservice.*;
import win.demonlegion.wechatservice.response.*;
import win.demonlegion.wechatservice.response.wechat.*;
import win.demonlegion.wechatservice.response.wechat.action.CreateQRCodeResponse;
import win.demonlegion.wechatservice.response.wechat.action.Long2ShortResponse;
import win.demonlegion.wechatservice.service.WechatService;
import win.demonlegion.wechatservice.module.JSWechatConfig;
import win.demonlegion.wechatservice.config.WechatConfig;
import win.demonlegion.wechatservice.module.WechatSignatureParameter;
import win.demonlegion.wechatservice.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("wechatService")
public class WechatServiceImpl implements WechatService {
    private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    private WechatConfig wechatConfig;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ClientCredentialResponse getClientCredential() {
        ClientCredentialRequest clientCredentialRequest = new ClientCredentialRequest();
        clientCredentialRequest.setAppid(wechatConfig.getAppId());
        clientCredentialRequest.setSecret(wechatConfig.getAppSecret());
        clientCredentialRequest.setGrant_type(wechatConfig.getCredGrantType());
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getClientCredentialUrl(), clientCredentialRequest), String.class);
        return JSON.parseObject(response, ClientCredentialResponse.class);
    }

    @Override
    public String getBaseAccessToken() {
        String baseAccessToken = cacheService.selectCache(WechatConfig.CACHE_BASE_ACCESSTOKEN);
        // 缓存中是否存在BaseAccessToken
        if(StringUtils.isNotEmpty(baseAccessToken)) {
            logger.debug("BaseAccessToken在缓存中存在: " + baseAccessToken);
        } else {
            // 缓存中没有缓存中是否存在BaseAccessToken, 访问微信获取
            ClientCredentialResponse clientCredentialResponse = getClientCredential();
            if(StringUtils.isNotEmpty(clientCredentialResponse.getAccess_token())) {
                // 写入缓存
                cacheService.saveToken(WechatConfig.CACHE_BASE_ACCESSTOKEN,
                        clientCredentialResponse.getAccess_token(), clientCredentialResponse.getExpires_in());
                baseAccessToken = clientCredentialResponse.getAccess_token();
            } else {
                logger.error(clientCredentialResponse.getErrcode() + ": " + clientCredentialResponse.getErrmsg());
            }
        }
        return baseAccessToken;
    }

    @Override
    public String getWechatLoginQRConnection() {
        QRConnectRequest qrConnectRequest = new QRConnectRequest();
        qrConnectRequest.setAppid(wechatConfig.getAppId());
        qrConnectRequest.setRedirect_uri(wechatConfig.getRedirectUri());
        qrConnectRequest.setResponse_type(wechatConfig.getResponseType());
        qrConnectRequest.setScope(wechatConfig.getLoginScope());
        String state = RandomUtil.getRandomString(32);
        cacheService.saveTempToken(state, state);
        qrConnectRequest.setState(state);
        JSONObject json = new JSONObject();
        json.put("url", RequestUtil.getUrl(wechatConfig.getQrconnectUrl(), qrConnectRequest, wechatConfig.getSuffix()));
        json.put("state", state);
        return json.toJSONString();
    }

    @Override
    public AccessTokenResponse getAccessToken(String code) {
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setAppid(wechatConfig.getAppId());
        accessTokenRequest.setSecret(wechatConfig.getAppSecret());
        accessTokenRequest.setCode(code);
        accessTokenRequest.setGrant_type(wechatConfig.getAuthGrantType());
        String response = restTemplate.getForObject(RequestUtil.getUrl(wechatConfig.getAccessTokenUrl(), accessTokenRequest), String.class);
        return JSON.parseObject(response, AccessTokenResponse.class);
    }

    @Override
    public RefreshAccessTokenResponse refreshAccessToken(String refreshToken) {
        RefreshAccessTokenRequest refreshAccessTokenRequest = new RefreshAccessTokenRequest();
        refreshAccessTokenRequest.setAppid(wechatConfig.getAppId());
        refreshAccessTokenRequest.setGrant_type(wechatConfig.getRefreshGrantType());
        refreshAccessTokenRequest.setRefresh_token(refreshToken);
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getRefreshAccessTokenUrl(), refreshAccessTokenRequest), String.class);
        return JSON.parseObject(response, RefreshAccessTokenResponse.class);
    }

    @Override
    public WechatResponse verifyAccessToken(String accessToken, String openid) {
        VerifyAccessTokenRequest verifyAccessTokenRequest = new VerifyAccessTokenRequest();
        verifyAccessTokenRequest.setAccess_token(accessToken);
        verifyAccessTokenRequest.setOpenid(openid);
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getVerifyAccessTokenUrl(), verifyAccessTokenRequest), String.class);
        return JSON.parseObject(response, WechatResponse.class);
    }

    @Override
    public UserInfo getUserInfo(String accessToken, String openId) {
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setAccess_token(accessToken);
        userInfoRequest.setOpenid(openId);
        userInfoRequest.setLang(wechatConfig.getLanguage());
        String response = restTemplate.getForObject(RequestUtil.getUrl(wechatConfig.getUserInfoUrl(), userInfoRequest), String.class);
        return JSON.parseObject(response, UserInfo.class);
    }

    @Override
    public String getTicket(String accessToken) {
        // 微信js-ticket有效期7200秒, 如果缓存中存在则使用缓存
        String jsApiTicket = cacheService.selectCache(JSWechatConfig.CACHE_WECHAT_TICKET);

        if(StringUtils.isNotEmpty(jsApiTicket)) {
            logger.debug("微信js-ticket在缓存中存在: " + jsApiTicket);
        } else {
            // ticket在缓存中不存在, 则去访问微信获取

            GetTicketRequest getTicketRequest = new GetTicketRequest();
            // 使用通用AccessToken获取js票据
            getTicketRequest.setAccess_token(accessToken);
            getTicketRequest.setType(wechatConfig.getJsapiType());
            String response = restTemplate.getForObject(RequestUtil.getUrl(wechatConfig.getGetTicketUrl(), getTicketRequest), String.class);
            GetTicketResponse getTicketResponse = JSON.parseObject(response, GetTicketResponse.class);
            if(getTicketResponse != null) {
                if(getTicketResponse.getErrcode() == WechatResponse.CODE_OK) {
                    cacheService.saveToken(JSWechatConfig.CACHE_WECHAT_TICKET, getTicketResponse.getTicket(), getTicketResponse.getExpires_in());
                    jsApiTicket = getTicketResponse.getTicket();
                } else {
                    logger.error(getTicketResponse.getErrcode() + ": " + getTicketResponse.getErrmsg());
                }
            }
        }
        return jsApiTicket;
    }

    @Override
    public String generateSignature(String jsApiTicket, String noncestr, long timestamp, String url) {
        WechatSignatureParameter wechatSignatureParameter = new WechatSignatureParameter();
        wechatSignatureParameter.setJsapi_ticket(jsApiTicket);
        wechatSignatureParameter.setNoncestr(noncestr);
        wechatSignatureParameter.setTimestamp(timestamp);
        wechatSignatureParameter.setUrl(url);
        // 微信签名参数不需要进行URL转义
        return SHAUtil.SHA1(RequestUtil.getParameters(wechatSignatureParameter, false));
    }

    @Override
    public JSWechatConfig getJSWechatConfig(String accessToken, String url) {
        JSWechatConfig jsWechatConfig = new JSWechatConfig();
        jsWechatConfig.setAppId(wechatConfig.getAppId());
        // 32位随机码
        jsWechatConfig.setNonceStr(RandomUtil.getRandomString(16));
        jsWechatConfig.setTimestamp(TimestampUtil.getDatetime().getTime() / 1000);
        jsWechatConfig.setSignature(generateSignature(getTicket(accessToken), jsWechatConfig.getNonceStr(),
                jsWechatConfig.getTimestamp(), url));
        return jsWechatConfig;
    }

    // 自定义菜单
    @Override
    public WechatResponse menuCreate(String accessToken, WechatMenu wechatMenu) {
        WechatRequest wechatRequest = new WechatRequest();
        wechatRequest.setAccess_token(accessToken);
        String response = restTemplate.postForObject(RequestUtil.getUrl(
                wechatConfig.getMenuCreateUrl(), wechatRequest), JSON.toJSONString(wechatMenu), String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-menuCreate: " + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public MenuGetResponse menuGet(String accessToken) {
        WechatRequest wechatRequest = new WechatRequest();
        wechatRequest.setAccess_token(accessToken);
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getMenuGetUrl(), wechatRequest), String.class);
        MenuGetResponse menuGetResponse = JSON.parseObject(response, MenuGetResponse.class);
        if(menuGetResponse.getErrcode() == WechatResponse.CODE_OK) {
            return menuGetResponse;
        } else {
            logger.error("WechatService-menuGet: " + menuGetResponse.getErrcode() + ", " + menuGetResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse menuDelete(String accessToken) {
        WechatRequest wechatRequest = new WechatRequest();
        wechatRequest.setAccess_token(accessToken);
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getMenuDeleteUrl(), wechatRequest), String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-menuDelete: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    // 客服管理
    @Override
    public CustomServiceAccountListResponse customServiceAccountList(String accessToken) {
        WechatRequest wechatRequest = new WechatRequest();
        wechatRequest.setAccess_token(accessToken);
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getCustomServiceAccountListUrl(), wechatRequest), String.class);
        CustomServiceAccountListResponse customServiceAccountListResponse =
                JSON.parseObject(response, CustomServiceAccountListResponse.class);
        if(customServiceAccountListResponse.getErrcode() == WechatResponse.CODE_OK) {
            return customServiceAccountListResponse;
        } else {
            logger.error("WechatService-customServiceAccountList: "
                    + customServiceAccountListResponse.getErrcode() + ", " + customServiceAccountListResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse addCustomServiceAccount(String accessToken, String kfAccount, String nickname, String password) {
        CustomServiceAccountRequest customServiceAccountRequest = new CustomServiceAccountRequest();
        customServiceAccountRequest.setAccess_token(accessToken);
        customServiceAccountRequest.setKf_account(kfAccount);
        customServiceAccountRequest.setNickname(nickname);
        customServiceAccountRequest.setPassword(password);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatConfig.getAddCustomServiceAccountUrl(), customServiceAccountRequest, WechatRequest.class),
                customServiceAccountRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-addCustomServiceAccount: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse updateCustomServiceAccount(String accessToken, String kfAccount, String nickname, String password) {
        CustomServiceAccountRequest customServiceAccountRequest = new CustomServiceAccountRequest();
        customServiceAccountRequest.setKf_account(kfAccount);
        customServiceAccountRequest.setNickname(nickname);
        customServiceAccountRequest.setPassword(password);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatConfig.getUpdateCustomServiceAccountUrl(), customServiceAccountRequest, WechatRequest.class),
                customServiceAccountRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-updateCustomServiceAccount: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse deleteCustomServiceAccount(String accessToken, String kfAccount, String nickname, String password) {
        CustomServiceAccountRequest customServiceAccountRequest = new CustomServiceAccountRequest();
        customServiceAccountRequest.setKf_account(kfAccount);
        customServiceAccountRequest.setNickname(nickname);
        customServiceAccountRequest.setPassword(password);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatConfig.getDeleteCustomServiceAccountUrl(), customServiceAccountRequest, WechatRequest.class),
                customServiceAccountRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-deleteCustomServiceAccount: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse sendTextCustomServiceMesaage(String accessToken, String touser,
                                               CustomServiceTextMessage customServiceTextMessage) {
        CustomServiceSendTextRequest customServiceSendTextRequest = new CustomServiceSendTextRequest();
        customServiceSendTextRequest.setAccess_token(accessToken);
        customServiceSendTextRequest.setTouser(touser);
        customServiceSendTextRequest.setText(customServiceTextMessage);
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getSendCustomServiceMessageUrl(), customServiceSendTextRequest, WechatRequest.class),
                customServiceSendTextRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-sendTextCustomServiceMesaage: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse sendImageCustomServiceMesaage(String accessToken, String touser,
                                                CustomServiceImageMessage customServiceImageMessage) {
        CustomServiceSendImageRequest customServiceSendImageRequest = new CustomServiceSendImageRequest();
        customServiceSendImageRequest.setAccess_token(accessToken);
        customServiceSendImageRequest.setTouser(touser);
        customServiceSendImageRequest.setImage(customServiceImageMessage);
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getSendCustomServiceMessageUrl(), customServiceSendImageRequest, WechatRequest.class),
                customServiceSendImageRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-sendImageCustomServiceMesaage: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse sendVoiceCustomServiceMesaage(String accessToken, String touser,
                                                CustomServiceVoiceMessage customServiceVoiceMessage) {
        CustomServiceSendVoiceRequest customServiceSendVoiceRequest = new CustomServiceSendVoiceRequest();
        customServiceSendVoiceRequest.setAccess_token(accessToken);
        customServiceSendVoiceRequest.setTouser(touser);
        customServiceSendVoiceRequest.setVoice(customServiceVoiceMessage);
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getSendCustomServiceMessageUrl(), customServiceSendVoiceRequest, WechatRequest.class),
                customServiceSendVoiceRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-sendTextCustomServiceMesaage: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse sendVideoCustomServiceMesaage(String accessToken, String touser,
                                                CustomServiceVideoMessage customServiceVideoMessage) {
        CustomServiceSendVideoRequest customServiceSendVideoRequest = new CustomServiceSendVideoRequest();
        customServiceSendVideoRequest.setAccess_token(accessToken);
        customServiceSendVideoRequest.setTouser(touser);
        customServiceSendVideoRequest.setVideo(customServiceVideoMessage);
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getSendCustomServiceMessageUrl(), customServiceSendVideoRequest, WechatRequest.class),
                customServiceSendVideoRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-sendTextCustomServiceMesaage: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse sendMusicCustomServiceMesaage(String accessToken, String touser,
                                                CustomServiceMusicMessage customServiceMusicMessage) {
        CustomServiceSendMusicRequest customServiceSendMusicRequest = new CustomServiceSendMusicRequest();
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getSendCustomServiceMessageUrl(), customServiceSendMusicRequest, WechatRequest.class),
                customServiceSendMusicRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.error("WechatService-sendTextCustomServiceMesaage: "
                    + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public UserList getUserList(String accessToken) {
        UserList userList = new UserList();
        String nextOpenid = null;
        while(userList.getTotal() == 0 || userList.getTotal() > userList.getCount()) {
            UserListResponse userListResponse = queryUserList(accessToken, nextOpenid);
            if (userListResponse.getErrcode() == WechatResponse.CODE_OK) {
                nextOpenid = userListResponse.getNext_openid();
                userList.setTotal(userListResponse.getTotal());
                userList.setCount(userList.getCount() + userListResponse.getCount());
                userList.getOpenids().addAll(userListResponse.getData().getOpenid());
            } else {
                logger.debug("WechatService-userList: " + userListResponse.getErrcode() + ", " + userListResponse.getErrmsg());
                return null;
            }
        }
        return userList;
    }

    private UserListResponse queryUserList(String accessToken, String nextOpenid) {
        UserListRequest userListRequest = new UserListRequest();
        userListRequest.setAccess_token(accessToken);
        userListRequest.setNext_openid(nextOpenid);
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getUserListUrl(), userListRequest), String.class);
        logger.debug(response);
        return JSON.parseObject(response, UserListResponse.class);
    }

    @Override
    public UserInfo getUserInfoUnionID(String accessToken, String openid) {
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setAccess_token(accessToken);
        userInfoRequest.setOpenid(openid);
        userInfoRequest.setLang(wechatConfig.getLanguage());
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getUserInfoUnionIDUrl(), userInfoRequest), String.class);
        UserInfoResponse userInfoResponse = JSON.parseObject(response, UserInfoResponse.class);
        if(userInfoResponse.getErrcode() == WechatResponse.CODE_OK) {
            return new UserInfo(userInfoResponse);
        } else {
            logger.error("WechatService-getUserInfoUnionID: " + userInfoResponse.getErrcode() + ", " + userInfoResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public List<UserInfo> getBatchUserInfo(String accessToken, List<String> userList) {
        BatchUserInfoRequest batchUserInfoRequest = new BatchUserInfoRequest();
        batchUserInfoRequest.setAccess_token(accessToken);
        batchUserInfoRequest.setUser_list(userList, wechatConfig.getLanguage());
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatConfig.getBatchUserInfoUrl(), batchUserInfoRequest, WechatRequest.class), batchUserInfoRequest, String.class);
        BatchUserInfoResponse batchUserInfoResponse = JSON.parseObject(response, BatchUserInfoResponse.class);
        if(batchUserInfoResponse.getErrcode() == WechatResponse.CODE_OK) {
            return batchUserInfoResponse.getUser_info_list();
        } else {
            logger.error("WechatService-getBatchUserInfo: " + batchUserInfoResponse.getErrcode() + ", " + batchUserInfoResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public UserList selectBlacklist(String accessToken) {
        UserList userList = new UserList();
        String nextOpenid = null;
        while(userList.getTotal() == 0 || userList.getTotal() > userList.getCount()) {
            UserListResponse userListResponse = queryBlacklist(accessToken, nextOpenid);
            if (userListResponse.getErrcode() == WechatResponse.CODE_OK) {
                nextOpenid = userListResponse.getNext_openid();
                userList.setTotal(userListResponse.getTotal());
                userList.setCount(userList.getCount() + userListResponse.getCount());
                userList.getOpenids().addAll(userListResponse.getData().getOpenid());
            } else {
                logger.debug("WechatService-userList: " + userListResponse.getErrcode() + ", " + userListResponse.getErrmsg());
                return null;
            }
        }
        return userList;
    }

    private UserListResponse queryBlacklist(String accessToken, String nextOpenid) {
        SelectBlacklistRequest selectBlacklistRequest = new SelectBlacklistRequest();
        selectBlacklistRequest.setAccess_token(accessToken);
        selectBlacklistRequest.setBegin_openid(nextOpenid);
        String response = restTemplate.getForObject(
                RequestUtil.getUrl(wechatConfig.getSelectBlacklistUrl(), selectBlacklistRequest), String.class);
        logger.debug(response);
        return JSON.parseObject(response, UserListResponse.class);
    }

    @Override
    public WechatResponse batchBlacklist(String accessToken, List<String> openidList) {
        BatchBlacklistRequest batchBlacklistRequest = new BatchBlacklistRequest();
        batchBlacklistRequest.setAccess_token(accessToken);
        batchBlacklistRequest.setOpenid_list(openidList);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatConfig.getBatchBlacklistUrl(), batchBlacklistRequest, WechatRequest.class),
                batchBlacklistRequest, String.class);
        logger.debug(response);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.debug("WechatService-batchBlacklist: " + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public WechatResponse batchUnblacklist(String accessToken, List<String> openidList) {
        // 添加和删除黑名单请求参数一致, 所以公用一个请求实体类
        BatchBlacklistRequest batchBlacklistRequest = new BatchBlacklistRequest();
        batchBlacklistRequest.setAccess_token(accessToken);
        batchBlacklistRequest.setOpenid_list(openidList);
        String response = restTemplate.postForObject(
                RequestUtil.getUrl(wechatConfig.getBatchUnblacklistUrl(), batchBlacklistRequest, WechatResponse.class),
                batchBlacklistRequest, String.class);
        WechatResponse wechatResponse = JSON.parseObject(response, WechatResponse.class);
        if(wechatResponse.getErrcode() == WechatResponse.CODE_OK) {
            return wechatResponse;
        } else {
            logger.debug("WechatService-batchUnblacklist: " + wechatResponse.getErrcode() + ", " + wechatResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public CreateQRCodeResponse createQRCode(String accessToken, String action, int sceneId) {
        CreateQRCodeRequest createQRCodeRequest = new CreateQRCodeRequest();
        createQRCodeRequest.setAccess_token(accessToken);
        createQRCodeRequest.setAction_name(action);
        createQRCodeRequest.setExpire_seconds(CreateQRCodeRequest.MONTH_EXPIRE_SECONDS);
        QRCodeActionInfo qrCodeActionInfo = new QRCodeActionInfo();
        qrCodeActionInfo.setScene_id(sceneId);
        createQRCodeRequest.setAction_info(qrCodeActionInfo);
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getCreateQRCodeUrl(),
                createQRCodeRequest, WechatRequest.class), createQRCodeRequest, String.class);
        logger.debug(response);
        CreateQRCodeResponse createQRCodeResponse = JSON.parseObject(response, CreateQRCodeResponse.class);
        if(createQRCodeResponse.getErrcode() == WechatResponse.CODE_OK) {
            return createQRCodeResponse;
        } else {
            logger.error("WechatService-createQRCode: " + createQRCodeResponse.getErrcode()
                    + ", " + createQRCodeResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public CreateQRCodeResponse createQRCode(String accessToken, String action, String sceneStr) {
        CreateQRCodeRequest createQRCodeRequest = new CreateQRCodeRequest();
        createQRCodeRequest.setAccess_token(accessToken);
        createQRCodeRequest.setAction_name(action);
        createQRCodeRequest.setExpire_seconds(CreateQRCodeRequest.MONTH_EXPIRE_SECONDS);
        QRCodeActionInfo qrCodeActionInfo = new QRCodeActionInfo();
        qrCodeActionInfo.setScene_str(sceneStr);
        createQRCodeRequest.setAction_info(qrCodeActionInfo);
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getCreateQRCodeUrl(),
                createQRCodeRequest, WechatRequest.class), createQRCodeRequest, String.class);
        logger.debug(response);
        CreateQRCodeResponse createQRCodeResponse = JSON.parseObject(response, CreateQRCodeResponse.class);
        if(createQRCodeResponse.getErrcode() == WechatResponse.CODE_OK) {
            return createQRCodeResponse;
        } else {
            logger.error("WechatService-createQRCode: " + createQRCodeResponse.getErrcode()
                    + ", " + createQRCodeResponse.getErrmsg());
            return null;
        }
    }

    @Override
    public String showQRCode(String ticket) {
        ShowQRCodeRequest showQRCodeRequest = new ShowQRCodeRequest();
        showQRCodeRequest.setTicket(ticket);
        return RequestUtil.getUrl(wechatConfig.getShowQRCodeUrl(), showQRCodeRequest);
    }

    @Override
    public Long2ShortResponse long2short(String accessToken, String longUrl) {
        Long2ShortRequest long2shortRequest = new Long2ShortRequest();
        long2shortRequest.setAccess_token(accessToken);
        long2shortRequest.setLong_url(longUrl);
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getLong2shortUrl(),
                long2shortRequest, WechatRequest.class), long2shortRequest, String.class);
        logger.debug(response);
        Long2ShortResponse long2shortResponse = JSON.parseObject(response, Long2ShortResponse.class);
        if(long2shortResponse.getErrcode() == WechatResponse.CODE_OK) {
            return long2shortResponse;
        } else {
            logger.error("WechatService-long2short: " + long2shortResponse.getErrcode()
                    + ", " + long2shortResponse.getErrmsg());
            return null;
        }
    }

    // 通用接口
    @Override
    public GetCallbackIpResponse getCallbackIp(String accessToken) {
        GetCallbackIpRequest getCallbackIpRequest = new GetCallbackIpRequest();
        getCallbackIpRequest.setAccess_token(accessToken);
        // 请求微信接口
        String response = restTemplate.getForObject(RequestUtil.getUrl(wechatConfig.getGetCallbackIpUrl(), getCallbackIpRequest), String.class);
        return JSON.parseObject(response, GetCallbackIpResponse.class);
    }

    @Override
    public WechatResponse clearQuota(String accessToken) {
        ClearQuotaRequest clearQuotaRequest = new ClearQuotaRequest();
        clearQuotaRequest.setAppid(wechatConfig.getAppId());
        clearQuotaRequest.setAccess_token(accessToken);
        String response = restTemplate.postForObject(RequestUtil.getUrl(wechatConfig.getClearQuotaUrl(), clearQuotaRequest, WechatRequest.class),
                clearQuotaRequest, String.class);
        return JSON.parseObject(response, WechatResponse.class);
    }
}
