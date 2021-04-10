package com.jq.mall.service;

import java.util.List;

/**
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, Object value);

    /**
     * 存储数据并设置过期时间
     */
    void set(String key, Object value, long time);

    /**
     * 获取数据
     */
    Object get(String key);

    /**
     * 设置超期时间
     */
    Boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    Boolean delete(String key);

    /**
     * 批量删除数据
     */
    Long delete(List<String> keys);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);
}
