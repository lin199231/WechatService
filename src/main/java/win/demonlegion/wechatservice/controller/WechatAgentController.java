package win.demonlegion.wechatservice.controller;

import win.demonlegion.wechatservice.common.HttpCode;
import win.demonlegion.wechatservice.service.CacheService;
import win.demonlegion.wechatservice.util.ResultUtil;
import win.demonlegion.wechatservice.util.XMLUtil;
import win.demonlegion.wechatservice.module.customservice.CustomServiceTextMessage;
import win.demonlegion.wechatservice.module.message.EventMessage;
import win.demonlegion.wechatservice.module.message.ReplyTextMessage;
import win.demonlegion.wechatservice.module.message.TextMessage;
import win.demonlegion.wechatservice.module.message.WechatMessage;
import win.demonlegion.wechatservice.response.component.ApiQueryAuthResponse;
import win.demonlegion.wechatservice.service.WechatAgentService;
import win.demonlegion.wechatservice.service.WechatComponentService;
import win.demonlegion.wechatservice.service.WechatService;
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
@RequestMapping("wechat/agent/")
public class WechatAgentController implements Serializable {
    private static final long serialVersionUID = 8902583476010923901L;
    private static final Logger logger = LoggerFactory.getLogger(WechatAgentController.class);

    @Autowired
    private WechatService wechatService;
    @Autowired
    private WechatAgentService wechatAgentService;
    @Autowired
    private WechatComponentService wechatComponentService;
    @Autowired
    private CacheService cacheService;

    /**
     * 代公众号授权链接获取
     */
    @GetMapping("authorize/url")
    public String getAuthorizationUrl(String appid) {
        if(StringUtils.isNotEmpty(appid)) {
            String authorizationUrl = wechatAgentService.getAuthorizationUrl(appid);

            return ResultUtil.getResult(HttpCode.OK, "url", authorizationUrl);
        } else return ResultUtil.getResult(HttpCode.NOT_COMPLETE_FIELD);
    }

    /**
     * 用户授权后微信回调
     */
    @RequestMapping("authorized")
    public String wechatComponentAuthorized(String code, String state, String appid) {
        // 处理回调结果
        if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(appid)) {
            String cacheState = cacheService.selectCache(appid + "_" + state);
            if(StringUtils.isNotEmpty(cacheState)) {
                // 判断state是否正确
                if(StringUtils.equals(cacheState, state)) {
                    // 获取用户数据
                    return ResultUtil.getResult(HttpCode.OK);
                } else return ResultUtil.getResult(HttpCode.WECHAT_AUTH_ERROR);
            } else return ResultUtil.getResult(HttpCode.STATE_EXPIRE);
        } else return ResultUtil.getResult(HttpCode.USER_NOT_AUTH);
    }

    /**
     * 消息与事件接收
     */
    @RequestMapping("message/{appid}")
    public String wechatAgentMessage(HttpServletRequest request, String msg_signature, String timestamp, String nonce,
                                     @PathVariable("appid")String appid) {
        logger.debug("msg_signature: " + msg_signature + ", timestamp: " + timestamp + ", nonce: " + nonce);
        // 处理用户消息与事件
        // 微信平台发送的是加密之后消息
        String encryptMessage;
        try {
            // 微信推送消息为xml格式, 先将xml数据解析成java对象
            encryptMessage = RequestUtil.readString(request.getInputStream());
            logger.debug(encryptMessage);
            String plainMessage = wechatComponentService.decryptWechatMessage(encryptMessage,
                    msg_signature, timestamp, nonce);
            logger.debug(plainMessage);

            WechatMessage wechatMessage = XMLUtil.parseXML(plainMessage, WechatMessage.class);
            // 通过推送消息的类型进行不同的操作
            return dealAgentMessage(wechatMessage, plainMessage, timestamp, nonce);
        } catch(IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    private String dealAgentMessage(WechatMessage wechatMessage, String plainMessage, String timestamp, String nonce) {
        String resultMessage = "";
        // 处理用户消息, 需要实现第三方平台全网测试的一些消息处理
        if(StringUtils.equals(wechatMessage.getMsgType(), WechatMessage.MSG_TYPE_EVENT)) {
            // 事件消息
            logger.debug("dealAgentMessage: EventMessage");
            EventMessage eventMessage = XMLUtil.parseXML(plainMessage, EventMessage.class);
            // 生成回复消息
            ReplyTextMessage replyMessage = new ReplyTextMessage();
            replyMessage.setContent(eventMessage.getEvent() + "from_callback");
            replyMessage.setFromUserName(eventMessage.getToUserName());
            replyMessage.setToUserName(eventMessage.getFromUserName());
            logger.debug(replyMessage.toString());
            resultMessage = wechatComponentService.encryptWechatMessage(
                    XMLUtil.toXML(replyMessage), replyMessage.getToUserName(), timestamp, nonce);
        } else if(StringUtils.equals(wechatMessage.getMsgType(), WechatMessage.MSG_TYPE_TEXT)) {
            // 文本消息
            TextMessage textMessage = XMLUtil.parseXML(plainMessage, TextMessage.class);
            // 判断是否属于微信第三方平台全网测试中的第2,3步验证
            if(StringUtils.equals(textMessage.getContent(), WechatMessage.COMPONENT_INSERT_TEST_STEP_TWO)) {
                // 微信第三方平台全网测试第二步
                ReplyTextMessage replyMessage = new ReplyTextMessage();
                replyMessage.setContent(textMessage.getContent() + "_callback");
                replyMessage.setFromUserName(textMessage.getToUserName());
                replyMessage.setToUserName(textMessage.getFromUserName());
                resultMessage = wechatComponentService.encryptWechatMessage(
                        XMLUtil.toXML(replyMessage), replyMessage.getToUserName(), timestamp, nonce);
            } else if(StringUtils.startsWith(textMessage.getContent(), WechatMessage.COMPONENT_INSERT_TEST_STEP_THREE)) {
                // 微信第三方平台全网测试第三步
                String queryAuthCode = StringUtils.substring(textMessage.getContent(), WechatMessage.COMPONENT_INSERT_TEST_STEP_THREE.length());
                ApiQueryAuthResponse apiQueryAuthResponse = wechatComponentService.apiQueryAuth(queryAuthCode);
                if(apiQueryAuthResponse != null) {
                    // 授权成功, 调用客服接口返回信息
                    CustomServiceTextMessage customServiceTextMessage = new CustomServiceTextMessage();
                    customServiceTextMessage.setContent(queryAuthCode + "_from_api");
                    wechatService.sendTextCustomServiceMesaage(
                            apiQueryAuthResponse.getAuthorization_info().getAuthorizer_access_token(),
                            textMessage.getFromUserName(), customServiceTextMessage);
                }
            } else {
                // 测试返回用户消息
                ReplyTextMessage replyMessage = new ReplyTextMessage();
                replyMessage.setContent("收到消息, 内容为" + textMessage.getContent());
                replyMessage.setFromUserName(textMessage.getToUserName());
                replyMessage.setToUserName(textMessage.getFromUserName());
                resultMessage = wechatComponentService.encryptWechatMessage(
                        XMLUtil.toXML(replyMessage), replyMessage.getToUserName(), timestamp, nonce);
            }
        } else {
            resultMessage = "success";
        }
        logger.debug(resultMessage);
        return resultMessage;
    }
}
