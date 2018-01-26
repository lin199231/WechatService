package win.demonlegion.wechatservice.test.service;

import com.alibaba.fastjson.JSON;
import win.demonlegion.wechatservice.test.BaseTest;
import win.demonlegion.wechatservice.module.AuthorizerOption;
import win.demonlegion.wechatservice.response.component.ApiAuthorizerTokenResponse;
import win.demonlegion.wechatservice.response.component.ApiGetAuthorizerInfoResponse;
import win.demonlegion.wechatservice.response.component.ApiGetAuthorizerOptionResponse;
import win.demonlegion.wechatservice.service.WechatComponentService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WechatComponentServiceTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(WechatComponentServiceTest.class);

    @Autowired
    private WechatComponentService wechatComponentService;

    @Test
    public void getAuthorizerAccessTokenTest() {
        String authorizerAppid = "wx402104fbdedf01cc";
        String authorizerAccessToken = wechatComponentService.getAuthorizerAccessToken(authorizerAppid);
        assertNotNull(authorizerAccessToken);
        logger.debug(authorizerAccessToken);
    }

    @Test
    public void apiGetAuthorizerInfoTest() {
        String authorizerAppid = "wx402104fbdedf01cc";
        ApiGetAuthorizerInfoResponse apiGetAuthorizerInfoResponse = wechatComponentService.apiGetAuthorizerInfo(authorizerAppid);
        assertNotNull(apiGetAuthorizerInfoResponse);
        logger.debug(apiGetAuthorizerInfoResponse.toString());
        logger.debug(JSON.toJSONString(apiGetAuthorizerInfoResponse));
    }

    @Test
    public void apiGetAuthorizerOptionTest() {
        String authorizerAppid = "wx402104fbdedf01cc";
        ApiGetAuthorizerOptionResponse apiGetAuthorizerOptionResponse = wechatComponentService.apiGetAuthorizerOption(
                authorizerAppid, AuthorizerOption.OPTION_NAME_LOCATION_REPORT);
        assertNotNull(apiGetAuthorizerOptionResponse);
        logger.debug(apiGetAuthorizerOptionResponse.toString());
        logger.debug(JSON.toJSONString(apiGetAuthorizerOptionResponse));
    }
}
