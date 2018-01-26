package win.demonlegion.wechatservice.test.util;

import com.alibaba.fastjson.JSON;
import win.demonlegion.wechatservice.util.XMLUtil;
import win.demonlegion.wechatservice.module.message.ComponentPushMessage;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLUtilTest extends Assert {
    private static final Logger logger = LoggerFactory.getLogger(XMLUtilTest.class);

    @Test
    public void toXMLTest() {
        ComponentPushMessage componentPushMessage = new ComponentPushMessage();
        componentPushMessage.setAppId("wx8b08e49c6c9f8aca");
        componentPushMessage.setCreateTime(1413192605);
        componentPushMessage.setInfoType("authorized");
        componentPushMessage.setAuthorizerAppid("wx613759ff4992254b");
        componentPushMessage.setAuthorizationCode("Cx_Dk6qiBE0Dmx4EmlT3oRfArPvwSQ-oa3NL");
        componentPushMessage.setAuthorizationCodeExpiredTime(1413592605);
        componentPushMessage.setPreAuthCode("Cx_Dk6qiBE0Dmx4EmlT3oRfArPvwSQ-oa3NL_fwHM7VI08r52wazoZX2Rhpz1dEw");
        String xml = XMLUtil.toXML(componentPushMessage);
        logger.debug(xml);
    }

    @Test
    public void parseXMLTest() {
        String xml = "<xml>" +
                "<AppId>wx8b08e49c6c9f8aca</AppId>" +
                "<CreateTime>1413192605</CreateTime>" +
                "<InfoType>component_verify_ticket</InfoType>" +
                "<ComponentVerifyTicket>fwHM7VI08r52wazoZX2Rhpz1dEw</ComponentVerifyTicket>" +
                "<AuthorizerAppid>wx613759ff4992254b</AuthorizerAppid>" +
                "<AuthorizationCode>Cx_Dk6qiBE0Dmx4EmlT3oRfArPvwSQ-oa3NL</AuthorizationCode>" +
                "<AuthorizationCodeExpiredTime>1413592605</AuthorizationCodeExpiredTime>" +
                "<PreAuthCode>Cx_Dk6qiBE0Dmx4EmlT3oRfArPvwSQ-oa3NL_fwHM7VI08r52wazoZX2Rhpz1dEw</PreAuthCode>" +
                "</xml>";
        ComponentPushMessage componentPushMessage = XMLUtil.parseXML(xml, ComponentPushMessage.class);
        logger.debug(componentPushMessage.toString());
        logger.debug(JSON.toJSONString(componentPushMessage));
    }
}
