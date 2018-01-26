package win.demonlegion.wechatservice.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import win.demonlegion.wechatservice.common.HttpCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2017/6/16.
 * <p>用于后台结果输出</p>
 */
public class ResultUtil implements Serializable {
    private static final long serialVersionUID = -4999418960335879379L;

    public static String getNotNullResult(Object object) {
        return JSON.toJSONString(object);
    }

    public static String getNotNullResult(HttpCode code) {
        return getNotNullResult(code, null);
    }

    public static String getNotNullResult(String name, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(name, object);
        return JSON.toJSONString(map);
    }

    public static String getNotNullResult(HttpCode code, Object object) {
        if(object instanceof Map) {
            Map<String, Object> map = (Map) object;
            if(code != null) {
                map.put("code", code.getValue());
                map.put("message", code.getMessage());
            }
            return JSON.toJSONString(map);
        } else return getNotNullResult(code, "obj", object);
    }

    public static String getNotNullResult(HttpCode code, String name, Object object) {
        Map<String, Object> map = new HashMap<>();
        if(code != null) {
            map.put("code", code.getValue());
            map.put("message", code.getMessage());
        }
        if(object != null) map.put(name, object);
        return JSON.toJSONString(map);
    }

    public static String getResult(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }

    public static String getResult(HttpCode code) {
        return getResult(code, null);
    }

    public static String getResult(String name, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(name, object);
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    public static String getResult(HttpCode code, Object object) {
        if(object instanceof Map) {
            Map<String, Object> map = (Map) object;
            if(code != null) {
                map.put("code", code.getValue());
                map.put("message", code.getMessage());
            }
            return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        } else return getResult(code, "obj", object);
    }

    public static String getResult(HttpCode code, String name, Object object) {
        Map<String, Object> map = new HashMap<>();
        if(code != null) {
            map.put("code", code.getValue());
            map.put("message", code.getMessage());
        }
        if(object != null) map.put(name, object);
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }
}
