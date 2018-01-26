package win.demonlegion.wechatservice.service;

import win.demonlegion.wechatservice.module.JSWechatConfig;
import win.demonlegion.wechatservice.module.UserInfo;
import win.demonlegion.wechatservice.module.UserList;
import win.demonlegion.wechatservice.module.customservice.*;
import win.demonlegion.wechatservice.module.menu.WechatMenu;
import win.demonlegion.wechatservice.response.wechat.*;
import win.demonlegion.wechatservice.response.WechatResponse;
import win.demonlegion.wechatservice.response.wechat.action.CreateQRCodeResponse;
import win.demonlegion.wechatservice.response.wechat.action.Long2ShortResponse;

import java.util.List;

public interface WechatService {
    ClientCredentialResponse getClientCredential();
    String getBaseAccessToken();
    String getWechatLoginQRConnection();
    AccessTokenResponse getAccessToken(String code);
    RefreshAccessTokenResponse refreshAccessToken(String refreshToken);
    WechatResponse verifyAccessToken(String accessToken, String openid);
    UserInfo getUserInfo(String accessToken, String openId);
    String getTicket(String accessToken);
    String generateSignature(String jsApiTicket, String noncestr, long timestamp, String url);
    JSWechatConfig getJSWechatConfig(String accessToken, String url);
    // 自定义菜单
    WechatResponse menuCreate(String accessToken, WechatMenu wechatMenu);
    MenuGetResponse menuGet(String accessToken);
    WechatResponse menuDelete(String accessToken);
    // 客服服务
    CustomServiceAccountListResponse customServiceAccountList(String accessToken);
    WechatResponse addCustomServiceAccount(String accessToken, String kfAccount, String nickname, String password);
    WechatResponse updateCustomServiceAccount(String accessToken, String kfAccount, String nickname, String password);
    WechatResponse deleteCustomServiceAccount(String accessToken, String kfAccount, String nickname, String password);
//    WechatResponse setCustomServiceAccountHeadimg(String accessToken, String kfAccount, File headimg);
    WechatResponse sendTextCustomServiceMesaage(String accessToken, String touser, CustomServiceTextMessage customServiceTextMessage);
    WechatResponse sendImageCustomServiceMesaage(String accessToken, String touser, CustomServiceImageMessage customServiceImageMessage);
    WechatResponse sendVoiceCustomServiceMesaage(String accessToken, String touser, CustomServiceVoiceMessage customServiceVoiceMessage);
    WechatResponse sendVideoCustomServiceMesaage(String accessToken, String touser, CustomServiceVideoMessage customServiceVideoMessage);
    WechatResponse sendMusicCustomServiceMesaage(String accessToken, String touser, CustomServiceMusicMessage customServiceMusicMessage);
    // 用户管理
    UserList getUserList(String accessToken);
    UserInfo getUserInfoUnionID(String accessToken, String openid);
    List<UserInfo> getBatchUserInfo(String accessToken, List<String> userList);
    // 黑名单管理
    UserList selectBlacklist(String accessToken);
    WechatResponse batchBlacklist(String accessToken, List<String> openidList);
    WechatResponse batchUnblacklist(String accessToken, List<String> openidList);
    // 附加操作
    CreateQRCodeResponse createQRCode(String accessToken, String action, int sceneId);
    CreateQRCodeResponse createQRCode(String accessToken, String action, String sceneStr);
    String showQRCode(String ticket);
    Long2ShortResponse long2short(String accessToken, String longUrl);
    GetCallbackIpResponse getCallbackIp(String accessToken);
    WechatResponse clearQuota(String accessToken);
}
