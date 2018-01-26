package win.demonlegion.wechatservice.module.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 回复音乐消息
 */
public class ReplyMusicMessage extends WechatMessage implements Serializable {
    private static final long serialVersionUID = -7648048973299188509L;

    private String MediaId;
    private String Title;
    private String Description;
    private String MusicURL;
    private String HQMusicUrl;
    private String ThumbMediaId;

    public ReplyMusicMessage() {
        setMsgType(WechatMessage.MSG_TYPE_MUSIC);
        setCreateTime((System.currentTimeMillis() / 1000) + "");
    }

    @JSONField(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    @JSONField(name = "Title")
    public String getTitle() {
        return Title;
    }

    @JSONField(name = "Description")
    public String getDescription() {
        return Description;
    }

    @JSONField(name = "MusicURL")
    public String getMusicURL() {
        return MusicURL;
    }

    @JSONField(name = "HQMusicUrl")
    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    @JSONField(name = "ThumbMediaId")
    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setMusicURL(String musicURL) {
        MusicURL = musicURL;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
