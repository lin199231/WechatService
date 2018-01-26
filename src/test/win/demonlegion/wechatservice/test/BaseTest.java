package win.demonlegion.wechatservice.test;

import win.demonlegion.wechatservice.WechatServiceApplication;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by john on 2017/6/19.
 * 测试基础类，写入一些配置
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WechatServiceApplication.class)
@ActiveProfiles(profiles = "test")
public class BaseTest extends Assert {}