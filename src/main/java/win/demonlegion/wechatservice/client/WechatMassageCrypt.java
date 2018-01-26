package win.demonlegion.wechatservice.client;

import win.demonlegion.wechatservice.config.WechatComponentConfig;
import win.demonlegion.wechatservice.crypt.WXBizMsgCrypt;
import win.demonlegion.wechatservice.exception.AesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatMassageCrypt {
    @Autowired
    private WechatComponentConfig wechatComponentConfig;

    @Bean
    public WXBizMsgCrypt getWXBizMsgCrypt() throws AesException {
        return new WXBizMsgCrypt(wechatComponentConfig.getMessageToken(), wechatComponentConfig.getEncodingAESKey(),
                wechatComponentConfig.getAppId());
    }
}
