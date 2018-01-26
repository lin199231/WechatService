package win.demonlegion.wechatservice.response.wechat;

import win.demonlegion.wechatservice.module.UserInfo;
import win.demonlegion.wechatservice.response.WechatResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 获取用户详情返回
 */
public class UserInfoResponse extends WechatResponse implements Serializable {
    private static final long serialVersionUID = -6822608459032558973L;

    private int subcribe;
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String language;
    private String headimgurl;
    private String privilege;
    private String unionid;
    private long subscribe_time;
    private String remark;
    private int groupId;
    private List<Integer> tagid_list;

    public int getSubcribe() {
        return subcribe;
    }

    public void setSubcribe(int subcribe) {
        this.subcribe = subcribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = UserInfo.SEX_DETAIL[sex];
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public long getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(List<Integer> tagid_list) {
        this.tagid_list = tagid_list;
    }
}
