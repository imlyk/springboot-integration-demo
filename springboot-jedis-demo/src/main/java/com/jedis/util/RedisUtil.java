/*
package com.jedis.util;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

*/
/**
 * @author lequal
 * @date 2022/11/05 22:15
 * @Description 通用Redis工具类，部分功能依赖于hutool-all依赖，需要请自行导入
 *//*

@Slf4j
@Component
public class RedisUtil {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private ExecutorService CACHE_REBUILD_EXECUTOR = null;

    private ExecutorService threadPoolInit() {
        if (null == CACHE_REBUILD_EXECUTOR) {
            CACHE_REBUILD_EXECUTOR = new ThreadPoolExecutor(10, 10,
                    0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        }
        return CACHE_REBUILD_EXECUTOR;
    }

    */
/**
     * 使用ObjectMapper写也可以自行修改
     *//*

    private final ObjectMapper objectMapper = new ObjectMapper();

    */
/**
     * @Author lequal
     * @Description 加互斥锁
     * @Date 2022/11/5 22:16
     * @param key
     * @return java.lang.Boolean
     **//*

    public boolean tryLock(String key) {
        // 不存在就设置，设置成功返回true，这样子就保证成功设置值的第一个线程才可以执行之后的操作
        Boolean isLock = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", RedisConstants.LOCK_SHOP_TTL, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(isLock);
    }

    */
/**
     * @Author lequal
     * @Description 释放锁
     * @Date 2022/11/5 22:16
     * @param key
     **//*

    public void unLock(String key) {
        stringRedisTemplate.delete(key);
    }

    */
/**
     * @Author: lequal
     * @Description 校验key
     * @Date 2022/11/11 17:45
     * @param key
     *//*

    private void validateKey(String key)  {
        if (StrUtil.isEmpty(key)) {
            throw new RuntimeException("Invalid key...");
        }
    }

    */
/** ============================ 通用方法 ============================ **//*


    */
/**
     * @Author: lequal
     * @Description 删除指定key的内容
     * @Date 2022/11/11 11:57
     * @param key
     * @return boolean
     *//*

    public boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }


    */
/**
     * @Author: lequal
     * @Description 批量删除key，返回删除的个数
     * @Date 2022/11/11 12:01
     * @param keys key的集合
     * @return java.lang.Long
     *//*

    public long deleteBatch(List<String> keys) {
        return stringRedisTemplate.delete(keys);
    }

    */
/**
     * @Author: lequal
     * @Description 批量删除key，返回删除的个数
     * @Date 2022/11/11 12:06
     * @param keys
     * @return long
     *//*

    public long deleteBatch(String... keys) {
        List<String> keyList = Arrays.asList(keys);
        return stringRedisTemplate.delete(keyList);
    }

    */
/**
     * @Author: lequal
     * @Description 指定key过期时间，默认时间单位为秒(s)
     * @Date 2022/11/11 11:56
     * @param key
     * @param time 过期时长
     * @return boolean
     *//*

    public boolean expire(String key, Long time) {
        return stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    */
/**
     * @Author: lequal
     * @Description 指定key的过期时间
     * @Date 2022/11/11 11:53
     * @param key
     * @param time 有效期
     * @param timeUnit 时间单位
     * @return boolean
     *//*

    public boolean expire(String key, Long time, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(key, time, timeUnit);
    }

    */
/**
     * @Author: lequal
     * @Description 获取key的有效期
     * @Date 2022/11/11 12:23
     * @param key
     * @return long
     *//*

    public long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    */
/**
     * @Author: lequal
     * @Description 获取key的有效期
     * @Date 2022/11/11 12:24
     * @param key
     * @param timeUnit 自定义时间单位
     * @return long
     *//*

    public long getExpire(String key, TimeUnit timeUnit) {
        return stringRedisTemplate.getExpire(key, timeUnit);
    }

    */
/**
     * @Author: lequal
     * @Description 判断是否存在指定key，存在返回true
     * @Date 2022/11/11 12:31
     * @param key
     * @return boolean
     *//*

    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    */
/** ============================ 通用方法 ============================ **//*



    */
/** ============================ String操作 ============================ **//*


    */
/**
     * @Author lequal
     * @Description 设置键值，默认过期单位是秒(s)
     * @Date 2022/11/13 22:17
     * @param key
     * @param value
     * @param expire
     **//*

    public void set(String key, Object value, Long expire) {
        set(key, value, expire, TimeUnit.SECONDS);
    }

    */
/**
     * @Author: lequal
     * @Description key-value序列化成String存储
     * @Date 2022/11/11 11:46
     * @param key 键
     * @param value 值
     * @param expire 过期时间
     * @param timeUnit 时间单位
     *//*

    public void set(String key, Object value, Long expire, TimeUnit timeUnit) {
        String jsonStr = JSONUtil.toJsonStr(value);
        stringRedisTemplate.opsForValue().set(key, jsonStr, expire, timeUnit);
    }

    */
/**
     * @Author: lequal
     * @Description 根据key返回json字符串
     * @Date 2022/11/11 17:42
     * @param key
     * @return java.lang.String
     *//*

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    */
/**
     * @Author: lequal
     * @Description 根据key返回指定类型的结果
     * @Date 2022/11/11 17:41
     * @param key
     * @param clazz
     * @return T
     *//*

    public <T> T get(String key, Class<T> clazz) {
        String jsonStr = stringRedisTemplate.opsForValue().get(key);
        return StrUtil.isEmpty(jsonStr) ? null : JSONUtil.toBean(jsonStr, clazz);
    }

    */
/**
     * @Author: lequal
     * @Description 设置逻辑过期
     * @Date 2022/11/11 11:47
     * @param key 键
     * @param value 值
     * @param expire 过期时间
     * @param timeUnit 时间单位
     *//*

    public void setWithLogical(String key, Object value, Long expire, TimeUnit timeUnit) {
        // 依赖于RedisLogicalData对象
        RedisLogicalData redisLogicalData = new RedisLogicalData(LocalDateTime.now().plusSeconds(timeUnit.toSeconds(expire)), value);

        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisLogicalData));
    }

    */
/**
     * @Author: lequal
     * @Description 获取key对应的内容，防止缓存穿透
     * 命中缓存就直接返回，命中的数据不正确就返回null；
     * 否则就查库，查库查不到就缓存空值，查到就重建缓存返回数据
     * @Date 2022/11/11 14:45
     * @param keyPrefix key的前缀
     * @param id 数据的id
     * @param clazz 返回的类型
     * @param dbCallback 查库的回调逻辑
     * @param expire 重建的缓存的有效期
     * @param timeUnit 重建的缓存的有效期单位
     * @return T
     *//*

    public <T, ID> T getWithThrough(String keyPrefix, ID id, Class<T> clazz, Function<ID, T> dbCallback, Long expire, TimeUnit timeUnit) {
        final String key = keyPrefix + id;
        String jsonString = stringRedisTemplate.opsForValue().get(key);

        if (StrUtil.isNotBlank(jsonString)) {
            // 有内容，返回
            return JSONUtil.toBean(jsonString, clazz);
        }

        if (null != jsonString) {
            // 可能为空串时返回null
            return null;
        }

        T dbResult = dbCallback.apply(id);

        if (Objects.isNull(dbResult)) {
            // 写空值
            set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
        } else {
            // 重建缓存
            String value = JSONUtil.toJsonStr(dbResult);
            set(key, value, expire, timeUnit);
        }

        return dbResult;
    }

    */
/**
     * @Author: lequal
     * @Description 使用逻辑过期方式防止缓存击穿
     * @Date 2022/11/11 17:15
     * @param keyPrefix
     * @param id
     * @param clazz 返回的类型
     * @param dbCallback 查库的回调逻辑
     * @param expire 重建的缓存的有效期
     * @param timeUnit 重建的缓存的有效期单位
     * @return T
     *//*

    public <T,ID> T getWithLogicalExpire(String keyPrefix, ID id, Class<T> clazz, Function<ID, T> dbCallback, Long expire, TimeUnit timeUnit) {
        // 1.查询缓存是否存在
        final String key = keyPrefix + id;
        String jsonString = stringRedisTemplate.opsForValue().get(key);
        // 2.未命中返回空
        if (StrUtil.isEmpty(jsonString)) {
            return null;
        }

        // 3.命中缓存则判断逻辑过期时间是否过期
        RedisLogicalData redisData = JSONUtil.toBean(jsonString, RedisLogicalData.class);
        boolean after = redisData.getExpireTime().isAfter(LocalDateTime.now());
        JSONObject data = (JSONObject) redisData.getData();
        T result = JSONUtil.toBean(data, clazz);
        if (after) {
            // 未过期直接返回
            return result;
        }

        // 4.过期就重建缓存
        // 4.1 获取互斥锁
        final String lockKey = RedisConstants.LOCK_SHOP_KEY + id;
        boolean lock = tryLock(lockKey);
        if (lock) {
            // 初始化线程池
            threadPoolInit();
            // 获取到互斥锁后开启独立线程重建缓存
            // 注意：获取互斥锁之后应该再次检测redis中的缓存是否过期。未过期则无需重建缓存
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    // 先查数据库
                    T  dbResult = dbCallback.apply(id);
                    // 写入redis
                    set(key, JSONUtil.toJsonStr(dbResult), expire, timeUnit);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    unLock(lockKey);
                }
            });
        }

        // 先返回旧数据
        return result;
    }



    */
/** ============================ String操作 ============================ **//*






    */
/** ============================ Hash操作 ============================ **//*


    */
/**
     * @Author lequal
     * @Description 判断hash中是否有某项
     * @Date 2022/11/13 23:02
     * @param key
     * @param item
     * @return boolean
     **//*

    public boolean hHasItem(String key, String item) {
        return stringRedisTemplate.opsForHash().hasKey(key, item);
    }

    */
/**
     * @Author lequal
     * @Description 一次存多个键值对到key中
     * @Date 2022/11/13 22:28
     * @param key
     * @param values
     **//*

    public void hSet(String key, Map<String, Object> values) {
        stringRedisTemplate.opsForHash().putAll(key, values);
    }

    */
/**
     * @Author lequal
     * @Description 一次存多个键值对到key中,并设置过期时间，默认是秒
     * @Date 2022/11/13 22:29
     * @param key
     * @param values
     * @param expire
     **//*

    public void hSet(String key, Map<String, Object> values, long expire) {
        if (expire <= 0) {
            throw new RuntimeException("key设定的过期时间无效!");
        }
        stringRedisTemplate.opsForHash().putAll(key, values);
        expire(key, expire);
    }

    */
/**
     * @Author lequal
     * @Description 设置hash中某一项的值，存在则覆盖
     * @Date 2022/11/13 22:56
     * @param key
     * @param item
     * @param value
     **//*

    public void hItemSet(String key, String item, String value) {
        stringRedisTemplate.opsForHash().put(key, item,value);
    }

    */
/**
     * @Author lequal
     * @Description 获取hash中某项对应的值
     * @Date 2022/11/13 22:35
     * @param key
     * @param hk
     * @return java.lang.Object
     **//*

    public Object hGet(String key, String hk) {
        return stringRedisTemplate.opsForHash().get(key, hk);
    }

    */
/**
     * @Author lequal
     * @Description 获取hash中多个项对应的值
     * @Date 2022/11/13 23:14
     * @param key
     * @param items
     * @return java.util.List<java.lang.Object>
     **//*

    public List<Object> hMultiGet(String key, List<Object> items) {
        return stringRedisTemplate.opsForHash().multiGet(key, items);
    }

    */
/**
     * @Author lequal
     * @Description 获取key对应的全部键值对
     * @Date 2022/11/13 22:34
     * @param key
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     **//*

    public Map<Object, Object> hGetAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    */
/**
     * @Author lequal
     * @Description 删除hash中指定项
     * @Date 2022/11/13 23:01
     * @param key
     * @param items
     **//*

    public void hDelete(String key, String... items) {
        stringRedisTemplate.opsForHash().delete(key, items);
    }


    */
/** ============================ Hash操作 ============================ **//*






    */
/** ============================ List操作 ============================ **//*



    */
/**
     * @Author lequal
     * @Description 获取list的size
     * @Date 2022/11/13 23:57
     * @param key
     * @return long
     **//*

    public long lGetSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    */
/**
     * @Author lequal
     * @Description 获取list中指定索引的内容
     * @Date 2022/11/13 23:38
     * @param key
     * @param index
     * @return java.lang.String
     **//*

    public String lGet(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    */
/**
     * @Author lequal
     * @Description 获取list中指定范围的内容
     * @Date 2022/11/13 23:38
     * @param key
     * @param start
     * @param end
     * @return java.util.List<java.lang.String>
     **//*

    public List<String> lGetRange(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    */
/**
     * @Author lequal
     * @Description 左边弹出list一个元素
     * @Date 2022/11/13 23:42
     * @param key
     * @return java.lang.String
     **//*

    public String lLeftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    */
/**
     * @Author lequal
     * @Description 右边弹出list一个元素
     * @Date 2022/11/13 23:42
     * @param key
     * @return java.lang.String
     **//*

    public String lRightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    */
/**
     * @Author lequal
     * @Description 更新key中index对应的值
     * @Date 2022/11/14 0:01
     * @param key
     * @param index
     * @param value
     **//*

    public void lUpdateIndex(String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    */
/**
     * @Author lequal
     * @Description list左边放入元素
     * @Date 2022/11/13 23:45
     * @param key
     * @param value
     **//*

    public void lLeftPush(String key, String value) {
        stringRedisTemplate.opsForList().leftPush(key, value);
    }

    */
/**
     * @Author lequal
     * @Description list中值为v的左边放入元素
     * 有多个v值时，从左边开始查到第一个v值即可（也就是最后插入的v值，靠左的v值），然后在v值左边插入新值v1
     * 不存在v时，不插入新值v1
     * @Date 2022/11/13 23:51
     * @param key
     * @param v
     * @param value
     **//*

    public void lLeftPush(String key, String v, String value) {
        stringRedisTemplate.opsForList().leftPush(key, v, value);
    }

    */
/**
     * @Author lequal
     * @Description list右边放入元素
     * @Date 2022/11/13 23:49
     * @param key
     * @param value
     **//*

    public void lRightPush(String key, String value) {
        stringRedisTemplate.opsForList().rightPush(key, value);
    }

    */
/**
     * @Author lequal
     * @Description list中值为v的右边放入元素
     * @Date 2022/11/13 23:53
     * @param key
     * @param v
     * @param value
     **//*

    public void lRightPush(String key, String v, String value) {
        stringRedisTemplate.opsForList().rightPush(key, v, value);
    }

    */
/**
     * @Author lequal
     * @Description 左边批量插入
     * @Date 2022/11/13 23:55
     * @param key
     * @param value
     **//*

    public void lLeftPushAll(String key, String... value) {
        List<String> list = Arrays.asList(value);
        stringRedisTemplate.opsForList().leftPushAll(key, list);
    }

    */
/**
     * @Author lequal
     * @Description 左边批量插入
     * @Date 2022/11/13 23:59
     * @param key
     * @param value
     **//*

    public void lLeftPushAll(String key, List<String> value) {
        stringRedisTemplate.opsForList().leftPushAll(key, value);
    }

    */
/**
     * @Author lequal
     * @Description 右边批量插入
     * @Date 2022/11/13 23:56
     * @param key
     * @param value
     **//*

    public void lRightPushAll(String key, String... value) {
        List<String> list = Arrays.asList(value);
        stringRedisTemplate.opsForList().rightPushAll(key, list);
    }

    */
/**
     * @Author lequal
     * @Description 右边批量插入
     * @Date 2022/11/14 0:00
     * @param key
     * @param value
     **//*

    public void lRightPushAll(String key, List<String> value) {
        stringRedisTemplate.opsForList().rightPushAll(key, value);
    }



    */
/** ============================ List操作 ============================ **//*



    */
/** ============================ Set操作 ============================ **//*


    public boolean isMember(String key, Object value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }


    */
/** ============================ Set操作 ============================ **//*

}
*/
