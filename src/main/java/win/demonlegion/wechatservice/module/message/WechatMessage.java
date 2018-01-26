package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 微信消息
 */
public class WechatMessage implements Serializable {
    private static final long serialVersionUID = 7124823687603528425L;

    public static final String MSG_TYPE_EVENT = "event";
    public static final String MSG_TYPE_TEXT = "text";
    public static final String MSG_TYPE_IMAGE = "image";
    public static final String MSG_TYPE_VOICE = "voice";
    public static final String MSG_TYPE_VIDEO = "video";
    public static final String MSG_TYPE_SHORT_VIDEO = "shortvideo";
    public static final String MSG_TYPE_MUSIC = "music";
    public static final String MSG_TYPE_NEWS = "news";
    public static final String MSG_TYPE_LOCATION = "location";
    public static final String MSG_TYPE_LINK = "link";
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    public static final String EVENT_TYPE_CLICK = "CLICK";
    // 全网接入测试
    public static final String COMPONENT_INSERT_TEST_STEP_TWO = "TESTCOMPONENT_MSG_TYPE_TEXT";
    public static final String COMPONENT_INSERT_TEST_STEP_THREE = "QUERY_AUTH_CODE:";


    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;

    @JSONField(name = "ToUserName")
    public String getToUserName() {
        return ToUserName;
    }

    @JSONField(name = "FromUserName")
    public String getFromUserName() {
        return FromUserName;
    }

    @JSONField(name = "CreateTime")
    public String getCreateTime() {
        return CreateTime;
    }

    @JSONField(name = "MsgType")
    public String getMsgType() {
        return MsgType;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
