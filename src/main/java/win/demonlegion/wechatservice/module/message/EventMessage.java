package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 事件消息
 */
public class EventMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = 8707779559033251155L;

    private String Event;
    // 自定义菜单事件字段
    private String EventKey;
    // 位置事件字段
    private String Latitude;
    private String Longitude;
    private String Precision;

    @JSONField(name = "Event")
    public String getEvent() {
        return Event;
    }

    @JSONField(name = "EventKey")
    public String getEventKey() {
        return EventKey;
    }

    @JSONField(name = "Latitude")
    public String getLatitude() {
        return Latitude;
    }

    @JSONField(name = "Longitude")
    public String getLongitude() {
        return Longitude;
    }

    @JSONField(name = "Precision")
    public String getPrecision() {
        return Precision;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}
