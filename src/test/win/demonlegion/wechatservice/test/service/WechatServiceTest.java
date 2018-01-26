package win.demonlegion.wechatservice.test.service;

import win.demonlegion.wechatservice.test.BaseTest;
import win.demonlegion.wechatservice.module.UserInfo;
import win.demonlegion.wechatservice.module.UserList;
import win.demonlegion.wechatservice.module.customservice.*;
import win.demonlegion.wechatservice.module.menu.WechatMenu;
import win.demonlegion.wechatservice.module.menu.WechatMenuButton;
import win.demonlegion.wechatservice.request.wechat.action.CreateQRCodeRequest;
import win.demonlegion.wechatservice.response.WechatResponse;
import win.demonlegion.wechatservice.response.wechat.MenuGetResponse;
import win.demonlegion.wechatservice.response.wechat.action.CreateQRCodeResponse;
import win.demonlegion.wechatservice.response.wechat.action.Long2ShortResponse;
import win.demonlegion.wechatservice.service.WechatComponentService;
import win.demonlegion.wechatservice.service.WechatService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WechatServiceTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(WechatServiceTest.class);

    @Autowired
    private WechatService wechatService;
    @Autowired
    private WechatComponentService wechatComponentService;

    @Test
    public void getTicketTest() {
        String baseAccessToken = wechatService.getBaseAccessToken();
        String ticket = wechatService.getTicket(baseAccessToken);
        logger.debug(ticket);
        assertNotNull(ticket);
    }

    @Test
    public void generateSignatureTest() {
        String signature = wechatService.generateSignature("sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg",
                "Wm3WZYTPz0wzccnW", 1414587457, "http://mp.weixin.qq.com?params=value");
        logger.debug(signature);
        assertEquals("0f9de62fce790f9a083d5c99e95740ceb90c27ed", signature);
    }

    @Test
    public void menuCreateTest() {
        WechatMenu wechatMenu = new WechatMenu();
        WechatMenuButton wechatMenuButton1 = new WechatMenuButton();
        wechatMenuButton1.setName("菜单1");
        WechatMenuButton wechatMenuButton1_1 = new WechatMenuButton();
        wechatMenuButton1_1.setName("子菜单1");
        wechatMenuButton1_1.setType(WechatMenuButton.BUTTON_TYPE_CLICK);
        wechatMenuButton1_1.setKey("sub_button1");
        WechatMenuButton wechatMenuButton1_2 = new WechatMenuButton();
        wechatMenuButton1_2.setName("子菜单2");
        wechatMenuButton1_2.setType(WechatMenuButton.BUTTON_TYPE_VIEW);
        wechatMenuButton1_2.setUrl("https://www.baidu.com");
        List<WechatMenuButton> subButton = new ArrayList<>();
        subButton.add(wechatMenuButton1_1);
        subButton.add(wechatMenuButton1_2);
        wechatMenuButton1.setSub_button(subButton);
        WechatMenuButton wechatMenuButton2 = new WechatMenuButton();
        wechatMenuButton2.setName("菜单2");
        wechatMenuButton2.setType(WechatMenuButton.BUTTON_TYPE_VIEW);
        wechatMenuButton2.setUrl("https://www.google.com/?gws_rd=ssl");
        List<WechatMenuButton> menuButtons = new ArrayList<>();
        menuButtons.add(wechatMenuButton1);
        menuButtons.add(wechatMenuButton2);
        wechatMenu.setButton(menuButtons);
        String baseAccessToken = wechatService.getBaseAccessToken();
        WechatResponse wechatResponse = wechatService.menuCreate(baseAccessToken, wechatMenu);
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void menuGetTest() {
        String baseAccessToken = wechatService.getBaseAccessToken();
        MenuGetResponse menuGetResponse = wechatService.menuGet(baseAccessToken);
        assertNotNull(menuGetResponse);
        logger.debug(menuGetResponse.toString());
    }

    @Test
    public void menuDeleteTest() {
        String baseAccessToken = wechatService.getBaseAccessToken();
        WechatResponse wechatResponse = wechatService.menuDelete(baseAccessToken);
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void customServiceAccountListTest() {
        String appid = "wx37df0c82bb2231e8";
        String accessToken = wechatComponentService.getAuthorizerAccessToken(appid);
        WechatResponse wechatResponse = wechatService.customServiceAccountList(accessToken);
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void addCustomServiceAccountTest() {
        String baseAccessToken = wechatService.getBaseAccessToken();
        WechatResponse wechatResponse = wechatService.addCustomServiceAccount(baseAccessToken, "test1@test", "客服1",
                "89920fbbeea6faba3172e90dcd073ba90bdc71e2b5b9c6b73c7bacfae27b5b034a8e7abe6802a8e6134a83bcb11e2b7c3425e47232385a0419f7fac457802d8e");
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void sendTextCustomServiceMessageTest() {
        String appid = "wx37df0c82bb2231e8";
        String accessToken = wechatComponentService.getAuthorizerAccessToken(appid);
        String touser = "oiXz7srouDpUhRPibIGafwO-78bI";
        CustomServiceTextMessage customServiceTextMessage = new CustomServiceTextMessage();
        customServiceTextMessage.setContent("客服消息测试");
        WechatResponse wechatResponse = wechatService.sendTextCustomServiceMesaage(accessToken, touser, customServiceTextMessage);
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void sendImageCustomServiceMessageTest() {
        String appid = "wx37df0c82bb2231e8";
        String accessToken = wechatComponentService.getAuthorizerAccessToken(appid);
        String touser = "oiXz7srouDpUhRPibIGafwO-78bI";
        CustomServiceImageMessage customServiceImageMessage = new CustomServiceImageMessage();
        customServiceImageMessage.setMedia_id("9X4zz-ufW_zljdb_FVzZns4kfHg1mUAlZIGAFg3BtGJWzFE3-pG5Z9utwTZW8C7Z");
        WechatResponse wechatResponse = wechatService.sendImageCustomServiceMesaage(accessToken, touser, customServiceImageMessage);
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void sendVoiceCustomServiceMessageTest() {
        String appid = "wx37df0c82bb2231e8";
        String accessToken = wechatComponentService.getAuthorizerAccessToken(appid);
        String touser = "oiXz7srouDpUhRPibIGafwO-78bI";
        CustomServiceVoiceMessage customServiceVoiceMessage = new CustomServiceVoiceMessage();
        customServiceVoiceMessage.setMedia_id("9X4zz-ufW_zljdb_FVzZns4kfHg1mUAlZIGAFg3BtGJWzFE3-pG5Z9utwTZW8C7Z");
        WechatResponse wechatResponse = wechatService.sendVoiceCustomServiceMesaage(accessToken, touser, customServiceVoiceMessage);
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void sendVideoCustomServiceMessageTest() {
        String appid = "wx37df0c82bb2231e8";
        String accessToken = wechatComponentService.getAuthorizerAccessToken(appid);
        String touser = "oiXz7srouDpUhRPibIGafwO-78bI";
        CustomServiceVideoMessage customServiceVideoMessage = new CustomServiceVideoMessage();
        customServiceVideoMessage.setTitle("测试视频");
        customServiceVideoMessage.setDescription("客服视频消息测试");
        customServiceVideoMessage.setMedia_id("9X4zz-ufW_zljdb_FVzZns4kfHg1mUAlZIGAFg3BtGJWzFE3-pG5Z9utwTZW8C7Z");
        customServiceVideoMessage.setThumb_media_id("9X4zz-ufW_zljdb_FVzZns4kfHg1mUAlZIGAFg3BtGJWzFE3-pG5Z9utwTZW8C7Z");
        WechatResponse wechatResponse = wechatService.sendVideoCustomServiceMesaage(accessToken, touser, customServiceVideoMessage);
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void sendMusicCustomServiceMessageTest() {
        String appid = "wx37df0c82bb2231e8";
        String accessToken = wechatComponentService.getAuthorizerAccessToken(appid);
        String touser = "oiXz7srouDpUhRPibIGafwO-78bI";
        CustomServiceMusicMessage customServiceMusicMessage = new CustomServiceMusicMessage();
        customServiceMusicMessage.setTitle("测试音乐");
        customServiceMusicMessage.setDescription("客服音乐消息测试");
        customServiceMusicMessage.setThumb_media_id("9X4zz-ufW_zljdb_FVzZns4kfHg1mUAlZIGAFg3BtGJWzFE3-pG5Z9utwTZW8C7Z");
        customServiceMusicMessage.setMusicurl("http://mksueditor.oss-cn-shanghai.aliyuncs.com/test.mp3");
        customServiceMusicMessage.setHqmusicurl("http://mksueditor.oss-cn-shanghai.aliyuncs.com/test.mp3");
        WechatResponse wechatResponse = wechatService.sendMusicCustomServiceMesaage(accessToken, touser, customServiceMusicMessage);
        assertNotNull(wechatResponse);
        logger.debug(wechatResponse.toString());
    }

    @Test
    public void userListTest() {
        String appid = "wx37df0c82bb2231e8";
        String accessToken = wechatComponentService.getAuthorizerAccessToken(appid);
        UserList userList = wechatService.getUserList(accessToken);
        assertNotNull(userList);
        logger.debug(userList.toString());
    }

    @Test
    public void getUserInfoUnionIDTest() {
        String baseAccessToken = wechatService.getBaseAccessToken();
        String openid = "oa0WcwmBjBsGn4B_-sfqRw0QLWAY";
        UserInfo userInfo = wechatService.getUserInfoUnionID(baseAccessToken, openid);
        assertNotNull(userInfo);
        logger.debug(userInfo.toString());
    }

    @Test
    public void getBatchUserInfoTest() {
        String appid = "wx37df0c82bb2231e8";
        String accessToken = wechatComponentService.getAuthorizerAccessToken(appid);
        String[] openids = {"oiXz7stS7UzDvxdZv_QfF0sWvv2I","oiXz7suoUqpdYa0bJ4hQ3Hi2Znbs","oiXz7st9_-oZI5x3A4ZE6ocnjUgI","oiXz7suaJP5BtlQI7zfy-C0PqHUY","oiXz7so_RK0UozydvEt50WN5LIsw","oiXz7sn6Xd-1eQi061K5XNNw9Wbc","oiXz7sjSv_x0i2kJL4HRwr0cNf0g","oiXz7suoRWnYmTemlI0y7nleiMf8","oiXz7smhmjkl6udH3iROctWQlweo","oiXz7stxDVlIM8OMUsSoypD3peQA","oiXz7shnykldBlK5lYMI3NzswsxE","oiXz7shRVYoJ8eRqx__pSeSt2DMo","oiXz7spQ869N0dTBntGBrbv48OAI","oiXz7svk8K4ju6jGMX8_A2kwq7KE","oiXz7smm97fvcwidZy_tcm1zNN6Q","oiXz7skwbZuwCjhf13eLUzQ8dTpM","oiXz7sgSV31ex52-cYd9_lewKUQc","oiXz7srouDpUhRPibIGafwO-78bI","oiXz7slMzC2xEnAGsqE-5uqXon_U","oiXz7sqp3zy27kZnSUwxI7xj4ajY","oiXz7sl4XvvqbIPH271HN3yFf2vk","oiXz7suZQhrSxxZIexML25RrT52U","oiXz7sgtU3eVEfDYqLqJOVZOGRJU","oiXz7spLiHJSG7XYaytXaFGQ7ydI","oiXz7stQ5GtuJ0ectQ6cRZpMsRFg","oiXz7snoIYiMdJr4cdsRddm9YWwQ","oiXz7ss6lqjWpmP7JOn9yQ711bTs","oiXz7shWPOSiXrWMVviIu73uKJDc","oiXz7slG8rDXlo5nbd-eaCEvu000","oiXz7sr8a2qSwARDiKuvouojlsKk","oiXz7ssH--ytuuhZ3a5zYOhyzq7o","oiXz7supBJ-FDxh-DavHJSI2bmbA","oiXz7sii1TmNEN72UEag7RcOtXZE","oiXz7sh66_m9kQlvGVQUXxn6hwfo","oiXz7stJ2OWk7F5pSGvdd-3Q137Y","oiXz7slAjZprjuFLglmmoKXeqv9I","oiXz7sv3QsAp2zwZy8kiTTPM5qps","oiXz7skjhbY8dsW338X6HYiZlzGQ","oiXz7sp8GDXmDq5UxQ2M4GDVf3QY","oiXz7slFLa_OG6VmtvQofW4hDAwE","oiXz7sigl37mOkicRzoxHe7sdmEI","oiXz7skfPUrqZzmRbSgAUFOJT0rg","oiXz7sgK6cawOcrIA1c6EvnSvt4k","oiXz7shWJVH1woTB_4B2uc1qHqpg","oiXz7sj7pX5ebMaDohtth4kH833Q","oiXz7sliMx_DsE8o9iQ7GikGOu4U","oiXz7suk1fmD0zC-t8dyRPOCfvtc","oiXz7skdTecysYxP-RdSmk2Ycc-k","oiXz7sq6AQGWuLW1-oiO8DsdaNhk","oiXz7sqd_ljzR_mfSl2XqRORsJns","oiXz7stkayu7fyAmPyaJCcl3JRko","oiXz7sjDM_hObsCJ_VP3xX6CxAzY","oiXz7soZU7EyYUa2ZXGz3w3ess30","oiXz7sqm9FfkDKfAt9wXQxuvDshw","oiXz7slhOP9ApyQEku9AGYhkPLmM","oiXz7shDJkwyIF-KJs5RTwZm96Ag","oiXz7ssfdIGmSKE9IwAz3xoIKIZQ","oiXz7sqYEFZonwuRFKZeFo54Y4-U","oiXz7sh-_akj_VhkjGMZQZDqT-Wo","oiXz7sn9N7I0O2MbO6jR_e56qi4o","oiXz7sqUG2faGP1VDc8fZ8n_sNLs","oiXz7svhGgfn8CdxK-sz0i4ZK2FM","oiXz7sgH-shIpg1lhdPAuKG4SGgU","oiXz7svryBv-BtxO5-xTWXZ8lYRc","oiXz7sra83SLeIJhcCC7zntra-rk","oiXz7siMoP-VPi4TGMAarFOQRMzw","oiXz7shQvr6ba4QAip7b_hLGb3L0","oiXz7svG6ckrvT_CGC6LwBicp9kw","oiXz7shMnfkGSjTcsbeMdesfWeJ4","oiXz7smGiHE8-lQKdz7mu5AdhYRM","oiXz7stz3v-Acq2dSeKd4vVBofas","oiXz7sib4Kb46VOqDzd8-56ZO8cE","oiXz7sqmYN4squ33EJqt85kvUuec","oiXz7snziVpP_R65FyVyh5uOSWjw","oiXz7sufDLFjcR0CFVI0szGPNl28","oiXz7siBQNokgUakpcgmI8QlKlec","oiXz7siP_1uEGRfG8orZyQFgln0c","oiXz7srzl8ppVS2Gnpm6sjLXGKPA","oiXz7svtl7QdBE7QgDdKddEz4GB8","oiXz7sqQyX2zcEjeTzdcyihZMkSU","oiXz7slOP8St9F4O-XCq2y5Vgw9k","oiXz7ss8Weqrd3dBZEVzdYwZXIZA","oiXz7ssaoV2q1YsFt4EUrdGgJdhw","oiXz7suve8AVtv95VxpNnDCaJdqc","oiXz7ss7yP8kbzAGkh1WegiBDsos","oiXz7sl0AqLelrZoAD_m5NvDX7H4","oiXz7sltwO4gSIi04ztYXY9rndO8","oiXz7shuhjmbshBpcWdsOBHrUOh8","oiXz7slQWD_HCEH_8ROK-GBwi2I8","oiXz7snQ98zcmJx5xB2oyI03cyD0","oiXz7smME45zQrdqGV_VKkC8j1u8","oiXz7sszr3R6N3WeLefE1Lt90FYA","oiXz7sovhsyo4i-MNXK99PHNKK5g","oiXz7sshAUjvXpw5ZmHD2w9-21BU","oiXz7smzReYCoy-klkPTCfIsj1Xs","oiXz7sth5DNjM0_82yG_HXPNk_1g","oiXz7snitroqZ6OMcoN6ghpFSBo8","oiXz7smOwMuS-z0krpNbtYQTW7Fk","oiXz7soevSPWjyvcDlXypO1RqpoM","oiXz7sr98Qv7DyLLmS7Oz6vQD0z0"};
        List<String> list = Arrays.asList(openids);
        List<UserInfo> userInfos = wechatService.getBatchUserInfo(accessToken, list);
        assertNotNull(userInfos);
        logger.debug(userInfos.toString());
    }

    @Test
    public void createQRCodeTest() {
        String baseAccessToken = wechatService.getBaseAccessToken();
        CreateQRCodeResponse createQRCodeResponse = wechatService.createQRCode(baseAccessToken, CreateQRCodeRequest.ACTION_NAME_QR_SCENE, 123456);
        assertNotNull(createQRCodeResponse);
        logger.debug(createQRCodeResponse.toString());
    }

    @Test
    public void showQRCodeTest() {
        String ticket = "gQEY8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQmhaRGx3SDVlczIxQUh5aWhxMUEAAgQr1WpaAwQAjScA";
        String qrCodeUrl = wechatService.showQRCode(ticket);
        assertNotNull(qrCodeUrl);
        logger.debug(qrCodeUrl);
    }

    @Test
    public void long2shortTest() {
        String baseAccessToken = wechatService.getBaseAccessToken();
        String longUrl = "https://headline.taobao.com/feed/feedDetail.htm?spm=a21aq.7719463.1998565035.1.4056b599HHA64&id=200525630735&columnId=0";
        Long2ShortResponse long2ShortResponse = wechatService.long2short(baseAccessToken, longUrl);
        assertNotNull(long2ShortResponse);
        logger.debug(long2ShortResponse.toString());
    }
}
