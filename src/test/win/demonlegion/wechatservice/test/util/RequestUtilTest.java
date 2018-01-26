package win.demonlegion.wechatservice.test.util;

import win.demonlegion.wechatservice.test.BaseTest;
import win.demonlegion.wechatservice.config.WechatComponentConfig;
import win.demonlegion.wechatservice.request.component.ApiQueryAuthRequest;
import win.demonlegion.wechatservice.request.component.WechatComponentRequest;
import win.demonlegion.wechatservice.util.RequestUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestUtilTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtilTest.class);

    @Autowired
    private WechatComponentConfig wechatComponentConfig;

    @Test
    public void getUrlTest() {
        ApiQueryAuthRequest apiQueryAuthRequest = new ApiQueryAuthRequest();
        apiQueryAuthRequest.setComponent_appid(wechatComponentConfig.getAppId());
        apiQueryAuthRequest.setAuthorization_code("023cd907b1e849a78806b3108800919d");
        String url =
                RequestUtil.getUrl(wechatComponentConfig.getApiSetAuthorizerOptionUrl(), apiQueryAuthRequest, WechatComponentRequest.class);
        logger.debug(url);
    }
}
