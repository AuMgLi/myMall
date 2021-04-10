package com.jq.mall.service.impl;

import com.jq.mall.bo.AdminUserDetails;
import com.jq.mall.common.exception.Asserts;
import com.jq.mall.dto.UmsAdminParam;
import com.jq.mall.dto.UpdateAdminPasswordParam;
import com.jq.mall.mbg.mapper.UmsAdminMapper;
import com.jq.mall.mbg.model.UmsAdmin;
import com.jq.mall.mbg.model.UmsAdminExample;
import com.jq.mall.mbg.model.UmsResource;
import com.jq.mall.service.UmsAdminCacheService;
import com.jq.mall.service.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    /**
     * 根据用户名获取后台管理员
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);  // Get redis
        if (admin != null) {
            return admin;
        }
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);  // where...
        List<UmsAdmin> adminList = adminMapper.selectByExample(adminExample);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            // Set redis
            adminCacheService.setAdmin(admin);
        }
        return admin;
    }

    /**
     * 根据用户id获取用户
     */
    @Override
    public UmsAdmin getAdminById(Long id) {
//        log.info("Service querying id: " + id);
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 注册功能
     */
    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin admin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(1);

        // 查询是否有相同用户名的用户
        if (getAdminByUsername(admin.getUsername()) != null) {
            return null;
        }

        // 将密码进行加密操作
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        adminMapper.insert(admin);

        return admin;
    }

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = getUserDetailsByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                Asserts.fail("用户名或密码错误");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("账号已被禁用");
            }
            // Todo Token
            token = "Hello Kitty!";
        } catch (Exception e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword
     * @param pageSize
     * @param pageNum
     */
    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    /**
     * 修改指定用户信息
     *
     * @return count
     */
    @Override
    public int update(Long id, UmsAdmin admin) {
        return 0;
    }

    /**
     * 删除指定用户
     */
    @Override
    public int delete(Long id) {
        return 0;
    }

    /**
     * 修改密码
     */
    @Override
    public int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam) {
        return 0;
    }

    /**
     * 获取用户信息
     */
    @Override
    public UserDetails getUserDetailsByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
//            List<UmsResource> resourceList = getR; TODO
            return new AdminUserDetails(admin, null);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 获取指定用户的可访问资源
     */
    @Override
    public List<UmsResource> getResourceList(Long adminId) {
//        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);  Todo

        return null;
    }

}
