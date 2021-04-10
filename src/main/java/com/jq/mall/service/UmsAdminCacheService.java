package com.jq.mall.service;

import com.jq.mall.mbg.model.UmsAdmin;

import java.util.List;

/**
 * 管理员缓存操作Service
 */
public interface UmsAdminCacheService {

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 获取缓存后台用户信息
     */
    UmsAdmin getAdmin(String username);

    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    // TODO -----------------------------------------
    /**
     * 删除后台用户资源列表缓存
     */
//    void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
//    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
//    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
//    void delResourceListByResource(Long resourceId);

    /**
     * 获取缓存后台用户资源列表
     */
//    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置缓存后台用户资源列表
     */
//    void setResourceList(Long adminId, List<UmsResource> resourceList);

}
