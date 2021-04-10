package com.jq.mall.service.impl;

import com.jq.mall.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存储数据
     */
    @Override
    public void set(String key, Object value) {
        log.info("[Set] Key: " + key + ". Value: " + value);
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储数据并设置过期时间
     */
    @Override
    public void set(String key, Object value, long time) {
        log.info("[Set] Key: " + key + ". Value: " + value);
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 获取数据
     */
    @Override
    public Object get(String key) {
        log.info("[Get] Key: " + key);
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置超期时间
     */
    @Override
    public Boolean expire(String key, long expire) {
        log.info("[Expire] Key: " + key + ". Expire: " + expire);
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 删除数据
     */
    @Override
    public Boolean delete(String key) {
        log.info("[Del] Key: " + key);
        return redisTemplate.delete(key);
    }

    /**
     * 批量删除数据
     */
    @Override
    public Long delete(List<String> keys) {
        log.info("[Del] Key: " + keys);
        return redisTemplate.delete(keys);
    }

    /**
     * 自增操作
     * @param delta 自增步长
     */
    @Override
    public Long increment(String key, long delta) {
        log.info("[Incr] Key: " + key + ". Delta: ", delta);
        return redisTemplate.opsForValue().increment(key, delta);
    }
}
