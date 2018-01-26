package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 地理位置消息
 */
public class LocationMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = -5605282880613362508L;

    private String Location_X;
    private String Location_Y;
    private String Scale;
    private String Label;
    private String MsgId;

    @JSONField(name = "Location_X")
    public String getLocation_X() {
        return Location_X;
    }

    @JSONField(name = "Location_Y")
    public String getLocation_Y() {
        return Location_Y;
    }

    @JSONField(name = "Scale")
    public String getScale() {
        return Scale;
    }

    @JSONField(name = "Label")
    public String getLabel() {
        return Label;
    }

    @JSONField(name = "MsgId")
    public String getMsgId() {
        return MsgId;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
