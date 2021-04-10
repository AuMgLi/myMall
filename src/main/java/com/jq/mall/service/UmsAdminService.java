package com.jq.mall.service;

import com.jq.mall.dto.UmsAdminParam;
import com.jq.mall.dto.UpdateAdminPasswordParam;
import com.jq.mall.mbg.model.UmsAdmin;
import com.jq.mall.mbg.model.UmsResource;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 根据用户id获取用户
     */
    UmsAdmin getAdminById(Long id);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     * @return count
     */
    int update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails getUserDetailsByUsername(String username);  // Todo 用户 or Admin?


    // TODO
    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */

    /**
     * 修改用户角色关系
     */
//    @Transactional
//    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对应角色
     */

    /**
     * 获取指定用户的可访问资源
     * */
     List<UmsResource> getResourceList(Long adminId);
}
