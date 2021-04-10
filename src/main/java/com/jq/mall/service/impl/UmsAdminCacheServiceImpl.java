package com.jq.mall.service.impl;

import com.jq.mall.mbg.model.UmsAdmin;
import com.jq.mall.service.RedisService;
import com.jq.mall.service.UmsAdminCacheService;
import com.jq.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.key.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.prefix.admin}")
    private String REDIS_KEY_PREFIX_ADMIN;
    @Value("${redis.key.prefix.resourceList}")
    private String REDIS_KEY_PREFIX_RESOURCE_LIST;

    /**
     * 设置缓存后台用户信息
     */
    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PREFIX_ADMIN + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    /**
     * 获取缓存后台用户信息
     */
    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PREFIX_ADMIN + username;
        return (UmsAdmin) redisService.get(key);
    }

    /**
     * 删除后台用户缓存
     */
    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminService.getAdminById(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_PREFIX_ADMIN + admin.getUsername();
            redisService.delete(key);
        }
    }
}
