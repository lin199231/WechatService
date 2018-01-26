package win.demonlegion.wechatservice.web.wechat;

import win.demonlegion.wechatservice.common.HttpCode;
import win.demonlegion.wechatservice.response.WechatResponse;
import win.demonlegion.wechatservice.util.ResultUtil;
import win.demonlegion.wechatservice.util.XMLUtil;
import win.demonlegion.wechatservice.module.AuthorizerOption;
import win.demonlegion.wechatservice.module.message.ComponentPushMessage;
import win.demonlegion.wechatservice.response.component.ApiGetAuthorizerInfoResponse;
import win.demonlegion.wechatservice.response.component.ApiGetAuthorizerOptionResponse;
import win.demonlegion.wechatservice.response.component.ApiQueryAuthResponse;
import win.demonlegion.wechatservice.service.WechatComponentService;
import win.demonlegion.wechatservice.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@RestController
@CrossOrigin
@RequestMapping("wechat/component/")
public class WechatComponentController implements Serializable {
    private static final long serialVersionUID = 7956531920402091241L;
    private static final Logger logger = LoggerFactory.getLogger(WechatComponentController.class);

    @Autowired
    private WechatComponentService wechatComponentService;

    /**
     * 获取微信第三方平台定期授权事件
     */
    @RequestMapping("callback")
    public String componentPushMessage(HttpServletRequest request, String msg_signature, String timestamp, String nonce) {
        // 微信平台发送的是加密之后消息
        String encryptMessage;
        try {
            // 微信推送消息为xml格式, 先将xml数据解析成java对象
            encryptMessage = RequestUtil.readString(request.getInputStream());
            logger.debug(encryptMessage);
            String plainMessage = wechatComponentService.decryptWechatMessage(encryptMessage,
                    msg_signature, timestamp, nonce);
            logger.debug(plainMessage);
            ComponentPushMessage componentPushMessage = XMLUtil.parseXML(plainMessage, ComponentPushMessage.class);
            // 通过推送消息的类型进行不同的操作
            dealComponentPushMessage(componentPushMessage);

            return "success";
        } catch(IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 接收微信授权方事件消息
     */
    @RequestMapping("callback/{authorizerAppid}")
    public String authorizerPushMessage(HttpServletRequest request, String msg_signature, String timestamp, String nonce,
                                        @PathVariable("authorizerAppid")String authorizerAppid) {
        // 微信平台发送的是加密之后消息
        String encryptMessage;
        try {
            // 微信推送消息为xml格式, 先将xml数据解析成java对象
            encryptMessage = RequestUtil.readString(request.getInputStream());
            logger.debug(encryptMessage);
            String plainMessage = wechatComponentService.decryptWechatMessage(encryptMessage,
                    msg_signature, timestamp, nonce);
            logger.debug(plainMessage);
            ComponentPushMessage componentPushMessage = XMLUtil.parseXML(plainMessage, ComponentPushMessage.class);
            // 通过推送消息的类型进行不同的操作, 暂空

            return "success";
        } catch(IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    private void dealComponentPushMessage(ComponentPushMessage componentPushMessage) {
        switch(componentPushMessage.getInfoType()) {
            case ComponentPushMessage.INFO_TYPE_TICKET:
                // 微信每10分钟会推送一次component_verify_ticket, 收到后更新缓存
                wechatComponentService.setComponentVerifyTicket(componentPushMessage.getComponentVerifyTicket());
                break;
            case ComponentPushMessage.INFO_TYPE_AUTHORIZED:
                // 微信公众号授权消息处理
                break;
            case ComponentPushMessage.INFO_TYPE_UNAUTHORIZED:
                // 微信公众号取消授权消息处理
                break;
            case ComponentPushMessage.INFO_TYPE_UPDATEAUTHORIZED:
                // 微信公众号更新授权消息处理
                break;
        }
    }

    /**
     * 用户授权后微信回调
     */
    @RequestMapping("authorized/{brandWechatAuthorizerId}")
    public String wechatComponentAuthorized(String auth_code, Integer expires_in,
                                            @PathVariable("brandWechatAuthorizerId")String brandWechatAuthorizerId) {
        if(StringUtils.isNotEmpty(auth_code)) {
            // 通过微信的回调auth_code获取授权方数据
            ApiQueryAuthResponse apiQueryAuthResponse = wechatComponentService.apiQueryAuth(auth_code);
            // 获取授权方信息后更新数据库授权方数据
            if(apiQueryAuthResponse != null && StringUtils.isNotEmpty(apiQueryAuthResponse.getAuthorization_info().getAuthorizer_appid())) {
                // 通过微信的回调auth_code获取授权方账号数据
                ApiGetAuthorizerInfoResponse apiGetAuthorizerInfoResponse =
                        wechatComponentService.apiGetAuthorizerInfo(apiQueryAuthResponse.getAuthorization_info().getAuthorizer_appid());
                // 添加授权方的AccessToken到缓存中
                wechatComponentService.setAuthorizerAccessToken(apiQueryAuthResponse.getAuthorization_info().getAuthorizer_appid(),
                        apiQueryAuthResponse.getAuthorization_info().getAuthorizer_access_token(),
                        apiQueryAuthResponse.getAuthorization_info().getExpires_in());
                // 存储授权方信息, 需要按照具体业务逻辑自行实现
                int result = 1;

                if(result != 1) logger.error(apiQueryAuthResponse.getAuthorization_info().getAuthorizer_appid()
                        + ": 更新授权方数据出错");
            }
            return ResultUtil.getResult(HttpCode.OK);
        } else return ResultUtil.getResult(HttpCode.NOT_COMPLETE_FIELD);
    }

    /**
     * 获取微信授权登录页地址
     */
    @GetMapping("authorizer/url")
    public String getAuthorizerUrl() {
        // 获取授权链接时需要通过授权方的appid进行账号的绑定, 所以需要传递authorizerAppid
        String id = ""; // 测试授权方id

        // 判断是否已经添加对应品牌与授权方微信公众号appid的关联, 自行实现
        return ResultUtil.getResult(HttpCode.OK, "url",
                wechatComponentService.componentLoginPage(id));
    }

    /**
     * 获取授权方的选项设置信息
     */
    @GetMapping("authorizer/option")
    public String getComponentOption(String authorizerAppid, String optionName) {
        if(StringUtils.isNotEmpty(authorizerAppid) && StringUtils.isNotEmpty(optionName)) {
            ApiGetAuthorizerOptionResponse apiGetAuthorizerOptionResponse =
                    wechatComponentService.apiGetAuthorizerOption(authorizerAppid, optionName);
            if(apiGetAuthorizerOptionResponse != null) {
                AuthorizerOption authorizerOption = new AuthorizerOption();
                authorizerOption.setOption_name(apiGetAuthorizerOptionResponse.getOption_name());
                authorizerOption.setOption_value(apiGetAuthorizerOptionResponse.getOption_value());
                return ResultUtil.getResult(HttpCode.OK, authorizerOption);
            } else return ResultUtil.getResult(HttpCode.REQUEST_WECHAT_SERVICE_ERROR);
        } else return ResultUtil.getResult(HttpCode.NOT_COMPLETE_FIELD);
    }

    /**
     * 设置授权方的选项设置信息
     */
    @PostMapping("authorizer/option")
    public String setComponentOption(String authorizerAppid, String optionName, Integer optionValue) {
        if(StringUtils.isNotEmpty(authorizerAppid) && StringUtils.isNotEmpty(optionName)
                && optionValue != null) {
            WechatResponse apiSetAuthorizerOptionResponse =
                    wechatComponentService.apiSetAuthorizerOption(authorizerAppid, optionName, optionValue);
            if(apiSetAuthorizerOptionResponse != null) {
                return ResultUtil.getResult(HttpCode.OK);
            } else return ResultUtil.getResult(HttpCode.REQUEST_WECHAT_SERVICE_ERROR);
        } else return ResultUtil.getResult(HttpCode.NOT_COMPLETE_FIELD);
    }

    /**
     * 获取授权方的选项设置信息
     */
    @GetMapping("authorizer/info")
    public String getAuthorizerInfo(String authorizerAppid) {
        if(StringUtils.isNotEmpty(authorizerAppid)) {
            ApiGetAuthorizerInfoResponse apiGetAuthorizerInfoResponse =
                    wechatComponentService.apiGetAuthorizerInfo(authorizerAppid);
            if(apiGetAuthorizerInfoResponse != null) {
                return ResultUtil.getResult(HttpCode.OK, "authorizerInfo", apiGetAuthorizerInfoResponse);
            } else return ResultUtil.getResult(HttpCode.REQUEST_WECHAT_SERVICE_ERROR);
        } else return ResultUtil.getResult(HttpCode.NOT_COMPLETE_FIELD);
    }
}
