package win.demonlegion.wechatservice.service;

import java.util.List;
import java.util.Set;

/**
 * @Author:Samson
 * @Description:
 * @Date:Created in 2017/6/2.
 */
public interface CacheService {
    CacheService selectDB(int index);
    /**
     * 判断是否存在Key
     * @param key
     * @return boolean
     */
    boolean hasKey(String key);
    /**
     * 存放长期保存的缓存数据
     * @param key
     * @param cache
     */
    void saveCache(String key, String cache);

    /**
     * 存放登录令牌缓存
     * @param key
     * @param token
     */
    void saveToken(String key, String token, long expire);

    /**
     * 存放登录令牌缓存
     * @param key
     * @param token
     */
    void saveToken(String key, String token);

    /**
     * 存放临时授权令牌缓存
     * @param key
     * @param token
     */
    void saveTempToken(String key, String token);

    /**
     *
     * @param key
     * @param vaCode
     */
    void saveTempVaCode(String key, String vaCode);

    /**
     * 获取用户缓存
     */
    String selectCache(String key);

    /**
     * 删除用户缓存
     */
    void  deleteCache(String key);


    void saveVideoNumCode(String key, int vaCode);

    /**
     * 获取用户缓存
     */
    int selectVideoNum(String videoNum);

    // 集合操作
    /**
     * 添加集合值
     */
    void addSetValue(String key, String value);

    /**
     * 添加多集合值
     */
    void addSetValues(String key, String[] values);

    /**
     * 删除集合值
     */
    void deleteSetValue(String key, String value);

    /**
     * 删除多集合值
     */
    void deleteSetValues(String key, String[] values);

    /**
     * 查询集合值
     */
    Set<String> selectSetValues(String key);

    /**
     * 判断集合值是否存在
     */
    boolean isInSet(String key, String value);

    /**
     * 查询集合大小
     */
    long countSetSize(String key);

    // 列表操作
    List<String> getSubList(String key, long start, long end);

    List<String> getList(String key);

    long leftPushValue(String key, String value);

    long leftPushValues(String key, List<String> values);

    long rightPushValue(String key, String value);

    long rightPushValues(String key, List<String> values);

    void setValue(String key, long index, String value);

    long removeAllValue(String key, String value);

    long removeValueFromLeft(String key, String value);

    long removeValueFromRight(String key, String value);

    long countListSize(String key);
}
