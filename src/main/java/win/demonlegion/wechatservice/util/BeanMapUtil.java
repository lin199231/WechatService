package win.demonlegion.wechatservice.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BeanMapUtil implements Serializable {
    private static final long serialVersionUID = 7503358188252393158L;
    private static final Logger logger = LoggerFactory.getLogger(BeanMapUtil.class);

    //将javabean实体类转为map类型，然后返回一个map类型的值
    public static Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> params = new HashMap<>();
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(bean, name));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return params;
    }

    //将javabean实体类转为map类型，然后返回一个map类型的值
    public static <T> Map<String, Object> beanToMap(Object bean, Class<T> clazz) {
        Map<String, Object> params = new HashMap<>();
        T t = null;
        try {
            t = clazz.newInstance();
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(t);
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(bean, name));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return params;
    }

    //通过fastjson解决javabean属性首字母大写的问题
    public static Map<String, Object> beanToMapByJSON(Object bean) {
        Map<String, Object> params = new HashMap<>();
        try {
            String jsonString = JSON.toJSONString(bean);
            logger.debug(jsonString);
            params = JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>(){});
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return params;
    }

    // map类型转为javabean实体类, 并返回
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T t = null;
        if(map != null) {
            try {
                t = clazz.newInstance();
                PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
                PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(t);
                for (PropertyDescriptor descriptor : descriptors) {
                    String name = descriptor.getName();
                    if (!"class".equals(name)) {
                        Object value = map.get(name);
                        if (value != null) {
                            BeanUtils.setProperty(t, name, value);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return t;
    }

    //通过fastjson解决javabean属性首字母大写的问题
    public static <T> T mapToBeanByJSON(Map<String, Object> map, Class<T> clazz) {
        T t = null;
        if(map != null) {
            try {
                t = clazz.newInstance();
                // 先通过fastjson将map转为JSONObject, 在从JSONObject中取出属性赋值javabean
                JSONObject jsonObject = new JSONObject(map);
                t = JSON.toJavaObject(jsonObject, clazz);
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return t;
    }
}
