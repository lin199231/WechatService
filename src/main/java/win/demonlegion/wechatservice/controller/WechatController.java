package win.demonlegion.wechatservice.controller;

import win.demonlegion.wechatservice.common.HttpCode;
import win.demonlegion.wechatservice.util.ResultUtil;
import win.demonlegion.wechatservice.response.wechat.GetCallbackIpResponse;
import win.demonlegion.wechatservice.service.WechatService;
import win.demonlegion.wechatservice.module.JSWechatConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@CrossOrigin
@RequestMapping("wechat/")
public class WechatController implements Serializable {
    private static final long serialVersionUID = -8410399491945461922L;
    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WechatService wechatService;

    /**
     * 查看用户个人信息
     */
    @GetMapping("js/config")
    public String getJSConfig(String url) {
        if(StringUtils.isNotEmpty(url)) {
            String baseAccessToken = wechatService.getBaseAccessToken();
            JSWechatConfig jsWechatConfig = wechatService.getJSWechatConfig(baseAccessToken, url);
            if (jsWechatConfig != null) {
                return ResultUtil.getResult(HttpCode.OK, "jsWechatConfig", jsWechatConfig);
            } else return ResultUtil.getResult(HttpCode.ERROR);
        } else return ResultUtil.getResult(HttpCode.NOT_COMPLETE_FIELD);
    }

    /**
     * 获取微信回调服务器ip
     */
    @GetMapping("callback/ip")
    public String getCallbackIp() {
        String baseAccessToken = wechatService.getBaseAccessToken();
        GetCallbackIpResponse getCallbackIpResponse = wechatService.getCallbackIp(baseAccessToken);
        if (getCallbackIpResponse != null) {
            return ResultUtil.getResult(HttpCode.OK, "getCallbackIpResponse", getCallbackIpResponse);
        } else return ResultUtil.getResult(HttpCode.ERROR);
    }
}
