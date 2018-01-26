package win.demonlegion.wechatservice.test.service;

import win.demonlegion.wechatservice.test.BaseTest;
import win.demonlegion.wechatservice.service.WechatAgentService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WechatAgentServiceTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(WechatAgentServiceTest.class);

    @Autowired
    private WechatAgentService wechatAgentService;

    @Test
    public void getAuthorizationUrlTest() {
        String appid = "wx8b08e49c6c9f8aca";
        String url = wechatAgentService.getAuthorizationUrl(appid);
        logger.debug(url);
    }
}
