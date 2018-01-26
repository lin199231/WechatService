package win.demonlegion.wechatservice.request.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 批量用户详情请求
 */
public class BatchUserInfoRequest extends WechatRequest implements Serializable {
    private static final long serialVersionUID = 6672468982728178702L;

    private static final String KEY_OPENID = "openid";
    private static final String KEY_LANG = "lang";

    private JSONArray user_list;

    public BatchUserInfoRequest() {
        user_list = new JSONArray();
    }

    public JSONArray getUser_list() {
        return user_list;
    }

    public void setUser_list(JSONArray user_list) {
        this.user_list = user_list;
    }

    public void setUser_list(List<String> userList, String language) {
        for(String openid : userList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(KEY_OPENID, openid);
            jsonObject.put(KEY_LANG, language);
            user_list.add(jsonObject);
        }
    }
}
