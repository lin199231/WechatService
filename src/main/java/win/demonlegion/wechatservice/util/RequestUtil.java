package win.demonlegion.wechatservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 请求工具类, 通过RequestBean产生特定的访问
 */
public class RequestUtil implements Serializable {
    private static final long serialVersionUID = 5725890420589401321L;
    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    public static String getUrl(String url, Object bean) {
        return getUrl(url, bean, null, bean.getClass());
    }

    public static String getUrl(String url, Object bean, String suffix) {
        return getUrl(url, bean, suffix, bean.getClass());
    }

    public static <T> String getUrl(String url, Object bean, Class<T> clazz) {
        return getUrl(url, bean, null, clazz);
    }

    public static <T> String getUrl(String url, Object bean, String suffix, Class<T> clazz) {
        return getUrl(url, bean, null, suffix, clazz);
    }

    public static <T> String getUrl(String url, Object bean, List<String> keyList, String suffix, Class<T> clazz) {
        if(url != null) {
            if(bean != null) {
                url += "?" + getParameters(bean, true, keyList, clazz);
                // 增加url的后缀
                if(suffix != null) url += suffix;
                logger.debug(url);
                return url;
            }
        }
        return null;
    }

    public static String getParameters(Object bean, boolean isEscape) {
        return getParameters(bean, isEscape, bean.getClass());
    }

    public static <T> String getParameters(Object bean, boolean isEscape, Class<T> clazz) {
        return getParameters(bean, isEscape, null, clazz);
    }

    public static <T> String getParameters(Object bean, boolean isEscape, List<String> keyList, Class<T> clazz) {
        String parameters = "";
        if(bean != null) {
            Map<String, Object> params = BeanMapUtil.beanToMap(bean, clazz);
            if(keyList == null) {
                keyList = params.keySet().stream().sorted().collect(Collectors.toList());
            }
            try {
                for(String key : keyList) {
                    if(params.get(key) != null) {
                        parameters += key + "="
                                + (isEscape ? URLEncoder.encode(params.get(key).toString(), "UTF-8") : params.get(key).toString())
                                + "&";
                    }
                }
            } catch (UnsupportedEncodingException e) {
                logger.debug(e.getMessage());
                e.printStackTrace();
            }
            // 删除最后的&符号
            if(parameters.length() > 0) parameters = parameters.substring(0, parameters.length() - 1);
        }

        logger.debug(parameters);
        return parameters;
    }

    // Reads an InputStream and converts it to a String.

    public static String readString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        // 写入内容到stringBuffer
        while ((temp = br.readLine()) != null) {
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }
}
