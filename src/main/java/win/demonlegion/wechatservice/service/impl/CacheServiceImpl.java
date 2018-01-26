package win.demonlegion.wechatservice.service.impl;

import win.demonlegion.wechatservice.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Samson
 * @Description:
 * @Date:Created in 2017/6/2.
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private JedisConnectionFactory redisConnectionFactory;

    @Override
    public CacheService selectDB(int index) {
        redisConnectionFactory.setDatabase(index);
        return this;
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void saveCache(String key, String cache) {
        redisTemplate.opsForValue().set(key, cache);
    }

    @Override
    public void saveToken(String key, String token, long expire) {
        redisTemplate.opsForValue().set(key, token);
        // 登录令牌过期时间为1天
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void saveToken(String key, String token) {
        redisTemplate.opsForValue().set(key, token);
        // 登录令牌过期时间为1天
        redisTemplate.expire(key, 24, TimeUnit.HOURS);
    }

    @Override
    public void saveTempToken(String key, String token) {
        redisTemplate.opsForValue().set(key, token);
        // 临时令牌过期时间为1笑死
        redisTemplate.expire(key, 1, TimeUnit.HOURS);
    }

    @Override
    public void saveTempVaCode(String key, String code) {
        redisTemplate.opsForValue().set(key, code);
        // 临时令牌过期时间为1笑死
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);
    }

    @Override
    public String selectCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteCache(String userId) {
        redisTemplate.delete(userId);
    }

    @Override
    public void saveVideoNumCode(String key, int vaCode) {
        String num = String.valueOf(vaCode);
        redisTemplate.opsForValue().set(key, num);
    }

    @Override
    public int selectVideoNum(String videoNum) {
        return Integer.parseInt(redisTemplate.opsForValue().get(videoNum));
    }

    @Override
    public void addSetValue(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public void addSetValues(String key, String[] values) {
        redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public void deleteSetValue(String key, String value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    public void deleteSetValues(String key, String[] values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public Set<String> selectSetValues(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public boolean isInSet(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public long countSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public List<String> getSubList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public List<String> getList(String key) {
        return getSubList(key, 0, -1);
    }

    @Override
    public long leftPushValue(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public long leftPushValues(String key, List<String> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public long rightPushValue(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public long rightPushValues(String key, List<String> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public void setValue(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public long removeAllValue(String key, String value) {
        return redisTemplate.opsForList().remove(key, 0, value);
    }

    @Override
    public long removeValueFromLeft(String key, String value) {
        // 从左到右删除第一个符合的值
        return redisTemplate.opsForList().remove(key, 1, value);
    }

    @Override
    public long removeValueFromRight(String key, String value) {
        // 从右到左删除第一个符合的值
        return redisTemplate.opsForList().remove(key, -1, value);
    }

    @Override
    public long countListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }
}
