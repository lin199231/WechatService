package win.demonlegion.wechatservice.util;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 后台生成随机数工具类
 * Created by john on 2017/7/4.
 */
public class RandomUtil implements Serializable {
    private static final long serialVersionUID = -9142787188821218444L;

    private static final Random RANDOM = new SecureRandom();

    public static int getRandomInteger(int range) {
        return RandomUtils.nextInt(range);
    }

    public static String getRandomCode(int size) {
        return RandomStringUtils.randomNumeric(size);
    }

    public static int getRandomDelay(int range, int min) {
        return min + RandomUtils.nextInt(range);
    }

    public static String getRandomString(int size) {
        return RandomStringUtils.randomAlphanumeric(size);
    }

    public static BigInteger getRandomPrime(int bitLength) {
        return BigInteger.probablePrime(bitLength, RANDOM);
    }

    public static BigInteger getRandomBigInteger(int bitLength) {
        return new BigInteger(bitLength, RANDOM);
    }

    /**
     * 通过参数的原始列表以及随机列表大小返回相应的随机列表
     * @param list 未进行随机的列表
     * @param randomListSize 返回需要的随机列表大小, 如果与传入的列表大小一致则返回数据随机化的列表
     * @return
     */
    public static <T> List<T> getRandomList(List<T> list, int randomListSize) {
        // 通过列表大小取随机整数, 将随机整数位置的原列表数据加入返回列表中并删除, 循环这个过程实现
        List<T> resultList = new ArrayList<>();
        // randomListSize如果大于原列表大小则使用列表大小
        if(randomListSize > list.size()) randomListSize = list.size();
        for(int i=0;i<randomListSize;i++) {
            int randomListIndex = getRandomInteger(list.size());
            resultList.add(list.get(randomListIndex));
            list.remove(randomListIndex);
        }
        return resultList;
    }
}
